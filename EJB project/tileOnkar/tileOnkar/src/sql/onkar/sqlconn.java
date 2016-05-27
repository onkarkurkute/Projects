package sql.onkar;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import bean.onkar.SurveyModel;

public class sqlconn {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "1234";
   
   
   public static ArrayList<SurveyModel> getStudentSurveyRecords(SurveyModel searchBean) {
	   Connection conn = null;
	   Statement stmt = null;
	   ArrayList<SurveyModel> studentRecordList=new  ArrayList();
	   try{
			
			
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * from   test.studentsurvey";
	      if(searchBean.getFirst_Name()==null)
	      {
	    	  System.out.println("Inside Test statement...");
	    		  
	      }
	      else
	      {
	      String whereCluase="";
	      if(searchBean !=null)
	      {
	    	  String s1="",s2="",s3="",s4="";
	    	  if(searchBean.getFirst_Name().isEmpty()==false && searchBean.getFirst_Name()!=null)
	    	  {
	    		  String s=searchBean.getFirst_Name();
	    		  if(s.indexOf("*") > 0)
	    		  {
	    			  s=  s.replace("*", "%");
	    			  s1="  first_name     LIKE '"+s+"%' ";
	    		  }
	    		  else
	    			  s1="  first_name ='"+s+"'";
	    		  System.out.println("s1 first_name "+s1);
	    	  }
	    	  if(searchBean.getLast_Name().isEmpty()==false  && searchBean.getLast_Name()!=null)
	    	  {
	    		  String s=searchBean.getLast_Name();
	    		  if(s.indexOf("*") > 0)
	    		  {
	    			  s=  s.replace("*", "%");
	    		     s2="   last_name     LIKE '"+s+"%' ";
	    		  }
	    		  else
	    			 s2=" last_name ='"+s+"'";
	    		  System.out.println("s2 lastname "+s2);
	    	  }
	    	  if(searchBean.getCity().isEmpty()==false && searchBean.getCity()!=null)
	    	  {
	    		  String s=searchBean.getCity();
	    		  if(s.indexOf("*") > 0)
	    		  {
	    			  s=  s.replace("*", "%");
	    		  s3="   city     LIKE '"+s+"%' ";
	    		  }
	    		  else
	    			  s3="  city ='"+s+"'";
	    		  System.out.println("s3 city "+s3);
	    	  }
	    	  if(searchBean.getState().isEmpty()==false && searchBean.getState()!=null)
	    	  {
	    		  String s=searchBean.getState();
	    		  if(s.indexOf("*") > 0)
	    		  {
	    			  s=  s.replace("*", "%");
	    		     s4="   STATE     LIKE '"+s+"%' ";
	    		  }
	    		  else
	    			  s4="  STATE ='"+s+"'";
	    		  System.out.println("s4 STATE "+s4);
	    	  }
	    	  if(s1.isEmpty()==false)
	    	  {
	    		  whereCluase=" where " +s1;
	    		  if(s2.isEmpty()==false)
	    			  whereCluase= whereCluase +" and  " +s2;
	    		  if(s3.isEmpty()==false)
	    			  whereCluase= whereCluase+" and  " +s3;
	    		  if(s4.isEmpty()==false)
	    			  whereCluase=whereCluase+ " and  " +s4;
	    		  System.out.println(" if(s1!= "+whereCluase);
	    	  }
	    	  else if (s2.isEmpty()==false)
	    	  {
	    		  
	    		  whereCluase=" where " +s2;
	    		 
	    		  if(s3.isEmpty()==false)
	    			  whereCluase= whereCluase + " and  " +s3;
	    		  if(s4.isEmpty()==false)
	    			  whereCluase= whereCluase +  " and  " +s4;
	    		  System.out.println("else if (s2 "+whereCluase);
	    	  }
	    	  else if (s3.isEmpty()==false)
	    	  {
	    		  
	    		  whereCluase=" where " +s3;
	    		 
	    		  if(s4.isEmpty()==false)
	    			  whereCluase= whereCluase +  " and  " +s4;
	    		  System.out.println( "else if (s3! "+whereCluase);
	    	  }
	    	  else if(s4.isEmpty()==false)
	    	  {
    			  whereCluase= " where  " +s4;
    			  System.out.println( " else if(s4! "+whereCluase);
	    	  }
	    	  
	      }
	      sql=sql+whereCluase;
	      System.out.println("Testing :"+sql);
	      }
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         //int id  = rs.getInt("student_id");first_name,last_name ,city ,STATE,zip_code "+
              
	    	  SurveyModel record=new SurveyModel();
	    	  record.setFirst_Name(rs.getString("first_name"));
	    	  record.setLast_Name(rs.getString("last_name"));
	    	  record.setCity(rs.getString("city"));
	    	  record.setState(rs.getString("STATE"));
	    	  record.setZip(rs.getString("zip_code"));
	    	  record.setEmail(rs.getString("email_id"));
	    	  record.setInterest(rs.getString("fav_thing"));
	    	  record.setNo(rs.getString("phone_no"));
	    	  studentRecordList.add(record);
	    	  //recor(rs.getString("survey_date"));
			
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	      //return studentRecordList;
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	 
	   System.out.println("Goodbye!");
	   return  studentRecordList;
	}//end main
   
   public static void insertStudentSurvey(SurveyModel bean) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      java.util.Date date = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(date.getTime());        
   
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "INSERT INTO test.studentsurvey "+
                " ( first_name,last_name ,city ,STATE,zip_code,email_id "+
                 " ,fav_thing ,phone_no,survey_date )"+
                " VALUES (   "+  "'"+bean.getFirst_Name()+"',"+ "'"+bean.getLast_Name()+"',"+ 
                 "'"+bean.getCity()+"'," +  "'"+bean.getState()+"',"+  "'"+bean.getZip()+"',"+"'"+bean.getEmail()+"',"
        +  "'"+bean.getInterest()+"',"+  "'"+bean.getNo()+"',"+  "'"+sqlDate.toString()+"')";
      stmt.executeUpdate(sql);
         
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample