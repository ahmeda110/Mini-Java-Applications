package edu.ucalgary.ensf409;

/**
 * @author Ahmed Abbas<a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 * 
 * @version	1.3
 * @since	1.0
 */
 
import java.sql.*;
import java.util.*;

public class Registration{
	
	public final String USERNAME; 
    public final String PASSWORD; 
    public final String DBURL; 
	
	private Connection compConnect;
    private ResultSet results;
    
	/**
	 * @param dbUrl		url path
	 * @param username	username
	 * @param password	password
	 */
	public Registration(String DBURL, String USERNAME, String PASSWORD){
		 this.DBURL = DBURL;
		 this.USERNAME = USERNAME;
		 this.PASSWORD = PASSWORD;
	}
	
	/**
	 * @return USERNAME
	 */
	public String getUsername() { return this.USERNAME; }
	
	/**
	 * @return Password
	 */
	public String getPassword() { return this.PASSWORD; }
	
	/**
	 * @return DBURL
	 */
	public String getDburl() { return this.DBURL; }
	
	/**
	* Here we utilize DBURL, USERNAME, & PASSWORD to begin a connection with the database
	*/
	public void initializeConnection() {
		try { compConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Here we look into the database - copy all the elements from the given table then draw out and
	 * return the first and last names in a formatted manner
	 * 
	 * @param tableN	given table
	 * @return	A 		every name in a string type
	 */
	public String selectAllNames(String tableName) {
        StringBuffer names = new StringBuffer();
        
        try {                    
            Statement statement = compConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM " + tableName);
            
            while (results.next()){
                names.append(results.getString("LName") + ", " + results.getString("FName"));
                names.append('\n');
            }
        statement.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
        
        return names.toString().trim();
	}
	
	/**
	 * Here we look into the database - copy all the elements from the studios table then draw out and
	 * return the first and last names in a formatted manner
	 * 
	 * @return A every name in a string type
	 */
	public String showStudios() {
        StringBuffer names = new StringBuffer();
        
        try {                    
            Statement statement = compConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM STUDIO");
            
            while (results.next()){
                names.append(results.getString("Name"));
                names.append('\n');
            }  
        statement.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
        
        return names.toString().trim();
	}
	
	/**
	 * Here users can insert new competiors into the database (Prepared statement -> use java to create SQL commands)
	 *
	 * @param id
	 * @param lName
	 * @param fName
	 * @param age
	 * @param instrument
	 * @param teachName
	 * @throws IllegalArgumentException
	 */
	public void insertNewCompetitor(String id, String lName, String fName, int age, String instrument, String teachName) throws IllegalArgumentException {
		if (age < 5 || age > 18) { throw new IllegalArgumentException("Invalid competior age!"); }
		
        try{
            Statement statement = compConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM teacher");

            ArrayList<String> array = new ArrayList<String>();
            
            while(results.next()){ array.add(results.getString("TeacherID")); }
            if(!array.contains(teachName)){ throw new IllegalArgumentException("Teacher id not found in the database."); }
        }catch(SQLException e){ e.printStackTrace(); }
		
		try {
            String query = "INSERT INTO COMPETITOR (CompetitorID, LName, FName, Age, Instrument, TeacherID) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = compConnect.prepareStatement(query);
            
            statement.setString(1, id);
            statement.setString(2, lName);
            statement.setString(3, fName);
            statement.setInt(4, age);
            statement.setString(5, instrument);
            statement.setString(6, teachName);
            
            int rowCount = statement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            statement.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Here users can regiter new teachers into the database (Prepared statement -> use java to create SQL commands)
	 * 
	 * @param teachId
	 * @param lName
	 * @param fName
	 * @param phone
	 * @param studioName
	 * @param studioPhone
	 * @param address
	 */
	public void registerNewTeacher(String teachId, String lName, String fName, String phone, String studioName, String studioPhone, String address) {
		try{
            Statement statement = compConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM studio");
            ArrayList<String>array = new ArrayList<String>();

            while(results.next()){ array.add(results.getString("Name")); }
            if(!array.contains(studioName)){ insertNewStudio(studioName, studioPhone, address); }
        }catch(SQLException e){ e.printStackTrace(); }

        try{
            Statement statement = compConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM teacher");

            while(results.next()){
                if(results.getString("TeacherID").equals(teachId)){ throw new IllegalArgumentException("Teacher already registered."); }
            }
        }catch(SQLException e){ e.printStackTrace(); }	
	
		
		try {
            String query = "INSERT INTO TEACHER (TeacherID, LName, FName, Phone, StudioName) VALUES (?,?,?,?,?)";
            PreparedStatement statement = compConnect.prepareStatement(query);
            
            statement.setString(1, teachId);
            statement.setString(2, lName);
            statement.setString(3, fName);
            statement.setString(4, phone);
            statement.setString(5, studioName);
            
            int rowCount = statement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            statement.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Here users can add insert studios into the database (Prepared statement -> use java to create SQL commands)
	 * 
	 * @param studioName
	 * @param studioPhone
	 * @param address
	 */
	private void insertNewStudio( String studioName, String studioPhone, String address) {
		try {
	        
	        String query = "INSERT INTO STUDIO (Name, Phone, Address) VALUES (?,?,?)";
	        PreparedStatement statement = compConnect.prepareStatement(query);
	        
	        statement.setString(1, studioName);
	        statement.setString(2, studioPhone);
	        statement.setString(3, address);
	        
	        int rowCount = statement.executeUpdate();
	        System.out.println("Rows affected: " + rowCount);
	        
	        statement.close();

	    } catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Here users can delete competitors off the database (Prepared statement -> use java to create SQL commands)
	 * 
	 * @param id
	 */
	public void deleteCompetitor(String id) {
		try {
            String query = "DELETE FROM COMPETITOR WHERE CompetitorID = ?";
            PreparedStatement statement = compConnect.prepareStatement(query);

            statement.setString(1, id);
                        
            int rowCount = statement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            statement.close();
        } catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Here users can delete teachers off the database (Prepared statement -> use java to create SQL commands)
	 * 
	 * @param id
	 */
	public void deleteTeacher(String id) {
		try {
            String query = "DELETE FROM TEACHER WHERE TeacherID = ?";
            PreparedStatement statement = compConnect.prepareStatement(query);

            statement.setString(1, id);
                        
            int rowCount = statement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            statement.close();

        } catch (SQLException ex) { ex.printStackTrace(); }
	}
	
	/**
	 * Here we lose the connection
	 * 
	 */
	public void close() {
        try {
            results.close();
            compConnect.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
	
	public static void main(String[] args) {

		 Registration myJDBC = new Registration("jdbc:mysql://localhost/competition","ahmed","ensf409");
	        myJDBC.initializeConnection();
	        
	        System.out.println(myJDBC.selectAllNames("competitor"));
	        /*
	        Example:
	        Williams, Sophie
	        Warrayen, Harper
	        */
	                
	        System.out.println(myJDBC.selectAllNames("teacher"));
	        /*
	        Example:
	        Estrada, Ethan
	        Lee, Jasmine
	        */

	        System.out.println(myJDBC.showStudios());
	        /*
	        Example:
	        Harmony Inc.
	        Melody Time
	        Music Mastery
	        */
	        
	        myJDBC.insertNewCompetitor("123", "Smyth", "Ali", 15, "Oboe", "0023");
	        myJDBC.registerNewTeacher("0987", "Marasco", "Emily", "403-222-5656", "Marasco Music", "587-222-5656", "123 Main Street NW");        

	        myJDBC.deleteCompetitor("123");
	        myJDBC.deleteTeacher("0987");      
    }
}













