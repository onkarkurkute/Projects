<%@ taglib prefix="s" uri="/struts-tags"%>

<html >
<head>
<title>SWE 645 Homepage</title>
</head>
<body>
    <h1>Welcome to Student Survey</h1></br>
    </hr>
    Hi, I am <b>Onkar Kurkute</b>,and Welcome to <b>SWE 645</b> Assignment page
    <h3>SWE 645: Component-based Software Development</h3>
    
	<p align="justify">
	 
<b>OBJECTIVE:</b>
This class will be a detailed study of the concepts and engineering principles of software component and component-based software systems that include in-depth study of Struts framework, Enterprise JavaBean (EJB) component model, Messaging and Message Driven Beans, Java Persistance API (JPA)/Hibernate, and Web services (SOAP and RESTful). After the course, students should be prepared to create large-scale component-based software systems.
</p>
<p align="justify">
 <b>CONTENT:</b>
SWE 645 covers some of the topics related to the software development models that are used to support component-based software systems. We will be studying the software design and development side of component-based software. The course content will largely focus on server-side software design and development. We will learn technologies such as an overview of MVC-based Web development framework (struts2.0), Enterprise JavaBeans (EJB 3.1), Asynchronous Messaging and Message Driven Beans, Java Persistence API (JPA 2.0)/Hibernate, Web services (SOAP, RESTful), and JavaServer Faces (JSF 2.0)/PrimeFaces ..</p>
<s:form  action="getSurvey" >
<s:submit value="Take Me to Survey" type="button" align='center'/>
</s:form>
  <s:form  action="getList" >
  <s:submit value="Display All Surveys" type="button" align='center' />
</s:form>
<s:form  action="getSearchSurvey" >
  <s:submit value="Search Surveys" type="button" align='center' />
</s:form>
<div class="clear"></div>
<script src="style/js/scripts.js"></script>
<!--[if !IE]> -->
<script src="style/js/jquery.corner.js"></script>
<!-- <![endif]-->
<div id="footer">
<p id="legal">Copyright &copy; Onkar All Rights Reserved.</p>
</div>

</body>
</html> 
