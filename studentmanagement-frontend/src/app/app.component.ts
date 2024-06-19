import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {StudentListComponent} from './student-list/student-list.component';
import { StudentSearchComponent } from "./student-search/student-search.component";
import { SearchComponent } from "./search/search.component";
import { AddStudentComponent } from "./add-student/add-student.component";
import { StudentManagerComponent } from "./student-manager/student-manager.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, StudentListComponent, HttpClientModule, StudentSearchComponent, SearchComponent, AddStudentComponent, StudentManagerComponent]
})
export class AppComponent {
  title = 'studentmanagement-frontend';
}
