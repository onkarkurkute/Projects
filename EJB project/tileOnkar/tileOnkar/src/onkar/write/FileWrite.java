package onkar.write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import bean.onkar.SurveyModel;
import bean.onkar.studentbeanEJB;
import bean.onkar.studentbeanEJBRemote;
import onkar.model.*;

public class FileWrite {
	
	public void saveSurvey(SurveyModel student) throws IOException, NamingException
	{
		getInitialContext();
		studentbeanEJBRemote bean =(studentbeanEJBRemote)context.lookup(name);
		bean.saveStudentRecord(student);
		
	}
	
	public ArrayList<bean.onkar.SurveyModel>   getSurvey(SurveyModel inputBean) throws IOException,NamingException
	{
		getInitialContext();
		 ArrayList<SurveyModel> studentRecordList=new  ArrayList<SurveyModel> ();
		    studentbeanEJBRemote bean =(studentbeanEJBRemote)context.lookup(name);
	      	//Students_Records_EJBRemote bean = doLookup();
	    	       // Student_Survey_Bean test=new Student_Survey_Bean();
	    	       // test.setFirstName("Sent From Server ::");
	    	        System.out.println("Testing EJB ");
	                 System.out.println(bean.getEchoString("Fuck yeah"));
	                 studentRecordList=  bean.getStudentRecords(inputBean);

			    return studentRecordList;
	
	}
	private static  Context context =null;
	private static String name ="";
	public static Context getInitialContext() throws NamingException
	{
    	   System.out.println("Lookup started: " );
    	   final Hashtable jndiProps = new Hashtable();
    	 jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	    context   = new InitialContext(jndiProps);
    	    System.out.println("Context  Initialized: " );
    	   String appName = "Assignment3EAR";    		 
    	    String moduleName = "OnkarEJB";
    	    String distinctName = "";
    	    String beanName = studentbeanEJB.class.getSimpleName();
    	    final String interfaceName = studentbeanEJBRemote.class.getName();
    	      // Create a look up string name
    	    name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
    	    System.out.println("name :  "+name);
    	    return context;    	        
		
	}
	
	public void saveStudentSurvey(SurveyModel student) throws IOException,NamingException
	{
		getInitialContext();
	    studentbeanEJBRemote bean =(studentbeanEJBRemote)context.lookup(name);
		bean.saveStudentRecord(student);
		
	}
	public ArrayList<SurveyModel>   getStudentSurveys(SurveyModel inputBean) throws IOException,NamingException
	{
		
	    getInitialContext();
	    ArrayList<SurveyModel> studentRecordList=new  ArrayList<SurveyModel> ();
	    studentbeanEJBRemote bean =(studentbeanEJBRemote)context.lookup(name);
      	         studentRecordList=  bean.getStudentRecords(inputBean);

		    return studentRecordList;
		
	}

}
