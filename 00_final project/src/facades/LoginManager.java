package facades;

public class LoginManager {
	
	private static LoginManager instance = new LoginManager();
	private LoginManager() {
		super();
	}
	public static LoginManager getInstance() {
		return instance;
	}
public ClientFacade login (String email, String password, ClientType clientType) throws Exception {
	switch (clientType) {
	case ADMINISTRATOR: {
			AdminFacade adminfacade = new AdminFacade();
			if(adminfacade.login(email, password)) 
			return adminfacade;	
		}
	case COMPANY: {
		CompanyFacade companyFacade = new CompanyFacade();
		if(companyFacade.login(email, password))
		return companyFacade;	
	}
	case CUSTOMER: {
		CustomerFacade customerFacade = new CustomerFacade();
		if(customerFacade.login(email, password)) 
			return customerFacade;
	}
	}
	return null;
}
}
