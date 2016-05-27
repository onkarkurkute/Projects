package bean.onkar;

public class SurveyModel implements java.io.Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  interest,like,campus,Address,City,State,Zip,No,Email,Comments, date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	private String First_Name,Last_Name;

	public SurveyModel() 
	{

		this.First_Name = First_Name;
		this.Last_Name = Last_Name;
	}
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}


	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

}