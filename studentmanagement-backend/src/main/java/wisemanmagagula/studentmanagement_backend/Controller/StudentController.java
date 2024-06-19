package wisemanmagagula.studentmanagement_backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisemanmagagula.studentmanagement_backend.DTO.*;
import wisemanmagagula.studentmanagement_backend.Model.Student;
import wisemanmagagula.studentmanagement_backend.Service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private  StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentResponse>> Get(){

        try {
            return new ResponseEntity<>(studentService.Get(), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("This is an exception: " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> Add(@RequestBody StudentCreateRequest studentCreateRequest){
        try {
            return new ResponseEntity<>(studentService.Add(studentCreateRequest), HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("This is an exception: " + e.getMessage());
            return new ResponseEntity<>(new StudentResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> Update(@RequestBody StudentUpdateRequest request){
        try {
            return new ResponseEntity<>(studentService.Update(request), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("This is an exception: " + e.getMessage());
            return new ResponseEntity<>(new Student(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/score", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addScore(@RequestBody StudentScoreRequest request) {
        try {
            studentService.AddScore(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("This is an exception: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentResponse>> searchStudents(@RequestBody StudentSearchRequest request) {
        try {
            return new ResponseEntity<>(studentService.Search(request), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("This is an exception: " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long studentId) {
        try {
            studentService.Remove(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error removing student: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
