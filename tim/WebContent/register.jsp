
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <form action="register" method="post">
 
        First Name:<input type="text" name="f_name"/><br/>
        Last Name:<input type="text" name="l_name"/><br/>
        Account Number:<input type="text" name="acc_nu" /><br/>
        Account: <select name="acc_type">
            <option>simple account</option>
            <option>current account</option>
            <option>fixed account</option>
        </select> <br/>
        User_Name:<input type="text" name="user_name"/><br/>
        Password:<input type="password" name="password"/><br/>
        Balance:<input type="text" name="balance"/><br/>
        Email:<input type="text" name="email"/><br/>
        ATM_PIN:<input type="text" name="change_pin"/><br/>
        <input type="submit" value="Submit"/>
 
    </form>
</body>
</html>