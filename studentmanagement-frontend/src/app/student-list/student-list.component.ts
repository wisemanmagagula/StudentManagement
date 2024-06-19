import { Component, Input, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { StudentService } from '../services/student.services';
import { StudentResponse } from '../models/StudentResponse';
import { MatTableModule } from '@angular/material/table';
import { AddStudentScore } from "../models/AddStudentScore";
import { FormsModule } from '@angular/forms';




@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, MatListModule, CommonModule, HttpClientModule, MatTableModule, FormsModule],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent {
  @Input() students: StudentResponse[] = [];
  displayedColumns: string[] = ['fullName', 'email', 'studentNumber', 'dob', 'cellPhone', 'currentScore', 'averageScore', 'actions'];
  scoreToAdd: number = 0;
  studentScore: AddStudentScore = {StudentId: 0, Score: 0};

  constructor(
    private studentService: StudentService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.studentService.getStudents().subscribe(
      students => {
        this.students = students;
      },
      error => {
        console.error('Error loading students:', error);
        this.snackBar.open('Failed to load students', 'Close', { duration: 3000 });
      }
    );
  }

  addScore(student: StudentResponse): void {
    if (this.scoreToAdd <= 0) {
      this.snackBar.open('Please enter a valid score', 'Close', { duration: 3000 });
      return;
    }

    console.log('Add score clicked for student:', student);
    this.studentScore.Score = this.scoreToAdd;
    this.studentScore.StudentId = student.id;
    this.studentService.addScore(this.studentScore).subscribe(
      () => {
        this.snackBar.open('Score added successfully', 'Close', { duration: 3000 });
        this.scoreToAdd = 0;
      },
      error => {
        console.error('Error adding score:', error);
        this.snackBar.open('Failed to add score', 'Close', { duration: 3000 });
      }
    );
  }

  deleteStudent(student: StudentResponse): void {
    console.log('Delete clicked for student:', student);
    if (confirm(`Are you sure you want to delete ${student.firstName} ${student.lastName}?`)) {
      this.studentService.deleteStudent(student.id).subscribe(
        () => {
          this.snackBar.open('Student deleted successfully', 'Close', { duration: 3000 });
          this.students = this.students.filter(s => s.id !== student.id);
        },
        error => {
          console.error('Error deleting student:', error);
          this.snackBar.open('Failed to delete student', 'Close', { duration: 3000 });
        }
      );
    }
  }
}
