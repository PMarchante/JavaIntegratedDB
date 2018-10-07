import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//have to include the sql jar in the external libraries
public class MovieList {
	
	private Connection connect(){
		String url = "jdbc:sqlite:C:\\Users\\DrunkCow\\OneDrive\\Java\\IntelliJ Projects\\JavaIntegratedDB\\database\\db.sqlite";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		}
		
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
		
		return conn;
	}//end sql connection
	
	public void update(int newYear, int newID,String nameColumn ){
	//UPDATE (db name) SET (what you want to change)   WHERE(the identifier of what you cant to change. could be
			//the id or the name anything that is unique)
		
		//here i am changing the value in the year column depending on the name column
		//String sql = "UPDATE test SET year =?"  + "WHERE Name =?";
		
		//this string updates the test TABLE sets a value in the year column and id column where
		//the specified name column
		String sql2 ="UPDATE test SET year =?," + "Name =?" + "WHERE ID =?";
		
		//this will capitalize the first letter of the name entered
		Character firstLetter =nameColumn.charAt(0);
		String stringFirstLetter = firstLetter.toString();
		String capitalizedFirstLetter =firstLetter.toString().toUpperCase();
		
		nameColumn = nameColumn.replaceFirst(stringFirstLetter,capitalizedFirstLetter );
		
		try{
			Connection conn = this.connect();
			PreparedStatement statement = conn.prepareStatement(sql2);
			
			//sets the corresponding parameters
			//statement.setInt(1,year2);
			//statement.setInt(1, newYear);
			//statement.setString(2, nameColumn);
			//statement.setInt(4, criticRating);
			
			//sets the parameters when using string SQL2
			statement.setInt(1,newYear);
			statement.setString(2,nameColumn);
			statement.setInt(3,newID);
			//actually pushes the update to SQL
			statement.executeUpdate();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
	}
	
	
	//insert new row into the database
	public void insert(String name, int year){
		//what is in parenthesis is the column name
		//test is the name of the DB
		String sql = "INSERT INTO TEST(Name,year) VALUES(?,?)";
		
		try{
			Connection conn = this.connect();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//sets the corresponding parameters
			
			//the 1 means it corresponds to the first questionmark
			statement.setString(1,name);
			statement.setInt(2,year);
			
			//actually pushes the update to SQL
			statement.executeUpdate();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
	}
	
	
	public static void main (String[] args) {
	MovieList test = new MovieList();
	
	//test.insert("fuck my ass",2018);
		//
		////update using string sql
		////test.update(202,"omfg");
		////test.update(0,"shit");
		//
		/*
		//update using string sql2
		//test.update(20,40,"pedro");
		*/
		
		
		//this update will fail because the ID column is a primary key and MUST be unique
		//the id of 6 already exists
		test.update(2019,30,"pedro");
		
	}
	
	
	
}//end movieList
