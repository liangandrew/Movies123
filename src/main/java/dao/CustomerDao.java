package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Customer");
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("Id"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCard"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		/*Sample data ends*/
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		/*Sample data begins*/
		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			String query = "SELECT MIN(Account.DateOpened) FROM (Customer INNER JOIN Account ON (Customer.Id=Account.CustomerId)) ";
			
			ResultSet r = st.executeQuery(query);
			Date date = new Date(0,0,0);
			if(r.next()) {
				date = r.getDate(1);
			}
			System.out.println(date);
			
			// query customer and account where account creation date matches the date
			String sql = "SELECT Customer.Id, LastName, FirstName, Email FROM (Customer INNER JOIN Account ON (Customer.Id=Account.CustomerId)) "
					+ "WHERE Account.DateOpened = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customer.setCustomerID(rs.getString("Id"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setEmail(rs.getString("Email"));
			}	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Customer");
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("Id"));
				customer.setAddress(rs.getString("Address"));
				customer.setState(rs.getString("State"));
				customer.setCity(rs.getString("City"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setEmail(rs.getString("Email"));
				customers.add(customer);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		/*Sample data begins*/
		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Customer where Id = '"+customerID+"'");		
			while(rs.next()) {
				customer.setCustomerID(rs.getString("Id"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCard"));
				customer.setRating(rs.getInt("Rating"));
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			String sql = "DELETE FROM Customer where Customer.Id = '" +customerID+"' ";
			st.executeUpdate(sql);
			return "success";
			
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data ends*/
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */

		String customerID = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Customer where Email = '"+ username +"'");
			while(rs.next())
				customerID= (rs.getString("Id"));
				System.out.println(customerID);
			return customerID;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return customerID;
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setCustomerID("111-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);			
		}
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			
			String sql = "INSERT INTO Customer " + "VALUES ('" + customer.getCustomerID() + "', '"+customer.getEmail()+"', '"+customer.getRating()+"', '"+
					customer.getCreditCard()+"', '"+customer.getFirstName()+"', '"+customer.getLastName()+"', '"+customer.getCity()+"'"
					+ ", '"+customer.getZipCode()+"', '"+customer.getTelephone()+"', '"+customer.getState()+"', '"+customer.getAddress()+"')";
			st.executeUpdate(sql);
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		try {
			//System.out.println(movie.getMovieName());
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agar"
					+ "gueta", "111456257");
			Statement st = con.createStatement();
			//int Id = movie.getMovieID();
			String sql = "UPDATE Customer " + "SET FirstName ='"+customer.getFirstName()+"', LastName='"+customer.getLastName()+"', Rating="+customer.getRating()+", Email='"+customer.getEmail()+"', CreditCard='"+customer.getCreditCard()+"'"
					+ ", City='"+customer.getCity()+"', ZipCode='"+customer.getZipCode()+"', Telephone='"+customer.getTelephone()+"', State='"+customer.getState()+"', Address='"+customer.getAddress()+"' "
					+ "WHERE Id ='"+customer.getCustomerID() +"' ";
			st.executeUpdate(sql);
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data ends*/

	}

}
