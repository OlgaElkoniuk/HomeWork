import java.util.Date;

import DBDAO.BasicDBDAO;
import JavaBeans.Categories;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import facades.AdminFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.LoginManager;

public class Test2 {
	//------------------DROP CREATE ALL TABLES AND FILL CATEGORY TABLE---------------------
	public void testAll() throws Exception {
	BasicDBDAO basicDBDAO=new BasicDBDAO();
	basicDBDAO.dropDB(BasicDBDAO.tableNameCustomersVSCoupons);
	basicDBDAO.dropDB(BasicDBDAO.tableNameCoupons);
	basicDBDAO.dropDB(BasicDBDAO.tableNameCategories);
	basicDBDAO.dropDB(BasicDBDAO.tableNameCustomers);
	basicDBDAO.dropDB(BasicDBDAO.tableNameCompanies);
	basicDBDAO.buildDB(basicDBDAO.getCategorySQL());
	basicDBDAO.buildDB(basicDBDAO.getCompanySQL());
	basicDBDAO.buildDB(basicDBDAO.getCustomerSQL());
	basicDBDAO.buildDB(basicDBDAO.getCouponSQL());
	basicDBDAO.buildDB(basicDBDAO.getCustomersVScouponsSQL());
	for (String category:Categories.categoriesList()) {
		basicDBDAO.addCategory(category);
	}
	
	//------------------CREATE ALL NECESSARY INSTANCES---------------------
	Company company1 = 	new Company("apple", "apple@gmail.com", "a1234");
	Company company2 = 	new Company("samsung", "samsung@gmail.com", "a5678");
	Customer customer1 = new Customer("customer1@gmail.com", "b1234", "Bob", "Smith");
	Customer customer2 = new Customer("customer2@gmail.com", "b5678", "Alice", "Rogers");
	Coupon coupon1 = new Coupon(2, Categories.BOOKS, "Free books", "You can get free books on a book sale", 
			new Date(2019-1900, 5+1,22), new Date(2019-1900, 6+1,22), 5, 27.5, "image");
	Coupon coupon2 = new Coupon(2, Categories.CLOTHES, "Free shoes", "You can get free shoes on a shoes sale", 
			new Date(2019-1900, 5+1,22), new Date(2019-1900, 6+1,22), 5, 27.5, "image");
	LoginManager logman = LoginManager.getInstance();
	//------------------JOB THREAD START----------------------
	CouponExpirationDailyJob job = new CouponExpirationDailyJob();
	Thread t1 = new Thread(job);
	t1.start();
	//------------------ADMIN FACADE TEST---------------------
	AdminFacade myAdminFacade = (AdminFacade) logman.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
	myAdminFacade.addCompany(company1);
	myAdminFacade.addCustomer(customer1);
	myAdminFacade.addCompany(company2);
	myAdminFacade.addCustomer(customer2);
	

	//------------------COMPANY FACADE TEST---------------------
	CompanyFacade companyFacade = (CompanyFacade) logman.login("samsung@gmail.com", "a5678", ClientType.COMPANY);
	companyFacade.addCoupon(coupon2);
	companyFacade.addCoupon(coupon1);


	
	CustomerFacade customerFacade = (CustomerFacade) logman.login("customer2@gmail.com", "b5678", ClientType.CUSTOMER);
	customerFacade.purchaseCoupon(coupon1);
	customerFacade.purchaseCoupon(coupon2);
	
	
	
//	companyFacade.deleteCoupon(coupon2.getId());
//	myAdminFacade.deleteCompany(company2.getId());
	myAdminFacade.deleteCustomer(customer2.getId());
	job.stopThread();
	}
}
