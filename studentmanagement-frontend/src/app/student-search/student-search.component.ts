import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StudentService } from '../services/student.services';
import { StudentResponse } from '../models/StudentResponse';

@Component({
  selector: 'app-student-search',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, CommonModule, FormsModule],
  templateUrl: './student-search.component.html',
  styleUrl: './student-search.component.css'
})
export class StudentSearchComponent implements OnInit {
  searchCriteria: string = '';
  searchValue: string = '';

  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
  }

  searchStudents(): void {
    if (this.searchCriteria && this.searchValue) {
      const searchRequest: { [key: string]: string } = {};
      searchRequest[this.searchCriteria] = this.searchValue;

      this.studentService.searchStudents(searchRequest)
        .subscribe((students: StudentResponse[]) => {
          console.log('Search Results:', students);
        }, (error) => {
          console.error('Error searching students:', error);
        });
    } else {
      console.log('Please select search criteria and enter search value.');
    }
  }

  clearForm(): void {
    this.searchCriteria = '';
    this.searchValue = '';
  }
}
