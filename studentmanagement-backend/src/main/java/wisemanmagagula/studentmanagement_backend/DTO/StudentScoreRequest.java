package wisemanmagagula.studentmanagement_backend.DTO;

public class StudentScoreRequest {

    private Long StudentId;

    private double Score;

    public StudentScoreRequest(Long StudentId, double Score){
        this.StudentId = StudentId;
        this.Score = Score;
    }

    public void setStudentId(Long StudentId) {
        this.StudentId = StudentId;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public double getScore() {
        return Score;
    }

    public Long getStudentId() {
        return StudentId;
    }
}
