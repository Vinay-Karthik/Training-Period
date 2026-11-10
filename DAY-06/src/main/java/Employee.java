public class Employee {

    private int id;
    private String name;
    private byte[] data;

    public Employee(int id) {
        this.id = id;
        this.name = "Employee_" + id;

        // Allocate 100 KB per object
        this.data = new byte[100 * 1024];
    }
}