package KISS_Principle.With;


public class Main {

    public static void main(String[] args) {

        double monthlySalary = 50000;

        if (monthlySalary <= 0) {
            System.out.println("Invalid Salary");
            return;
        }

        double annualSalary = monthlySalary * 12;

        System.out.println(
                "Annual Salary: Rs. " + annualSalary
        );
    }
}

