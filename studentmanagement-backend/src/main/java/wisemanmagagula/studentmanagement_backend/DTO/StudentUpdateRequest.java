package wisemanmagagula.studentmanagement_backend.DTO;

public class StudentUpdateRequest {

        private  Long Id;
        private String FirstName;

        private String LastName;

        private String DOB;

        private String CellPhone;

        private String EmailAddress;

        public StudentUpdateRequest(Long Id, String FirstName, String LastName, String DOB, String CellPhone, String EmailAddress){
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.DOB = DOB;
            this.CellPhone = CellPhone;
            this.EmailAddress = EmailAddress;
            this.Id = Id;
        }

    public Long getId() {
        return Id;
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


