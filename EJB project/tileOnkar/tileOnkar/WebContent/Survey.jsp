<%@ taglib prefix="s" uri="/struts-tags" %>
  
<h1 align="Left">Student Feedback Form</h1>
<s:form action="getDecision">
  <s:textfield name="First_Name" label="*First Name" />
  <s:textfield name="Last_Name" label="*Last Name" />
  <s:textfield name="Address" label="Address"/>
  <s:textfield name="City" label="City" />
  <s:textfield name="State" label="State" />
  <s:textfield name="Zip" label="Zip" />
  <s:textfield name="No" label="*Telephone No" />
  <s:textfield name="Email" label="*Email"/>
  <s:checkboxlist name="campus" label="What do you like most on this Campus" 
  list="{'Campus','Location','Atmosphere','Students','Sports','Dorm Rooms'}" />
  <s:radio name="interest" label="Source of Campus Interest"
  list="{'Internet','Television','Friends','Other'}" />
  <s:select name="like" label="Likelihood of recommending school to people might interested" list="{'Very Likely','Likely','Unlikely'}"  />
  <s:textarea name="Comments" label="Comments"  cols="15" rows="5" />
  <s:hidden name="date" value="%{new java.util.Date()}"/>
  <s:date name="%{new java.util.Date()}"/>
  <s:submit value="Submit Survey" type="button" align='center'/>
  </s:form>
