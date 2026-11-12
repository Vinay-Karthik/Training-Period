import java.util.List;

public class DataProcessor {
    public void processData(List<Customer> customerList){
        long start=System.currentTimeMillis();
        for(Customer customer : customerList){
                transform(customer);
        }
        long end=System.currentTimeMillis();
        System.out.println("Time Taken: " +(end-start));

    }

    private void transform(Customer customer) {
        String email= customer.getEmail();
        double bonus = customer.getSalary()*0.2;
        customer.setEmail(email.toUpperCase());
        customer.setSalary(bonus);

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
