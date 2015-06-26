<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page language="java" import="java.util.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symantec ISV</title>
</head>
<body>
<B>Welcome to Symantec ISV</B>

Session attributes:  
<%  
for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )   
{  
     String attribName = (String) e.nextElement();  
     Object attribValue = session.getAttribute(attribName);  
%>  
    <BR><%= attribName %> - <%= attribValue %>  
<%  
}  
%>  

</body>
</html>