package facades;
import java.util.ArrayList;

import Exeptions.DataAlreadyExistExeption;
import Exeptions.DataDoesNotExist;
import Exeptions.LoginFailedException;
import JavaBeans.Categories;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyFacade extends ClientFacade{
public boolean login(String email, String password) throws Exception {
	if (comDAO.isCompanyExists(email, password)) {
	this.name=email;
	this.password=password;
	return true;
	}
	else throw new LoginFailedException(email+", "+password);
}
public void addCoupon(Coupon coupon) throws Exception {
	if (coupon.getCompany_id()==comDAO.getCompanyByEmail(name).getId()) {
	for (Coupon c:coupDAO.getCouponsByCompanyID(comDAO.getCompanyByEmail(name).getId()))
		if(coupon.getTitle()==c.getTitle())
			throw new DataAlreadyExistExeption("Coupon with title: "+coupon.getTitle()+" already exists");
	coupDAO.addCoupon(coupon);
	}else throw new DataDoesNotExist("You entered wrong company id number. The id of your company is: "+comDAO.getCompanyByEmail(name).getId());
}
public void updateCoupon(Coupon coupon) throws Exception {
	coupDAO.updateCoupon(coupon);
}
public void deleteCoupon(int couponID) throws Exception {
	coupDAO.deleteCoupon(couponID);
}
public ArrayList<Coupon> getCompanyCoupons() throws Exception{

return coupDAO.getCouponsByCompanyID(comDAO.getCompanyByEmail(name).getId());
}
public ArrayList<Coupon> getCompanyCoupons(Categories category) throws Exception{
	ArrayList<Coupon> couponsCategoryFilter = new ArrayList<Coupon>();
	for (Coupon c:coupDAO.getCouponsByCompanyID(comDAO.getCompanyByEmail(name).getId())) {
		if (c.getCategory()==category)
			couponsCategoryFilter.add(c);
	}
	if (couponsCategoryFilter.isEmpty()) 
		throw new DataDoesNotExist("There is no coupons of category: "+category);
		else return couponsCategoryFilter;
	 
}
public ArrayList<Coupon> getCompanyCoupons( double maxPrice) throws Exception{
	ArrayList<Coupon> couponsCategoryFilter = new ArrayList<Coupon>();
	for (Coupon c:coupDAO.getCouponsByCompanyID(comDAO.getCompanyByEmail(name).getId())) {
		if (c.getPrice()<maxPrice)
			couponsCategoryFilter.add(c);
	}
	if (couponsCategoryFilter.isEmpty())
	throw new DataDoesNotExist("There is no coupons cheaper than: "+maxPrice);
	else return couponsCategoryFilter;
}
public Company getCompanyDetails() throws Exception {
	return comDAO.getCompanyByEmail(name);
}
}
