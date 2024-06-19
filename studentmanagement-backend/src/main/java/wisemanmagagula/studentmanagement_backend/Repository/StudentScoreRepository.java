package wisemanmagagula.studentmanagement_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wisemanmagagula.studentmanagement_backend.Model.StudentScore;

import java.util.List;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {

    List<StudentScore> findByStudentId(Long studentId);
}