package exceptions;
class NegativeSalaryException extends Exception {
    public NegativeSalaryException(String message) {
        super(message);
    }
}

public class SalaryCalculator {
    public static void main(String[] args) {
        double[] salaries = {50000, 60000, -70000, 55000}; // Example salaries
        
        try {
            double averageSalary = calculateAverageSalary(salaries);
            System.out.println("Average Salary: " + averageSalary);
        } catch (NegativeSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double calculateAverageSalary(double[] salaries) throws NegativeSalaryException {
        double totalSalary = 0;
        int count = 0;

        for (double salary : salaries) {
            if (salary < 0) {
                throw new NegativeSalaryException("Salary cannot be negative. Problematic salary: " + salary);
            }
            totalSalary += salary;
            count++;
        }

        return totalSalary / count;
    }
}
