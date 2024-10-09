package entity;

public class Applicant {
    private int applicantID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;

    public Applicant(int applicantID, String firstName, String lastName, String email, String phone, String resume) {
        this.applicantID = applicantID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getResume() {
        return resume;
    }

    public void createProfile(String email, String firstName, String lastName, String phone) {
        // Profile creation logic (if needed)
    }

    public void applyForJob(JobListing jobListing, String coverLetter) {
        // Application logic (if needed)
    }
}
