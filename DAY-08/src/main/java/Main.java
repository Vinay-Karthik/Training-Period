import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println(ProcessHandle.current().pid());

        CustomerService customerService = new CustomerService();
        List<Customer> customers=customerService.generateCustomers(1000);

        DataProcessor dataProcessor=new DataProcessor();
        dataProcessor.processData(customers);

        DataProcessorThreadVersion dataProcessorThreadVersion=new DataProcessorThreadVersion();
        dataProcessorThreadVersion.process(customers);
    }
}
