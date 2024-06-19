import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { StudentResponse } from "../models/StudentResponse";
import { UpdateStudent } from "../models/UpdateStudent";
import { AddStudentScore } from "../models/AddStudentScore";

@Injectable({
    providedIn: 'root'
})
export class StudentService {
    private apiUrl = 'http://localhost:8080/api';

    constructor(private http: HttpClient) { }

    getStudents(): Observable<StudentResponse[]> {
        return this.http.get<StudentResponse[]>(`${this.apiUrl}/students`);
    }

    searchStudents(searchRequest: any): Observable<StudentResponse[]> {
        const url = `${this.apiUrl}/students/search`;
        return this.http.post<StudentResponse[]>(url, searchRequest);
    }

    addStudent(student: any): Observable<any> {
        const url = `${this.apiUrl}/students`;
        return this.http.post<any>(url, student)
            .pipe(
                catchError(this.handleError)
            );
    }

    updateStudent(updateRequest: UpdateStudent): Observable<StudentResponse> {
        return this.http.put<StudentResponse>(this.apiUrl, updateRequest)
          .pipe(catchError(this.handleError));
      }

      addScore(scoreRequest: AddStudentScore): Observable<void> {
        const url = `${this.apiUrl}/students/score`;
        return this.http.post<void>(url, scoreRequest)
          .pipe(catchError(this.handleError));
      }

      deleteStudent(studentId: number): Observable<void> {
        const url = `${this.apiUrl}/students/${studentId}`;
        return this.http.delete<void>(url).pipe(
          catchError(this.handleError)
        );
      }

    private handleError(error: any) {
        console.error('API Error:', error);
        return throwError(error);
    }
}