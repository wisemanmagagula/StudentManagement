package wisemanmagagula.studentmanagement_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisemanmagagula.studentmanagement_backend.DTO.StudentScoreRequest;
import wisemanmagagula.studentmanagement_backend.Model.StudentScore;
import wisemanmagagula.studentmanagement_backend.Repository.StudentScoreRepository;
import wisemanmagagula.studentmanagement_backend.Service.Interface.IStudentScore;

import java.util.List;

@Service
public class StudentScoreService implements IStudentScore {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public StudentScore Add(StudentScoreRequest scoreRequest) {
        return studentScoreRepository.save(new StudentScore(scoreRequest));
    }

    @Override
    public List<StudentScore> Get(Long StudentId) {
        return studentScoreRepository.findByStudentId(StudentId);
    }

    public StudentScore FindById(Long id){
        return studentScoreRepository.findById(id).orElseThrow();
    }
}
