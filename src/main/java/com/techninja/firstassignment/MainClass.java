package com.techninja.firstassignment;
import org.apache.log4j.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class PrintJob implements Runnable{
	
	private String location;
	private static Logger logger = Logger.getLogger(PrintJob.class);
	
	public PrintJob(String location) {
		
		this.location=location;
	}
	
	public void run() {
				//System.out.println(location+"..Job Started by Thread"+Thread.currentThread().getName());
				logger.info(location+"..Job Started by Thread"+Thread.currentThread().getName());
				MySQLLog(location+"..Job Started by Thread"+Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println(location+"..Job Completed by Thread"+Thread.currentThread().getName());
				logger.info(location+"..Job Started by Thread"+Thread.currentThread().getName());
				MySQLLog(location+"..completed by Thread"+Thread.currentThread().getName());
	}
	
	public void MySQLLog(String msg) {
		 String url = "jdbc:mysql://localhost:3306/MyJavaThreadLog?useSSL=false";
	     String user = "newuser";
	     String password = "password";
	     	    
	     try  {
	
	    	 Connection con = DriverManager.getConnection(url, user, password);
	         Statement st = con.createStatement();
	              
	         String query = "Insert into JavaThreadingLog (location,log) Values('" + location + "'" +","+ "'" + msg + "'" + ")";
	         System.out.println(query);
	         st.execute(query);
	         
	         logger.info("SQL:" +msg);
	         logger.info("SQL:" +query);
	     } catch (SQLException ex) {
	         	       
	         logger.info( ex.getMessage());
	     } 
	}
}

public class MainClass {

	
	public static void main(String[] args) {
				
		PrintJob [] jobs= { new PrintJob("United Kingdom"),
						new PrintJob("USA"),
						new PrintJob("India"),
						new PrintJob("Singapore"),		
						new PrintJob("Dubai"),
						new PrintJob("Brazil"),		
		};
		
		ExecutorService service=Executors.newFixedThreadPool(3);
		
		for(PrintJob job:jobs) {
		service.submit(job);
		}
		service.shutdown();

	}
}

