<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Login page</title>
    </head>
    <body>
           <form action="${pageContext.request.contextPath}/api/v1/users/login" method="post">
               <div><label> User Name : <input type="text" name="username"/> </label></div>
               <div><label> Password: <input type="password" name="password"/> </label></div>
               <div><input type="submit" value="Sign In"/></div>
           </form>
    </body>
</html>