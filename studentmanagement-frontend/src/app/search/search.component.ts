import { Component, OnInit } from '@angular/core';
import { StudentSearchComponent } from "../student-search/student-search.component";
import { StudentListComponent } from "../student-list/student-list.component";
import { StudentResponse } from '../models/StudentResponse';
import { StudentService } from '../services/student.services';

@Component({
    selector: 'app-search',
    standalone: true,
    templateUrl: './search.component.html',
    styleUrl: './search.component.css',
    imports: [StudentSearchComponent, StudentListComponent]
})
export class SearchComponent  implements OnInit{

    students: StudentResponse[] = [];

    constructor(private studentService: StudentService) {}

    ngOnInit(): void {
        this.studentService.getStudents().subscribe(
          (data: StudentResponse[]) => {
            this.students = data;
          },
          (error) => {
            console.error('Error fetching student data:', error);
          }
        );
      }
}
