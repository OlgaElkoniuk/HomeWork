package facades;
import DBDAO.CompaniesDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDAO;
import DBDAO.CustomersDBDAO;

public abstract class ClientFacade {
	CompaniesDAO comDAO=new CompaniesDBDAO();
	CustomersDAO cusDAO=new CustomersDBDAO();
	CouponsDAO coupDAO=new CouponsDBDAO();
	
	
	String name;
	String password;
public boolean login(String email, String password) throws Exception {
	
	//check in DB if user is ok
	
	
	//if is not ok --> throw new Exception();
	
	//if is ok

	return true;
};

}
