import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StudentService } from '../services/student.services';
import { CreateStudent } from '../models/CreateStudent';

@Component({
  selector: 'app-add-student',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, CommonModule, FormsModule],
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.css'
})
export class AddStudentComponent {
  student: CreateStudent = {
    FirstName: '',
    LastName: '',
    EmailAddress: '',
    DOB: '',
    CellPhone: '',
    CurrentScore: 0
  };

  constructor(private studentService: StudentService) {}

  onSubmit() {
    this.studentService.addStudent(this.student).subscribe(
      (response) => {
        console.log('Student added successfully:', response);
        this.clearForm();
      },
      (error) => {
        console.error('Error adding student:', error);
      }
    );
  }

  clearForm() {
    this.student = {
      FirstName: '',
      LastName: '',
      DOB: '',
      CellPhone: '',
      EmailAddress: '',
      CurrentScore: 0
    };
  }
}
