package dao;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Employee;
import model.Movie;
import java.sql.*;

public class MovieDao {

	
	public List<Movie> getMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Movie");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	public Movie getMovie(String movieID) {

		/*
		 * The students code to fetch data from the database based on "movieID" will be written here
		 * movieID, which is the Movie's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Movie" class object
		 */

		Movie movie = new Movie();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Movie where Id = '" + movieID + "'");
			while(rs.next()) {
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				System.out.println(movie.getMovieID());
				return movie;
			
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

		/*Sample data ends*/
		
		return movie;
	}
	
	public String addMovie(Movie movie) {

		/*
		 * All the values of the add movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the movie details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from Movie");
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
				count++;
			}
			String sql = "INSERT INTO Movie " + "VALUES (" + count + ", '"+movie.getMovieName()+"', '"+movie.getMovieType()+"', "+movie.getRating()+", "+movie.getDistFee()+", "+movie.getNumCopies()+")";
			st.executeUpdate(sql);
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}

	}
	
	public String editMovie(Movie movie) {
		/*
		 * All the values of the edit movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Movie class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteMovie(String movieID) {
		/*
		 * movieID, which is the Movie's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}
	
	
	public List<Movie> getBestsellerMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of the bestseller movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		
		/*Sample data begins*/
		for (int i = 0; i < 5; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

	public List<Movie> getSummaryListing(String searchKeyword) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of summary listing of revenue generated by a particular movie or movie type must be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * Store the revenue generated by an movie in the soldPrice attribute, using setSoldPrice method of each "movie" object
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	
	

	public List<Movie> getMovieSuggestions(String customerID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie suggestions for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom the movie suggestions are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	public List<Movie> getCurrentMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch currently hold movie for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom currently hold movie are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	
public List<Movie> getQueueOfMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie queue for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom movie queue are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	


	public List<Movie> getMovieTypes() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * A query to fetch the unique movie types has to be implemented
		 * Each movie type is to be added to the "movie" object using setType method
		 */
		
		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct MovieType from Movie");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieType(rs.getString("MovieType"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return movies;
	}

	public List getMoviesByName(String movieName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM (Movie INNER JOIN AppearedIn ON (Id=MovieId)) "
					+ "INNER JOIN Actor ON (ActorId=Actor.Id) "
					+ "WHERE MovieName like \'%" + movieName + "%\' OR ActorName like \'%" + movieName + "%\'");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}

		/*Sample data ends*/
		
		return movies;
	}
	
	public List getMoviesByActor(String actorName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The matrix");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List getMoviesByType(String movieType) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieType, which is the movie's type on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieType as their type has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Movie where MovieType = '" +movieType+ "'");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		

		
		return movies;
	}
	
	public List getMovieRentalsByName(String movieName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Movie where MovieName ='" +movieName+ "'");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		

		
		return movies;
	}
	
	public List getMovieRentalsByCustomer(String customerName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List getMovieRentalsByType(String movieType) {
		
		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql3.cs.stonybrook.edu:3306/agargueta?user=agargueta", "agargueta", "111456257");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Movie where Movie.MovieType = '" +movieType+ "'");
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setRating(rs.getInt("Rating"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("MovieName"));
				movie.setMovieType(rs.getString("MovieType"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movies.add(movie);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List<Movie> getBestsellersForCustomer(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList.
		 * Query to get the Best-seller list of movies for a particular customer, indicated by the customerID, has to be implemented
		 * The customerID, which is the customer's ID for whom the Best-seller movies have to be fetched, is given as method parameter
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

}
