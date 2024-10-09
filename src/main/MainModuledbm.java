package main;
import dao.DatabaseManager;
import entity.Applicant;
import entity.Company;
import entity.JobApplication;
import entity.JobListing;
import java.util.Date;
import java.util.List;
public class MainModuledbm {
    public static void main(String[] args) {
        // Initialize DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        // Initialize the database schema
        dbManager.initializeDatabase();
        System.out.println("Database initialized!");
        // Insert a new company
        Company company = new Company(1, "Tech Solutions", "New York");
        
        dbManager.insertCompany(company);
        System.out.println("Company inserted: " + company.getCompanyName());
        // Insert a new job listing
        JobListing jobListing = new JobListing(1, company.getCompanyID(), "Software Engineer", "Develop software applications", "New York", 80000.00, "Full-time", new Date());
        dbManager.insertJobListing(jobListing);
        System.out.println("Job listing inserted: " + jobListing.getJobTitle());
        // Insert a new applicant
        Applicant applicant = new Applicant(1, "John", "Doe", "john.doe@example.com", "1234567890", "resume.pdf");
        dbManager.insertApplicant(applicant);
        System.out.println("Applicant inserted: " + applicant.getFirstName() + " " + applicant.getLastName());
        // Applicant applies for a job
        JobApplication jobApplication = new JobApplication(1, jobListing.getJobID(), applicant.getApplicantID(), new Date(), "I am interested in this job.");
        dbManager.insertJobApplication(jobApplication);
        System.out.println("Job application submitted by: " + applicant.getFirstName() + " " + applicant.getLastName());

        // Retrieve and display all companies
        List<Company> companies = dbManager.getCompanies();
        System.out.println("All Companies:");
        for (Company c : companies) {
            System.out.println(c.getCompanyID() + " - " + c.getCompanyName() + ", " + c.getLocation());
        }
        // Retrieve and display all job listings
        List<JobListing> jobs = dbManager.getJobListings();
        System.out.println("\nAll Job Listings:");
        for (JobListing job : jobs) {
            System.out.println(job.getJobID() + " - " + job.getJobTitle() + " (" + job.getJobLocation() + ")");
        }
        // Retrieve and display all applicants
        List<Applicant> applicants = dbManager.getApplicants();
        System.out.println("\nAll Applicants:");
        for (Applicant a : applicants) {
            System.out.println(a.getApplicantID() + " - " + a.getFirstName() + " " + a.getLastName() + ", " + a.getEmail());
        }
        // Retrieve job applications for a specific job
        List<JobApplication> jobApplications = dbManager.getApplicationsForJob(jobListing.getJobID());
        System.out.println("\nJob Applications for Job ID " + jobListing.getJobID() + ":");
        for (JobApplication application : jobApplications) {
            System.out.println("Applicant ID: " + application.getApplicantID() + " applied with cover letter: " + application.getCoverLetter());
        }
    }
}
