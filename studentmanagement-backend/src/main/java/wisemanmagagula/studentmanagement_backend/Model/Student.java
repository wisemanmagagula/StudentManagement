package wisemanmagagula.studentmanagement_backend.Model;

import jakarta.persistence.*;
import wisemanmagagula.studentmanagement_backend.DTO.StudentCreateRequest;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "cell_phone_number")
    private String cellPhone;

    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "current_score_id")
    private Long currentScoreId;

    @Column(name = "deleted")
    private boolean isDeleted;

    public Student() {
    }

    public Student(StudentCreateRequest studentCreateRequest) {
        this.firstName = studentCreateRequest.getFirstName();
        this.lastName = studentCreateRequest.getLastName();
        this.dob = studentCreateRequest.getDOB();
        this.cellPhone = studentCreateRequest.getCellPhone();
        this.emailAddress = studentCreateRequest.getEmailAddress();
        setStudentNumber();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public Long getCurrentScoreId() {
        return currentScoreId;
    }

    public void setStudentNumber() {
        this.studentNumber = firstName + lastName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setCurrentScoreId(Long currentScoreId) {
        this.currentScoreId = currentScoreId;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }
}