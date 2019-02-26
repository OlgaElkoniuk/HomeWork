package facades;

import java.util.ArrayList;
import java.util.Date;

import Exeptions.DataAlreadyExistExeption;
import Exeptions.LoginFailedException;
import Exeptions.PurchaseFailedException;
import JavaBeans.Categories;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerFacade extends ClientFacade {
	public boolean login(String email, String password) throws Exception {
		if (cusDAO.isCustomerExists(email, password)) {
			this.name = email;
			this.password = password;
			return true;
		}

		else
			throw new LoginFailedException(email + ", " + password);
	}

	public void purchaseCoupon(Coupon coupon) throws Exception {
		for (Coupon c : coupDAO.getCouponsByCustomerID(cusDAO.getCustomerByEmail(name).getId())) {
			if (c.toString().equals(coupon.toString())) {
				throw new DataAlreadyExistExeption(". Sory, you already have this coupon");
			}
			break;
		}
		if (coupDAO.getOneCoupon(coupon.getId()).getAmount() == 0)
			throw new PurchaseFailedException(
					"Available amount of coupons is: " + coupDAO.getOneCoupon(coupon.getId()).getAmount());
		String endDateString = coupDAO.getOneCoupon(coupon.getId()).getEndDate();
		if (Coupon.sdf.parse(endDateString).before(new Date()))
			throw new PurchaseFailedException(
					"This coupon's end date expired on " + coupDAO.getOneCoupon(coupon.getId()).getEndDate());

		coupDAO.addCouponPurchase(cusDAO.getCustomerByEmail(name).getId(), coupon.getId());
		coupon.setAmount(coupDAO.getOneCoupon(coupon.getId()).getAmount() - 1);
		coupDAO.updateCoupon(coupon);
	}

	public ArrayList<Coupon> getCustomerCoupons() throws Exception {
		return coupDAO.getCouponsByCustomerID(cusDAO.getCustomerByEmail(name).getId());
	}

	public ArrayList<Coupon> getCustomerCoupons(Categories category) throws Exception {
		ArrayList<Coupon> couponsCategoryFilter = new ArrayList<Coupon>();
		for (Coupon c : coupDAO.getCouponsByCustomerID(cusDAO.getCustomerByEmail(name).getId()))
			if (c.getCategory() == category)
				couponsCategoryFilter.add(c);
		if (couponsCategoryFilter.isEmpty()) {
			System.out.println("There is no coupons of category: " + category);
			return null;
		} else
			return couponsCategoryFilter;
	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws Exception {
		ArrayList<Coupon> couponsCategoryFilter = new ArrayList<Coupon>();
		for (Coupon c : coupDAO.getCouponsByCustomerID(cusDAO.getCustomerByEmail(name).getId()))
			if (c.getPrice() < maxPrice)
				couponsCategoryFilter.add(c);
		if (couponsCategoryFilter.isEmpty()) {
			System.out.println("There is no coupons cheaper than: " + maxPrice);
			return null;
		} else
			return couponsCategoryFilter;
	}

	public Customer getCustomerDetails() throws Exception {
		return cusDAO.getCustomerByEmail(name);
	}
}
