package Kiss_Principle.Without;

public class Main {

    public static void main(String[] args) {

        EmployeeSalaryProcessor processor = new EmployeeSalaryProcessor();
        processor.processSalary(50000);
    }
}

//keep it simple stupid(KISS)
//Too many classes for a simple calculation.
//Unnecessary object creation.
//Unnecessary if-else logic.
//More code to maintain.
//Difficult to understand a simple business requirement
