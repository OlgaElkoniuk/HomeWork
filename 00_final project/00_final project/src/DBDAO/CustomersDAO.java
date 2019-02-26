package DBDAO;
import java.util.ArrayList;

import JavaBeans.Customer;


public interface CustomersDAO {
	 void addCustomer(Customer customer) throws Exception;
	    
	    void updateCustomer(Customer customer, String oldEmail, String oldPassword) throws Exception;   
	    Customer getCustomerByEmail(String email) throws Exception;    
		ArrayList<Customer> getAllCustomers() throws Exception;
	    Customer getOneCustomer(int customerID) throws Exception;
	    boolean isCustomerExists(String email, String password) throws Exception;

	    void deleteCustomer(int customerID) throws Exception;
}
