package wisemanmagagula.studentmanagement_backend.DTO;

import wisemanmagagula.studentmanagement_backend.Model.Student;

public class StudentResponse {

    private Long id;
    private String studentNumber;

    private String firstName;

    private String lastName;

    private String dob;

    private String cellPhone;

    private String emailAddress;

    private double currentScore;

    private double averageScore;

    public StudentResponse(Student student, double currentScore, double averageScore){
        this.id = student.getId();
        this.studentNumber = student.getStudentNumber();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.dob = student.getDob();
        this.cellPhone = student.getCellPhone();
        this.emailAddress = student.getEmailAddress();
        this.currentScore = currentScore;
        this.averageScore = averageScore;
    }

    public StudentResponse(){}

    // Getters


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

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public double getAverageScore() {
        return averageScore;
    }
}