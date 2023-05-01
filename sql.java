package Projekt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class sql {

	private static Connection conn; 
	public static Connection connect() { 
	       conn= null; 
	       try {
	              conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");                       
	       } 
	      catch (SQLException e) { 
	            System.out.println(e.getMessage());
		    
	      }
		return conn;
	    
	      
	}

	public static Connection getConnection() {
		return conn;
	}  public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	public void createTable() {
        try {
            Statement stmt = conn.createStatement();
            String sqlMovies = "CREATE TABLE IF NOT EXISTS movies " +
                    "(id INTEGER PRIMARY KEY, " +
                    "druh VARCHAR(255), " +
                    "meno VARCHAR(255), " +
                    "reziser VARCHAR(255), " +
                    "rok INT, " +
                    "hodnotenie INT, " +
                    "comment VARCHAR(255), " +
                    "vek INT)";
            stmt.executeUpdate(sqlMovies);

            String sqlHerci = "CREATE TABLE IF NOT EXISTS herci " +
                    "(id INTEGER PRIMARY KEY, " +
                    "meno VARCHAR(255), " +
                    "herec VARCHAR(255))";
            stmt.executeUpdate(sqlHerci);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }}

	
