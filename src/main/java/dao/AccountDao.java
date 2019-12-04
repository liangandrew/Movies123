package dao;

import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Movie;
import model.Account;
import java.sql.*;

public class AccountDao {
	
	public int getSalesReport(Account account) {
			
			/*
			 * The students code to fetch data from the database will be written here
			 * Query to get sales report for a particular month must be implemented
			 * account, which has details about the month and year for which the sales report is to be generated, is given as method parameter
			 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the dateOpened attribute of account object
			 * The month and year can be accessed by getter method, i.e., account.getAcctCreateDate()
			 */
	
			int income = 0;
					
			/*Sample data begins*/
			//Get the date from account, then go through all accounts where the creation date is before that 
			String date = account.getAcctCreateDate();	//in format of month-year
			String[] parts = date.split("-");
			String month = parts[0];
			String year = parts[1];
			String day = "30";
			date = year + "-" + month + "-" + day;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");			Statement st = con.createStatement();
				String query = "SELECT SUM(totalSales) FROM("
						+ "(SELECT COUNT(*)*10 AS totalSales FROM Account WHERE DateOpened < ? AND AccountType='Limited')";
				query += "UNION ALL"
						+ "(SELECT COUNT(*)*15 AS t1 FROM Account WHERE DateOpened < ? AND AccountType='Unlimited-1')";
				query += "UNION ALL"
						+ "(SELECT COUNT(*)*20 AS t2 FROM Account WHERE DateOpened < ? AND AccountType='Unlimited-2')";
				query += "UNION ALL"
						+ "(SELECT COUNT(*)*25 AS t3 FROM Account WHERE DateOpened < ? AND AccountType='Unlimited-3')";
				query += ")total";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, date);
				ps.setString(2, date);
				ps.setString(3, date);
				ps.setString(4, date);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int j =rs.getInt("SUM(totalSales)");
					income = j;
					
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			/*Sample data ends*/
			
	
			return income;
			
		}
	
	public String setAccount(String customerID, String accountType) {

		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			customerID = customerID.replaceAll("-","");
			String query = "UPDATE Account "
					+ "SET AccountType = '"+accountType+"'"
					+ " WHERE CustomerId= '"+customerID+"'";
			st.executeUpdate(query);
			return "success";
		} catch (Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data ends*/

	}
	

}
