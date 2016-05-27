package bean.onkar;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface studentbeanEJBRemote {
	
	   public   ArrayList<SurveyModel> getStudentRecords(SurveyModel inputBean);
	   public void saveStudentRecord(SurveyModel inputBean);
	   public String getEchoString(String clientString);
}
