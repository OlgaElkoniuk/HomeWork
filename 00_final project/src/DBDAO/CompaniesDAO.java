package DBDAO;
import java.util.ArrayList;

import JavaBeans.Company;


public interface CompaniesDAO {
	 void addCompany(Company company) throws Exception;
	    
	    void updateCompany(Company company, String oldEmail, String oldPassword) throws Exception;   
	        
		ArrayList<Company> getAllCompanies() throws Exception;
	    Company getOneCompany(int companyID) throws Exception;
	    boolean isCompanyExists(String email, String password) throws Exception;
	    Company getCompanyByEmail(String email)throws Exception;
	    void deleteCompany(int companyID) throws Exception;

}
