package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
	
	public List<Rental> getOrderHisroty(String customerID) {
		
		List<Rental> rentals = new ArrayList<Rental>();
			
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");			
			Statement st = con.createStatement();
			String query = "SELECT AccoutId, OrderId, MovieId, CustRepId"
					+ "FROM Customer, Account, Rental"
					+ "WHERE Customer.Id=? AND Customer.Id=Account.CustomerId AND Account.Id=Rental.AccountId";	
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Rental rental = new Rental();
				rental.setOrderID(rs.getInt("OrderId"));
				int r = rental.getOrderID();
				System.out.println(r);
				rental.setMovieID(rs.getInt("MovieId"));
				rental.setCustomerRepID(rs.getInt("CustRepId"));
				
				rentals.add(rental);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
//		for (int i = 0; i < 4; i++) {
//			Rental rental = new Rental();
//			
//			rental.setOrderID(1);
//			rental.setMovieID(1);
//			rental.setCustomerRepID(1);
//		
//			rentals.add(rental);
//			
//			
//		}
		/*Sample data ends*/
						
		return rentals;
		
	}

}
