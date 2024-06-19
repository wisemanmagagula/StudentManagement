package wisemanmagagula.studentmanagement_backend;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wisemanmagagula.studentmanagement_backend.DTO.*;
import wisemanmagagula.studentmanagement_backend.Model.Student;
import wisemanmagagula.studentmanagement_backend.Model.StudentScore;
import wisemanmagagula.studentmanagement_backend.Repository.StudentRepository;
import wisemanmagagula.studentmanagement_backend.Service.Interface.IStudentService;
import wisemanmagagula.studentmanagement_backend.Service.StudentScoreService;
import wisemanmagagula.studentmanagement_backend.Service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTests {

    @Mock
    private IStudentService iStudentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    StudentScoreService studentScoreService;

    @InjectMocks
    StudentService studentService;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_add_student_profile() {
        StudentCreateRequest request = new StudentCreateRequest("Wiseboy", "McGoo", "19/12/1990",
                "+27603462440", "macgoo@gmail.com", 50);

        Student savedStudent = new Student(request);
        savedStudent.setId(1L);

        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            student.setId(1L);
            return student;
        });

        StudentScore savedStudentScore = new StudentScore();
        savedStudentScore.setId(1L);
        savedStudentScore.setScore(50);

        when(studentScoreService.Add(any(StudentScoreRequest.class))).thenReturn(savedStudentScore);

        when(studentRepository.save(any(Student.class))).thenAnswer(invocation ->
                invocation.getArgument(0));

        StudentResponse actualResponse = studentService.Add(request);
        assertEquals("WiseboyMcGoo", actualResponse.getStudentNumber());
        assertEquals(50, actualResponse.getCurrentScore());

    }

    @Test
    void should_get_all_students() {

        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("Wiseboy");
        student1.setLastName("McGoo");
        student1.setCurrentScoreId(101L);
        student1.setStudentNumber();

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCurrentScoreId(102L);
        student2.setStudentNumber();

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findByIsDeletedFalse()).thenReturn(students);

        List<StudentScore> scoresStudent1 = Arrays.asList(
                new StudentScore(new StudentScoreRequest(1L, 50)),
                new StudentScore(new StudentScoreRequest(1L, 60))
        );

        List<StudentScore> scoresStudent2 = Arrays.asList(
                new StudentScore(new StudentScoreRequest(2L, 70)),
                new StudentScore(new StudentScoreRequest(2L, 80))
        );

        when(studentScoreService.Get(1L)).thenReturn(scoresStudent1);
        when(studentScoreService.Get(2L)).thenReturn(scoresStudent2);

        when(studentScoreService.FindById(101L)).thenReturn(new StudentScore(new StudentScoreRequest(1L, 60)));
        when(studentScoreService.FindById(102L)).thenReturn(new StudentScore(new StudentScoreRequest(2L, 80)));

        List<StudentResponse> actualResponses = studentService.Get();

        assertEquals(2, actualResponses.size());

        StudentResponse response1 = actualResponses.get(0);
        assertEquals("WiseboyMcGoo", response1.getStudentNumber());
        assertEquals(60, response1.getCurrentScore());
        assertEquals(55, response1.getAverageScore(), 0.01);

        StudentResponse response2 = actualResponses.get(1);
        assertEquals("JohnDoe", response2.getStudentNumber());
        assertEquals(80, response2.getCurrentScore());
        assertEquals(75, response2.getAverageScore(), 0.01);
    }

    @Test
    void should_update_existing_student() {

        StudentUpdateRequest request = new StudentUpdateRequest(1L, "UpdatedFirstName", "UpdatedLastName",
                "01/01/1990", "+27603462441", "updated.email@example.com");

        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setFirstName("OriginalFirstName");
        existingStudent.setLastName("OriginalLastName");
        existingStudent.setDob("01/01/1980");
        existingStudent.setCellPhone("+27603462440");
        existingStudent.setEmailAddress("original.email@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        Student updatedStudent = studentService.Update(request);

        assertEquals(request.getId(), updatedStudent.getId());
        assertEquals(request.getFirstName(), updatedStudent.getFirstName());
        assertEquals(request.getLastName(), updatedStudent.getLastName());
        assertEquals(request.getDOB(), updatedStudent.getDob());
        assertEquals(request.getCellPhone(), updatedStudent.getCellPhone());
        assertEquals(request.getEmailAddress(), updatedStudent.getEmailAddress());
    }

    @Test
    void should_add_score_to_student() {

        StudentScoreRequest request = new StudentScoreRequest(1L, 80.0);

        StudentScore studentScore = new StudentScore();
        studentScore.setId(1L);

        Student student = new Student();
        student.setId(1L);
        student.setCurrentScoreId(null);

        when(studentScoreService.Add(request)).thenReturn(studentScore);
        when(studentRepository.findById(1L)).thenReturn(java.util.Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        studentService.AddScore(request);

        assertEquals(studentScore.getId(), student.getCurrentScoreId());

        // Verify interactions
        verify(studentScoreService, times(1)).Add(request);
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void should_throw_entity_not_found_exception_when_student_not_found() {

        StudentScoreRequest request = new StudentScoreRequest(999L, 80.0);

        when(studentRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> studentService.AddScore(request));
        assertEquals("Student not found with id: 999", exception.getMessage());

        verify(studentRepository, times(1)).findById(999L);
        verify(studentRepository, never()).save(any(Student.class));
    }


    @Test
    void should_remove_existing_student() {

        Long studentId = 1L;

        Student existingStudent = new Student();
        existingStudent.setId(studentId);
        existingStudent.setDeleted(false);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        studentService.Remove(studentId);

        assertTrue(existingStudent.isDeleted());

        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(existingStudent);
    }

    @Test
    void should_throw_entity_not_found_exception_when_student_not_found_for_remove() {

        Long nonExistentStudentId = 999L;

        when(studentRepository.findById(nonExistentStudentId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> studentService.Remove(nonExistentStudentId));
        assertEquals("Student not found with id: " + nonExistentStudentId, exception.getMessage());

        verify(studentRepository, times(1)).findById(nonExistentStudentId);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void should_search_by_first_name_only() {
        StudentSearchRequest request = new StudentSearchRequest();
        request.setFirstName("Alice");

        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(new StudentCreateRequest("Alice", "Smith", "05/05/1992", "+1122334455",
                "alice.smith@example.com", 82)));
        when(studentRepository.findByFirstName(request.getFirstName())).thenReturn(mockStudents);

        when(studentScoreService.Add(any())).thenAnswer(invocation -> {
            StudentScoreRequest scoreRequest = invocation.getArgument(0);
            return new StudentScore(scoreRequest);
        });

        when(studentScoreService.FindById(any())).thenReturn(new StudentScore(new StudentScoreRequest(1L, 85.0)));

        List<StudentResponse> result = studentService.Search(request);

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getFirstName());

        verify(studentRepository, times(1)).findByFirstName(request.getFirstName());
        verify(studentScoreService, times(0)).Add(any());
        verify(studentScoreService, times(1)).FindById(any());
    }

    @Test
    void should_search_by_last_name_only() {

        StudentSearchRequest request = new StudentSearchRequest();
        request.setLastName("Smith");

        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(new StudentCreateRequest("Alice", "Smith", "05/05/1992", "+1122334455",
                "alice.smith@example.com", 82)));
        when(studentRepository.findByLastName(request.getLastName())).thenReturn(mockStudents);

        when(studentScoreService.Add(any())).thenAnswer(invocation -> {
            StudentScoreRequest scoreRequest = invocation.getArgument(0);
            return new StudentScore(scoreRequest);
        });

        when(studentScoreService.FindById(any())).thenReturn(new StudentScore(new StudentScoreRequest(1L, 85.0)));

        List<StudentResponse> result = studentService.Search(request);

        assertEquals(1, result.size());
        assertEquals("Smith", result.get(0).getLastName());

        verify(studentRepository, times(1)).findByLastName(request.getLastName());
        verify(studentScoreService, times(0)).Add(any());
        verify(studentScoreService, times(1)).FindById(any());
    }

    @Test
    void should_search_by_email_address_only() {
        StudentSearchRequest request = new StudentSearchRequest();
        request.setEmailAddress("alice.smith@example.com");

        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(new StudentCreateRequest("Alice", "Smith", "05/05/1992", "+1122334455",
                "alice.smith@example.com", 82)));
        when(studentRepository.findByEmailAddress(request.getEmailAddress())).thenReturn(mockStudents);

        when(studentScoreService.Add(any())).thenAnswer(invocation -> {
            StudentScoreRequest scoreRequest = invocation.getArgument(0);
            return new StudentScore(scoreRequest);
        });

        when(studentScoreService.FindById(any())).thenReturn(new StudentScore(new StudentScoreRequest(1L, 85.0)));

        List<StudentResponse> result = studentService.Search(request);

        assertEquals(1, result.size());
        assertEquals("alice.smith@example.com", result.get(0).getEmailAddress());

        verify(studentRepository, times(1)).findByEmailAddress(request.getEmailAddress());
        verify(studentScoreService, times(0)).Add(any());
        verify(studentScoreService, times(1)).FindById(any());
    }

    @Test
    void should_search_by_student_number_only() {

        StudentSearchRequest request = new StudentSearchRequest();
        request.setStudentNumber("AliceSmith");

        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(new StudentCreateRequest("Alice", "Smith", "05/05/1992", "+1122334455",
                "alice.smith@example.com", 82)));
        when(studentRepository.findByStudentNumber(request.getStudentNumber())).thenReturn(mockStudents);

        when(studentScoreService.Add(any())).thenAnswer(invocation -> {
            StudentScoreRequest scoreRequest = invocation.getArgument(0);
            return new StudentScore(scoreRequest);
        });

        when(studentScoreService.FindById(any())).thenReturn(new StudentScore(new StudentScoreRequest(1L, 85.0)));

        List<StudentResponse> result = studentService.Search(request);

        assertEquals(1, result.size());
        assertEquals("AliceSmith", result.get(0).getStudentNumber());

        verify(studentRepository, times(1)).findByStudentNumber(request.getStudentNumber());
        verify(studentScoreService, times(0)).Add(any());
        verify(studentScoreService, times(1)).FindById(any());
    }

    @Test
    void should_throw_exception_when_no_search_parameters_provided() {

        StudentSearchRequest request = new StudentSearchRequest();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> studentService.Search(request));
        assertEquals("At least one search parameter must be provided", exception.getMessage());

        verifyNoInteractions(studentRepository);
    }

}
