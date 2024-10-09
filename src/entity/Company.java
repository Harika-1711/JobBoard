package entity;

public class Company {
    private int companyID;
    private String companyName;
    private String location;

    public Company(int companyID, String companyName, String location) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.location = location;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }
    
}
