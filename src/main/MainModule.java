package main;

import entity.Applicant;
import entity.Company;
import entity.JobListing;
import entity.JobApplication;

import java.util.Date;

public class MainModule {
    public static void main(String[] args) {
        // Create a company
        Company company = new Company(1, "Tech Solutions", "New York");

        // Company posts a job
        company.postJob("Software Engineer", "Develop and maintain software applications.", "Remote", 120000.00, "Full-time");

        // Create an applicant
        Applicant applicant = new Applicant(1, "Harika", "Kari", "harika.kari@example.com", "123-456-7890", "resume_link");

        // Applicant creates a profile
        applicant.createProfile();

        // Applicant applies for the job
        JobListing job = company.getJobs().get(0);
        applicant.applyForJob(job, "I am very interested in this position.");

        // Retrieve applicants for the job
        System.out.println("Number of applicants for the job: " + job.getApplicants().size());

        // Submit a job application
        JobApplication application = new JobApplication(1, job.getJobID(), applicant.getApplicantID(), new Date(), "Cover letter content");
        application.submitApplication();
    }
}
