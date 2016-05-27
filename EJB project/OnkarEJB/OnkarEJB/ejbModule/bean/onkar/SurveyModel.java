package bean.onkar;

public class SurveyModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String Raffle, interest,like,campus,Address,City,State,Zip,No,Email,Comments, date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	private String First_Name,Middle_Name,Last_Name;
	private double  meanValue;
	private double  stdDeviation;
	int [] data;

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

	public String getRaffle() {
		return Raffle;
	}

	public void setRaffle(String raffle) {
		Raffle = raffle;
		String [] cs=Raffle.split(",");
		int i=0;
		data=new int[10];
		for(String A:cs)
		{
			data[i] = Integer.parseInt(A);
			i++;
		}	

	}

	public void setRaffle1(String raffle) {
		Raffle = raffle;
		
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

	public String getMiddle_Name() {
		return Middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		Middle_Name = middle_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public double getMeanValue() {
		return meanValue;
	}

	public void setMeanValue(double meanValue) {
		this.meanValue = meanValue;


	}

	public double getStdDeviation() {
		return stdDeviation;
	}

	public void setStdDeviation(double stdDeviation) {
		this.stdDeviation = stdDeviation;
	}

}