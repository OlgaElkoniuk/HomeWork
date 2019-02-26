package test;
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

public class Test {
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
	System.out.println("----------------------GET ONE COMPANY---------------------------");
	company1=myAdminFacade.getOneCompany(1);
	System.out.println(company1);
	System.out.println("----------------------GET ONE CUSTOMER---------------------------");
	customer1=(myAdminFacade.getOneCustomer(1));
	System.out.println(customer1);
	System.out.println("----------------------ALL COMPANIES---------------------------");
	System.out.println(myAdminFacade.getAllCompanies());
	System.out.println("----------------------ALL CUSTOMERS---------------------------");
	System.out.println(myAdminFacade.getAllCustomers());
	company1.setEmail("orange@gmail.com");company1.setPassword("c1234");
	myAdminFacade.updateCompany(company1, "apple@gmail.com", "a1234");
	customer1.setFirstName("Helen"); customer1.setSecondName("Rivers"); 
	customer1.setEmail("customup@gmail.com");customer1.setPassword("c5678");
	myAdminFacade.updateCustomer(customer1, "customer1@gmail.com", "b1234");
	myAdminFacade.deleteCompany(company1.getId());
	myAdminFacade.deleteCustomer(customer1.getId());
	//------------------COMPANY FACADE TEST---------------------
	CompanyFacade companyFacade = (CompanyFacade) logman.login("samsung@gmail.com", "a5678", ClientType.COMPANY);
	companyFacade.addCoupon(coupon2);
	companyFacade.addCoupon(coupon1);
	coupon2.setAmount(4);coupon2.setCategory(Categories.ENTERTAIMENT);
	coupon2.setDescription("Free clothes on sale");coupon2.setEndDate(new Date(2025-1900, 11+1,12));
	coupon2.setImage("image2");coupon2.setPrice(30.7);coupon2.setTitle("free clothes");
	companyFacade.updateCoupon(coupon2);
	System.out.println("----------------------ALL COMPANY'S COUPONS---------------------------");
	System.out.println(companyFacade.getCompanyCoupons());
	System.out.println("----------------------ALL COMPANY'S COUPONS MAX PRICE---------------------------");
	System.out.println(companyFacade.getCompanyCoupons(30));
	System.out.println("----------------------ALL COMPANY'S COUPONS BY CATEGORY---------------------------");
	System.out.println(companyFacade.getCompanyCoupons(Categories.ENTERTAIMENT));
	companyFacade.deleteCoupon(coupon2.getId());
	companyFacade.addCoupon(coupon2);
	System.out.println("----------------------COMPANY'S DETAILS---------------------------");
	System.out.println(companyFacade.getCompanyDetails());
	//------------------CUSTOMER FACADE TEST---------------------
	CustomerFacade customerFacade = (CustomerFacade) logman.login("customer2@gmail.com", "b5678", ClientType.CUSTOMER);
	customerFacade.purchaseCoupon(coupon1);
	customerFacade.purchaseCoupon(coupon2);
	System.out.println("----------------------ALL CUSTOMER'S COUPONS---------------------------");
	System.out.println(customerFacade.getCustomerCoupons());
	System.out.println("----------------------ALL CUSTOMER'S COUPONS BY CATEGORY---------------------------");
	System.out.println(customerFacade.getCustomerCoupons(Categories.HOME_STORE));
	System.out.println("----------------------ALL CUSTOMER'S COUPONS BY PRICE---------------------------");
	System.out.println(customerFacade.getCustomerCoupons(30));
	System.out.println("----------------------CUSTOMER'S DETAILS---------------------------");
	System.out.println(customerFacade.getCustomerDetails());
	//------------------JOB THREAD STOP----------------------
	job.stopThread();
	}
}
