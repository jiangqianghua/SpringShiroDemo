 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
    <head>
        <title>login</title>
    </head>
    <body>
        <h1>欢迎登陆</h1>
        <form action="/loginUser" method="POST">
            <input type="text" name="username"><br>
            <input type="password" name="password"><br>
            <input type="submit" value="提交">
        </form>
    </body>
 </html>