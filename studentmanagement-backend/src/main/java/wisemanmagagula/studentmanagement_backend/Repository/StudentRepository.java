package wisemanmagagula.studentmanagement_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wisemanmagagula.studentmanagement_backend.Model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.firstName) LIKE LOWER(concat('%', :firstName, '%'))")
    List<Student> findByFirstName(String firstName);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.lastName) LIKE LOWER(concat('%', :lastName, '%'))")
    List<Student> findByLastName(String lastName);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.emailAddress) LIKE LOWER(concat('%', :emailAddress, '%'))")
    List<Student> findByEmailAddress(String emailAddress);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.studentNumber) LIKE LOWER(concat('%', :studentNumber, '%'))")
    List<Student> findByStudentNumber(String studentNumber);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.firstName) LIKE LOWER(concat('%', :firstName, '%')) AND LOWER(s.lastName) LIKE LOWER(concat('%', :lastName, '%'))")
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByIsDeletedFalse();
}