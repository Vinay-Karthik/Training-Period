package Kiss_Principle.Without;

class SalaryValidator {

    public boolean isValid(double salary) {
        if (salary > 0) {
            return true;
        } else {
            return false;
        }
    }
}
