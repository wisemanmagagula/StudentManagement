package wisemanmagagula.studentmanagement_backend.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisemanmagagula.studentmanagement_backend.DTO.*;
import wisemanmagagula.studentmanagement_backend.Model.Student;
import wisemanmagagula.studentmanagement_backend.Model.StudentScore;
import wisemanmagagula.studentmanagement_backend.Repository.StudentRepository;
import wisemanmagagula.studentmanagement_backend.Service.Interface.IStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StudentService implements IStudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentScoreService studentScoreService;

    private double getAvgScore(List<StudentScore> studentScores) {

        double sum = 0;
        int count = studentScores.size();

        for (StudentScore studentScore : studentScores) {
            sum += studentScore.getScore();
        }

        return sum / count;
    }

    @Override
    public StudentResponse Add(StudentCreateRequest request) {

        Student savedStudent = studentRepository.save(new Student(request));
        StudentScore studentScore = studentScoreService.Add(new StudentScoreRequest(savedStudent.getId(), request.getCurrentScore()));
        savedStudent.setCurrentScoreId(studentScore.getId());
        studentRepository.save(savedStudent);

        return new StudentResponse(savedStudent, studentScore.getScore(), studentScore.getScore());

    }

    @Override
    public List<StudentResponse> Get() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        List<Student> students = studentRepository.findByIsDeletedFalse();

        for (Student student : students) {
            List<StudentScore> studentScores = studentScoreService.Get(student.getId());
            double avgScore = getAvgScore(studentScores);
            double lastScore = studentScoreService.FindById(student.getCurrentScoreId()).getScore();
            StudentResponse studentResponse = new StudentResponse(student, lastScore, avgScore);
            studentResponses.add(studentResponse);
        }
        return studentResponses;
    }

    @Override
    public Student Update(StudentUpdateRequest request) {

        Student existingStudent = studentRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getId()));

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            existingStudent.setFirstName(request.getFirstName());

        }

        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            existingStudent.setLastName(request.getLastName());
        }

        if (request.getDOB() != null) {
            existingStudent.setDob(request.getDOB());
        }

        if (request.getCellPhone() != null && !request.getCellPhone().isEmpty()) {
            existingStudent.setCellPhone(request.getCellPhone());
        }

        if (request.getEmailAddress() != null && !request.getEmailAddress().isEmpty()) {
            existingStudent.setEmailAddress(request.getEmailAddress());
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void AddScore(StudentScoreRequest request) {
        StudentScore studentScore = studentScoreService.Add(request);
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getStudentId()));
        student.setCurrentScoreId(studentScore.getId());
        studentRepository.save(student);
    }

    public List<StudentResponse> Search(StudentSearchRequest request) {
        if (request.getFirstName() != null && !request.getFirstName().isEmpty() &&
                request.getLastName() != null && !request.getLastName().isEmpty()) {
            List<Student> students = studentRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
            return MapStudentToStudentResponse(students);
        } else if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            List<Student> students = studentRepository.findByFirstName(request.getFirstName());
            return MapStudentToStudentResponse(students);
        } else if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            List<Student> students = studentRepository.findByLastName(request.getLastName());
            return MapStudentToStudentResponse(students);
        } else if (request.getEmailAddress() != null && !request.getEmailAddress().isEmpty()) {
            List<Student> students = studentRepository.findByEmailAddress(request.getEmailAddress());
            return MapStudentToStudentResponse(students);
        } else if (request.getStudentNumber() != null && !request.getStudentNumber().isEmpty()) {
            List<Student> students = studentRepository.findByStudentNumber(request.getStudentNumber());
            return MapStudentToStudentResponse(students);
        } else {
            throw new IllegalArgumentException("At least one search parameter must be provided");
        }
    }

    @Override
    public void Remove(Long Id) {
        Student existingStudent = studentRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + Id));
        existingStudent.setDeleted(true);
        studentRepository.save(existingStudent);
    }

    private List<StudentResponse> MapStudentToStudentResponse(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();

        for (Student student : students) {

            List<StudentScore> studentScores = studentScoreService.Get(student.getId());

            double avgScore = getAvgScore(studentScores);

            Long currentScoreId = student.getCurrentScoreId();
            StudentScore lastStudentScore = studentScoreService.FindById(currentScoreId);

            double lastScore = lastStudentScore.getScore();

            StudentResponse studentResponse = new StudentResponse(student, lastScore, avgScore);
            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }
}
