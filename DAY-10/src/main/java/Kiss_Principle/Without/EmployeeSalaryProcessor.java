package KISS_Principle.Without;

class EmployeeSalaryProcessor {
    SalaryValidator validator = new SalaryValidator();
    SalaryCalculator calculator = new SalaryCalculator();
    SalaryFormatter formatter = new SalaryFormatter();

    public void processSalary(double monthlySalary) {

        if (validator.isValid(monthlySalary)) {
            double annualSalary = calculator.calculate(monthlySalary);
            String result = formatter.format(annualSalary);
            System.out.println(result);
        } else {
            System.out.println("Invalid Salary");
        }
    }
}
