<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<%Integer eid= Integer.parseInt(request.getParameter("employeeId"));
	System.out.println(eid);
	%>
	
	<form action="updateEmployee.do" method="post">
		<input type="hidden" name="id" value="<%=eid%>" />
		<label>Name: </label> <input name="name" /> <br>
		<label>Age: </label> <input name="age" /> <br>
		<label>Designation: </label> <input name="designation" /> <br>
		<label>Department: </label> <input name="department" /> <br>
		<label>Country: </label> <input name="country" /> <br>
		
		<button type="submit">Update</button>
	</form>
</body>
</html >