package wisemanmagagula.studentmanagement_backend.Model;

import jakarta.persistence.*;
import wisemanmagagula.studentmanagement_backend.DTO.StudentScoreRequest;

@Entity
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private double Score;

    public StudentScore(StudentScoreRequest request){
        studentId = request.getStudentId();
        Score = request.getScore();
    }

    public StudentScore(){}

    public Long getStudentId() {
        return studentId;
    }

    public double getScore() {
        return Score;
    }

    public Long getId() {
        return id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(double score) {
        Score = score;
    }
}
