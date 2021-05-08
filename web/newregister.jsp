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
        <link href="https://cdn0.iconfinder.com/data/icons/simplicity/512/news_article_blog-512.png" rel="icon" type="image/icon type">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <%@include file="head.jsp"%>
    </head>
    <style>
        @font-face {
            font-family: Open Sans;
            font-weight: normal;
            src: url(css/fonts/OpenSans.ttf);
        }
    </style>
    <body class="regback">
    <div class="container">
        <div class="row " style="margin-top: 170px">
            <div class="col-6 mx-auto">
                <form action="/register" method="post">
                    <div class="register">
                        <div class="loghead">
                            Registration
                        </div>
                        <%
                            String error = request.getParameter("emailerror");
                            if(error!=null){
                        %>
                        <div class="container" style="margin-top: 20px">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                User with this email exists!
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <%
                            String passwordError = request.getParameter("passworderror");
                            if(passwordError!=null){
                        %>
                        <div class="container" style="margin-top: 20px">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                Password mismatch!
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <%
                            String success = request.getParameter("success");
                            if(success!=null){
                        %>
                        <div class="container" style="margin-top: 20px">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                Registration Completed!
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
                        <div class="logpass">
                            Repeat password
                        </div>
                        <input type="password" name="re_password" class="form-control" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                        <div class="logpass">
                            Full name
                        </div>
                        <input type="text" name="full_name" class="form-control" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">

                        <button class="btn btn-dark" style="margin-left: 30px; margin-right: 30px; margin-top: 20px; width: 82%;">SIGN UP</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
    <%@include file="foot.jsp"%>
</html>
