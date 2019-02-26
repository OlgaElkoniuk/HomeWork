import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Exeptions.InvalidAmountOfCharactersException;
import Exeptions.InvalidDateException;
import Exeptions.InvalidEmailException;
import Exeptions.InvalidNumberException;
import JavaBeans.Categories;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import facades.LoginManager;

public class App {


	
	//-----------------MAIN---------------------------------------------
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
	
Test2 test = new Test2();
test.testAll();

	}

}