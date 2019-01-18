import java.util.ArrayList;


public interface CustomersDAO {
	 void addCustomer(Customer customer) throws Exception;
	    
	    void updateCustomer(Customer customer) throws Exception;   
	        
		ArrayList<Customer> getAllCustomers() throws Exception;
	    Customer getOneCustomer(int customerID) throws Exception;
	    boolean isCustomerExists(String email, String password) throws Exception;

	    void deleteCustomer(int customerID) throws Exception;
}
