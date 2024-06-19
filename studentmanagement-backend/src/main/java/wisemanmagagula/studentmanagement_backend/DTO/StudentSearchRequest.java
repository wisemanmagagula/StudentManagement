package wisemanmagagula.studentmanagement_backend.DTO;

public class StudentSearchRequest {

    private String StudentNumber;

    private String FirstName;

    private String LastName;

    private String EmailAddress;

    public StudentSearchRequest(String FirstName, String LastName, String EmailAddress, String StudentNumber){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.StudentNumber = StudentNumber;
    }

    public StudentSearchRequest(){}

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setStudentNumber(String StudentNumber) {
        this.StudentNumber = StudentNumber;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }
}
