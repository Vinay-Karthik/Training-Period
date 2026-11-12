import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public List<Customer> generateCustomers(int count){
        List<Customer> customerList = new ArrayList<>();
        for(int i=1;i<=count;i++){
            customerList.add(new Customer(i,"user"+i,"user"+i+"@gmail.com"));
        }
        return customerList;
    }
}
