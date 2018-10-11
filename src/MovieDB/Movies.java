package MovieDB;

import java.sql.*;

//have to include the sql jar in the external libraries
public class Movies {
	
	private String formattedName;
	private int formattedYear;
	
	public String getFormattedName () {
		
		return formattedName;
	}
	
	public void setFormattedName (String formattedName) {
		//removes all leading and trailing space also any double space inbetween words
		formattedName = formattedName.trim().replaceAll(" +", "");
		this.formattedName = formattedName;
	}
	
	
	public int getFormattedYear () {
		
		return formattedYear;
	}
	
	public void setFormattedYear (int formattedYear) {
		//because the user can add spaces to the year value and SQL treats it as a string i had to remove all space
		String format;
		Integer tmp = new Integer(formattedYear);
		format = tmp.toString().replaceAll("\\s", "");
		formattedYear = Integer.parseInt(format);
		
		this.formattedYear = formattedYear;
	}
	
	
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
		
		//this string updates the test TABLE. sets a value in the year column and id column where
		//the specified name exists
		String sql2 ="UPDATE test SET year =?," + "Name =?" + "WHERE ID =?";
		
		//this will capitalize the first letter of the name entered
		Character firstLetter =nameColumn.charAt(0);
		String stringFirstLetter = firstLetter.toString();
		String capitalizedFirstLetter =firstLetter.toString().toUpperCase();
		
		nameColumn = nameColumn.replaceFirst(stringFirstLetter,capitalizedFirstLetter );
		
		try{
			Connection conn = this.connect();
			PreparedStatement statement = conn.prepareStatement(sql2);
			
		
			
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
	public void insert(int id,String name, int year){
		//what is in parenthesis is the column name
		//test is the name of the DB
		String sql = "INSERT INTO TEST(ID,Name,year) VALUES(?,?,?)";
		
		try{
			Connection conn = this.connect();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//sets the corresponding parameters
			
			//the 1 means it corresponds to the first questionmark
			statement.setInt(1,id);
			statement.setString(2,name);
			statement.setInt(3,year);
			
			//actually pushes the update to SQL
			statement.executeUpdate();
			System.out.println("inserted new data into db\n" + "ID = " + id +"\nName = " + name + "\nYear = " +year);
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
	}
	
	public static void main (String[] args) {
		
		Movies test = new Movies();
		
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
		//test.update(209,30,"fuck");
		//test.insert(3,"oscar",1990);
		
		test.displayAllData();
		
		test.deleteData("oscar");
		
		test.displayAllData();
	}
	
	public void displayAllData(){
		//this will get all the id, name and year from the table
		String sql = "SELECT ID, Name,year FROM test";
		
		try {
			Connection conn = this.connect();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			//loops through the results
			while (rs.next()){
				//this will print out the values in the columns, can use the name of the column or the index of the column,
				//so in the test DB index 1 is ID column
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Name") + "\t" + rs.getInt("year"));
				//System.out.println("gets only name " + rs.getString("Name"));
				//System.out.println(rs.getString(2));
				
				//can use if statements to only get the data we are looking for
				//String name = "oscar";
				//if(rs.getString(2).equalsIgnoreCase(name)){
				//System.out.println(rs.getString(2));
				//}
				
			}
		}
		
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}//end display all data method
	
	public void deleteData (String name) {
		
		String sql = "DELETE FROM test WHERE Name=?";
		try {
			
			Connection connection = this.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//set what the parameters of the prepared statement are
			preparedStatement.setString(1, name);
			
			//actually delete whatever i put in for name
			preparedStatement.executeUpdate();
		}//end try
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}//end delete data
	
	
	
}//end Movies class
