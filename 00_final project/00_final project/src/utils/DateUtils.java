
package utils;

public class DateUtils {

	public static java.sql.Date javaDateToSqlDate(java.util.Date javaDate) {
		return new java.sql.Date(javaDate.getTime());
	}
}
