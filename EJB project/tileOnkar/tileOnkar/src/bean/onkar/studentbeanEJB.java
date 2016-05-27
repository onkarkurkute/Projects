package bean.onkar;

import java.util.ArrayList;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import sql.onkar.sqlconn;


@Stateless

public class studentbeanEJB implements studentbeanEJBRemote{

	
    public studentbeanEJB() {
        // TODO Auto-generated constructor stub
    }

    public String getEchoString(String clientString) {
        return clientString + " - from local session bean";
    }
	public   ArrayList<SurveyModel> getStudentRecords(SurveyModel inputBean) {
		return sqlconn.getStudentSurveyRecords(inputBean);
		//return inputBean;
	}
    
	public void saveStudentRecord(SurveyModel inputBean) {
		sqlconn.insertStudentSurvey(inputBean);
		
	}

}
