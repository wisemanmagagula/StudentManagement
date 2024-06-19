package wisemanmagagula.studentmanagement_backend.DTO;


public class StudentCreateRequest {

    private String FirstName;

    private String LastName;

    private String DOB;

    private String CellPhone;

    private String EmailAddress;

    private double CurrentScore;

    public StudentCreateRequest(String FirstName, String LastName, String DOB, String CellPhone, String EmailAddress, double CurrentScore){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DOB = DOB;
        this.CellPhone = CellPhone;
        this.EmailAddress = EmailAddress;
        this.CurrentScore = CurrentScore;
    }

    public double getCurrentScore() {
        return CurrentScore;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getCellPhone() {
        return CellPhone;
    }
}
