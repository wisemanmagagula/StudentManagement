package wisemanmagagula.studentmanagement_backend.Service.Interface;

import wisemanmagagula.studentmanagement_backend.DTO.StudentScoreRequest;
import wisemanmagagula.studentmanagement_backend.Model.StudentScore;
import java.util.List;

public interface IStudentScore {

    StudentScore Add(StudentScoreRequest scoreRequest);

    List<StudentScore> Get(Long StudentId);
}
