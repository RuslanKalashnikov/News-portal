<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.Users" %>
<%@ page import="kz.bitlab.javaee.group29.db.DBManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <% String sitename = (String) request.getAttribute("sitename");
            if(sitename == null){
                sitename = "NewsBlog";
            }%>

        <title><%=sitename%></title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://cdn0.iconfinder.com/data/icons/simplicity/512/news_article_blog-512.png" rel="icon" type="image/icon type">
        <%@include file="head.jsp"%>
    </head>
    <style>
        @font-face {
            font-family: Open Sans;
            font-weight: normal;
            src: url(css/fonts/OpenSans.ttf);
        }
    </style>
    <body class="back">
        <div class="container">
            <div class="row " style="margin-top: 170px">
                <div class="col-6 mx-auto">
                    <form action="/login" method="post">
                        <div class="login">
                            <div class="loghead">
                                Log in to your account.
                            </div>
                            <%
                                String error = request.getParameter("error");
                                if(error!=null){
                            %>
                            <div class="container" style="margin-top: 20px">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    Incorrect email or password!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="logemail">
                                Email Address
                            </div>
                            <input type="email" name="email" class="form-control" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                            <div class="logpass">
                                Password
                            </div>
                            <input type="password" name="password" class="form-control" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">

                            <button class="btn btn-dark" style="margin-left: 30px; margin-right: 30px; margin-top: 20px; width: 82%;">Log In</button>
                            <p class="morelogin">
                                Don’t have a account? <a href="/register" style="font-weight: bold; color: black">    Create one</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <%@include file="foot.jsp"%>
</html>
