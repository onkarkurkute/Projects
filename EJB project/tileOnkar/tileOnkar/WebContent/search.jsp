<%@ taglib prefix="s" uri="/struts-tags" %>
  
<h1 align="Left">Student Search Form</h1>
<s:form action="getList">
  <s:textfield name="First_Name" label="First Name" />
  <s:textfield name="Last_Name" label="Last Name" />
  <s:textfield name="City" label="City" />
  <s:textfield name="State" label="State" />
  <s:submit value="Submit Survey" type="button" align='center'/>
  </s:form>
