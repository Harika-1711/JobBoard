package entity;

import java.util.Date;

public class JobListing {
    private int jobID;
    private int companyID;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private double salary;
    private String jobType;
    private Date postedDate;

    public JobListing(int jobID, int companyID, String jobTitle, String jobDescription, String jobLocation, double salary, String jobType, Date postedDate) {
        this.jobID = jobID;
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.salary = salary;
        this.jobType = jobType;
        this.postedDate = postedDate;
    }

    public int getJobID() {
        return jobID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public double getSalary() {
        return salary;
    }

    public String getJobType() {
        return jobType;
    }

    public Date getPostedDate() {
        return postedDate;
    }
}
