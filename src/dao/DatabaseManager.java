package dao;
import entity.Applicant;
import entity.Company;
import entity.JobApplication;
import entity.JobListing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DatabaseManager {
public void initializeDatabase() {
try (Connection connection = DBConnUtil.getConnection()) {
// Create tables if they don't exist
String createCompaniesTable = "CREATE TABLE IF NOT EXISTS Companies (" +
"CompanyID INT PRIMARY KEY," +
"CompanyName VARCHAR(255)," +
"Location VARCHAR(255));";
String createJobsTable = "CREATE TABLE IF NOT EXISTS Jobs (" +                    "JobID INT PRIMARY KEY," +
"CompanyID INT," +
"JobTitle VARCHAR(255)," +
"JobDescription TEXT," +
"JobLocation VARCHAR(255)," +
"Salary DECIMAL(10, 2)," +
"JobType VARCHAR(50)," +
"PostedDate DATETIME," +
"FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID));";
String createApplicantsTable = "CREATE TABLE IF NOT EXISTS Applicants (" +
"ApplicantID INT PRIMARY KEY," +
"FirstName VARCHAR(255)," +
"LastName VARCHAR(255)," +
"Email VARCHAR(255)," +
"Phone VARCHAR(15)," +
"Resume VARCHAR(255));";
String createApplicationsTable = "CREATE TABLE IF NOT EXISTS Applications (" +
"ApplicationID INT PRIMARY KEY," +
"JobID INT," +
"ApplicantID INT," +
"ApplicationDate DATETIME," +
"CoverLetter TEXT," +
"FOREIGN KEY (JobID) REFERENCES Jobs(JobID)," +
"FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID));";
try (PreparedStatement stmt1 = connection.prepareStatement(createCompaniesTable);
PreparedStatement stmt2 = connection.prepareStatement(createJobsTable);
PreparedStatement stmt3 = connection.prepareStatement(createApplicantsTable);
PreparedStatement stmt4 = connection.prepareStatement(createApplicationsTable)) {
stmt1.execute();
stmt2.execute();
stmt3.execute();
stmt4.execute();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
public void insertCompany(Company company) {
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "INSERT INTO Companies (CompanyID, CompanyName, Location) VALUES (?, ?, ?)";
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
stmt.setInt(1, company.getCompanyID());
stmt.setString(2, company.getCompanyName());
stmt.setString(3, company.getLocation());
stmt.executeUpdate();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
public void insertJobListing(JobListing job) {
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
stmt.setInt(1, job.getJobID());
stmt.setInt(2, job.getCompanyID());
stmt.setString(3, job.getJobTitle());
stmt.setString(4, job.getJobDescription());
stmt.setString(5, job.getJobLocation());
stmt.setDouble(6, job.getSalary());
stmt.setString(7, job.getJobType());
stmt.setTimestamp(8, new java.sql.Timestamp(job.getPostedDate().getTime()));
stmt.executeUpdate();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
public void insertApplicant(Applicant applicant) {
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "INSERT INTO Applicants (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?, ?)";
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
stmt.setInt(1, applicant.getApplicantID());
stmt.setString(2, applicant.getFirstName());
stmt.setString(3, applicant.getLastName());
stmt.setString(4, applicant.getEmail());
stmt.setString(5, applicant.getPhone());
stmt.setString(6, applicant.getResume());
stmt.executeUpdate();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
public void insertJobApplication(JobApplication application) {
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "INSERT INTO Applications (ApplicationID, JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?, ?)";
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
stmt.setInt(1, application.getApplicationID());
stmt.setInt(2, application.getJobID());
stmt.setInt(3, application.getApplicantID());
stmt.setTimestamp(4, new java.sql.Timestamp(application.getApplicationDate().getTime()));
stmt.setString(5, application.getCoverLetter());
stmt.executeUpdate();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
public List<Company> getCompanies() {
List<Company> companies = new ArrayList<>();
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "SELECT * FROM Companies";
try (PreparedStatement stmt = connection.prepareStatement(sql);
ResultSet rs = stmt.executeQuery()) {
while (rs.next()) {
int companyID = rs.getInt("CompanyID");
String companyName = rs.getString("CompanyName");
String location = rs.getString("Location");
companies.add(new Company(companyID, companyName, location));
}
}
} catch (SQLException e) {
e.printStackTrace();
}
return companies;
}
public List<JobListing> getJobListings() {
List<JobListing> jobListings = new ArrayList<>();
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "SELECT * FROM Jobs";
try (PreparedStatement stmt = connection.prepareStatement(sql);
ResultSet rs = stmt.executeQuery()) {
while (rs.next()) {
int jobID = rs.getInt("JobID");
int companyID = rs.getInt("CompanyID");
String jobTitle = rs.getString("JobTitle");
String jobDescription = rs.getString("JobDescription");
String jobLocation = rs.getString("JobLocation");
double salary = rs.getDouble("Salary");
String jobType = rs.getString("JobType");
Date postedDate = rs.getTimestamp("PostedDate");
jobListings.add(new JobListing(jobID, companyID, jobTitle, jobDescription, jobLocation, salary, jobType, postedDate));
}
}
} catch (SQLException e) {
e.printStackTrace();
}
return jobListings;
}
public List<Applicant> getApplicants() {
List<Applicant> applicants = new ArrayList<>();
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "SELECT * FROM Applicants";
try (PreparedStatement stmt = connection.prepareStatement(sql);
ResultSet rs = stmt.executeQuery()) {
while (rs.next()) {
int applicantID = rs.getInt("ApplicantID");
String firstName = rs.getString("FirstName");
String lastName = rs.getString("LastName");
String email = rs.getString("Email");
String phone = rs.getString("Phone");
String resume = rs.getString("Resume");
applicants.add(new Applicant(applicantID, firstName, lastName, email, phone, resume));
}
}
} catch (SQLException e) {
e.printStackTrace();
}
return applicants;
}

public List<JobApplication> getApplicationsForJob(int jobID) {
List<JobApplication> applications = new ArrayList<>();
try (Connection connection = DBConnUtil.getConnection()) {
String sql = "SELECT * FROM Applications WHERE JobID = ?";
try (PreparedStatement stmt = connection.prepareStatement(sql)) {
stmt.setInt(1, jobID);
try (ResultSet rs = stmt.executeQuery()) {
while (rs.next()) {
int applicationID = rs.getInt("ApplicationID");
int applicantID = rs.getInt("ApplicantID");
Date applicationDate = rs.getTimestamp("ApplicationDate");
String coverLetter = rs.getString("CoverLetter");
applications.add(new JobApplication(applicationID, jobID, applicantID, applicationDate, coverLetter));
}
}
}
} catch (SQLException e) {
e.printStackTrace();
}
return applications;
}
}
