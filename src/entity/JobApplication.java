package entity;

import java.util.Date;

public class JobApplication {
    private int applicationID;
    private int jobID;
    private int applicantID;
    private Date applicationDate;
    private String coverLetter;

    public JobApplication(int applicationID, int jobID, int applicantID, Date applicationDate, String coverLetter) {
        this.applicationID = applicationID;
        this.jobID = jobID;
        this.applicantID = applicantID;
        this.applicationDate = applicationDate;
        this.coverLetter = coverLetter;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public int getJobID() {
        return jobID;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }
}
