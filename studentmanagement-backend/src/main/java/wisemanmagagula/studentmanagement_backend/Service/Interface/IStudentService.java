package wisemanmagagula.studentmanagement_backend.Service.Interface;

import wisemanmagagula.studentmanagement_backend.DTO.*;
import wisemanmagagula.studentmanagement_backend.Model.Student;
import java.util.List;

public interface IStudentService {

    StudentResponse Add(StudentCreateRequest request);

    List<StudentResponse> Get();

    Student Update(StudentUpdateRequest request);

    void Remove(Long Id);

    void AddScore(StudentScoreRequest request);

    List<StudentResponse> Search(StudentSearchRequest request);

}
