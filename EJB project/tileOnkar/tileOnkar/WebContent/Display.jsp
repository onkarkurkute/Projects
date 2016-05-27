  <%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

    
<table border="1">
<tr>
    <td width="3%">Sr No</td>
    <td width="5%">First Name</td>
    <td width="5%">Last Name</td>
    <td width="5%">City</td>
    <td width="5%">State</td>
    <td width="4%">Zip</td>
    <td width="5%">Telephone No</td>
    <td width="6%">Email</td>
    
</tr>
<s:iterator value="studentList" status="rowstatus">
<tr>
    <td><s:property value="#rowstatus.count"/></td>
    <td><s:property value="First_Name"/></td>
    <td><s:property value="Last_Name"/></td>
    <td width="5%"><s:property value="City"/></td>
    <td width="5%"><s:property value="State"/></td>
    <td width="4%"><s:property value="Zip"/></td>
    <td width="5%"><s:property value="No"/></td>
    <td width="6%"><s:property value="Email"/></td>
    
</tr>
</s:iterator>
</table>
