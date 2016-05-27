package bean.onkar;

import java.util.ArrayList;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import sql.onkar.sqlconn;


@Stateless

public class studentbeanEJB implements studentbeanEJBRemote{

	
    public studentbeanEJB() {
    }

    @Override
    public String getEchoString(String clientString) {
        return clientString + " - from local session bean";
    }
	@Override
	public   ArrayList<SurveyModel> getStudentRecords(SurveyModel inputBean) {
		return sqlconn.getStudentSurveyRecords(inputBean);
	}
    
	@Override
	public void saveStudentRecord(SurveyModel inputBean) {
		System.out.println("In EJB Save");
		sqlconn.insertStudentSurvey(inputBean);
		System.out.println("done EJB Save");
		
	}

}
