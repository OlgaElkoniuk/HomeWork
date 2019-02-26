package test;
import java.util.Date;

import DBDAO.CouponsDAO;
import DBDAO.CouponsDBDAO;
import JavaBeans.Coupon;


public class CouponExpirationDailyJob implements Runnable {
private CouponsDAO couponDAO = new CouponsDBDAO();
private volatile boolean quit=false;



	@Override
	public void run() {
		
			
			System.out.println("Starting Job");
		while(!quit) {	
			try {
				synchronized(couponDAO){
				for (Coupon c:couponDAO.getAllCoupons()) {
					String endDateString = c.getEndDate();
					if (Coupon.sdf.parse(endDateString).before(new Date()))
						couponDAO.deleteCoupon(c.getId());
				}
			}
				Thread.sleep(1000);
			} catch(Exception ex) {}
			
	
	}
					System.out.println("Ending Job");	
	}

	public void stopThread() {
		quit=true;
	}

}
