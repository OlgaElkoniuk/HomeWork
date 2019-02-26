package facades;
import java.util.ArrayList;

import Exeptions.DataAlreadyExistExeption;
import Exeptions.DataDoesNotExist;
import Exeptions.LoginFailedException;
import JavaBeans.Company;
import JavaBeans.Customer;

public class AdminFacade extends ClientFacade{
	private final String adminEmail = "admin@admin.com";
	private final String adminPassword = "admin";
	public void addCompany (Company company) throws Exception {
		for (Company c:comDAO.getAllCompanies()) {
			if (c.getName()==company.getName())
				throw new DataAlreadyExistExeption("Company with name: "+company.getName()+" already exists");
			break;
		}
		comDAO.addCompany(company);
	}
	public void updateCompany (Company company, String oldEmail, String oldPassword) throws Exception {
		if (company.getName().equals(comDAO.getCompanyByEmail(oldEmail).getName())) {
		comDAO.updateCompany(company, oldEmail, oldPassword);
		}
		else throw new DataDoesNotExist("You've entered invalid company name: "+company.getName()+
				" you can't change company name.");
	}
	public void deleteCompany (int companyID) throws Exception {
		comDAO.deleteCompany(companyID);
	}
	public ArrayList<Company> getAllCompanies () throws Exception {
		return comDAO.getAllCompanies();
	}
	public Company getOneCompany (int companyID) throws Exception {
		return comDAO.getOneCompany(companyID);
	}
	public void addCustomer (Customer customer) throws Exception {
		cusDAO.addCustomer(customer);
	}
	public void updateCustomer(Customer customer, String oldEmail, String oldPassword) throws Exception {
		cusDAO.updateCustomer(customer, oldEmail, oldPassword);
	}
	public void deleteCustomer(int customerID) throws Exception {
		cusDAO.deleteCustomer(customerID);
	}
	public ArrayList<Customer> getAllCustomers() throws Exception{
		return cusDAO.getAllCustomers();
	}
	public Customer getOneCustomer(int customerID) throws Exception {
		return cusDAO.getOneCustomer(customerID);
		
	}
	public boolean login(String email, String password) throws Exception {
		if (email.equals(adminEmail)&&password.equals(adminPassword)) {
		//check in DB if user is ok
		//if is not ok --> throw new Exception();
		//if is ok
		this.name=email;
		this.password=password;
		return true;
		}else throw new LoginFailedException(email+", "+password);
	}

}
