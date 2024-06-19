import { Component } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { StudentSearchComponent } from "../student-search/student-search.component";
import { AddStudentComponent } from "../add-student/add-student.component";
import { SearchComponent } from "../search/search.component";

@Component({
    selector: 'app-student-manager',
    standalone: true,
    templateUrl: './student-manager.component.html',
    styleUrl: './student-manager.component.css',
    imports: [StudentSearchComponent, AddStudentComponent, MatTabsModule, SearchComponent]
})
export class StudentManagerComponent {

}
