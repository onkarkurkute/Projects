package onkar.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import onkar.model.SurveyModel;
import onkar.write.FileWrite;



public class SurveyAction extends ActionSupport implements ModelDriven {

	private static final long serialVersionUID = 1L;
	
	private List<bean.onkar.SurveyModel> studentList = new ArrayList<bean.onkar.SurveyModel>();
	
	FileWrite line = new FileWrite();
	
	//SurveyModel bean = new SurveyModel();
	bean.onkar.SurveyModel bean =new bean.onkar.SurveyModel();

	public bean.onkar.SurveyModel getRecord() {
		return bean;
	}

	public void setRecord(bean.onkar.SurveyModel inputRecord) {
		this.bean = inputRecord;
	}
@SkipValidation
	public String execute() throws Exception {

		return "success";
	}
@SkipValidation
public String getSearchSurvey() throws Exception {

	return "success";
}

	public String getDecision() throws Exception {
		System.out.println("In GETDEC");
		 
		line.saveStudentSurvey(bean);
		System.out.println("done GETDEC");
		
		return "success";
	         
	}

	@SkipValidation
	public String getList() throws Exception {
		System.out.println("In GETlist");
		 
		studentList = line.getSurvey(bean);
		System.out.println("done GETlist");
		//SurveyModel test=(SurveyModel)studentList.get(0);
	//	System.out.println(""+test.getFirst_Name());
		//System.out.println(""+test.getFirst_Name());
		
		return SUCCESS;
	}

	public List<bean.onkar.SurveyModel> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<bean.onkar.SurveyModel> student) {
		this.studentList = student;
	}

	public Object getModel() {
		return bean;
	}
	public void validate(){

		if (bean.getFirst_Name().length()==0) {

			addFieldError( "First_Name", "First name cannot be empty" );
		}
		if (bean.getLast_Name().length()==0) {

			addFieldError( "Last_Name", "Last name cannot be empty" );
		}

		if ( bean.getEmail().length()==0) {

			addFieldError( "Email", "Email cannot be empty" );
		}		
		if (bean.getNo().length()==0) {

			addFieldError( "No", "Telephone No cannot be empty" );
		}
		
	}
	
	
}
