package DBDAO;
import java.util.ArrayList;

import JavaBeans.Coupon;


public interface CouponsDAO {
	
	 void addCoupon(Coupon coupon) throws Exception;
	    
	    void updateCoupon(Coupon coupon) throws Exception;   
	        
		ArrayList<Coupon> getAllCoupons() throws Exception;
		Coupon getOneCoupon(int couponID) throws Exception;
	    boolean isCouponExists(String title, String description) throws Exception;

	    void deleteCoupon(int couponID) throws Exception;
	    ArrayList<Coupon> getCouponsByCompanyID(int companyID) throws Exception;
	    ArrayList<Coupon> getCouponsByCustomerID(int customerID) throws Exception;
	    void addCouponPurchase(int customerID, int couponID) throws Exception;
	    void deleteCouponPurchase(int customerID, int couponID) throws Exception;


}
