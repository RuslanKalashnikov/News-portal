<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kz.bitlab.javaee.group29.db.AuthUser" %>
<%
    AuthUser currentUser = (AuthUser)session.getAttribute("CURRENT_USER");
%>
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
    </head>
    <style>
        @font-face {
            font-family: Unique;
            font-weight: normal;
            src: url(css/fonts/Unique.ttf);
        }
        @font-face {
            font-family: "Open Sans";
            font-weight: normal;
            src: url(css/fonts/OpenSans.ttf);
        }

    </style>
    <body class="profile">
        <div class="container">
            <div class="row mt-3">
                <div class="col-6 mx-auto">
                    <div style="margin: auto; justify-content: center; font-family: Unique; font-size: 40px; font-weight: bold; padding-top: 10px; height: 80px; width: 500px; background-color: white; border-radius: 15px;">
                        <a href="/worldnews" style="color: black; text-decoration: none; justify-content: center;"> <p style="text-align: center;"">THE WORLD NEWS</p> </a>
                    </div>
                </div>
            </div>
            <div class="row " style="margin-top: 100px">
                <div class="col-4 mx-auto">
                    <form action="/updateprofile" method="post">
                        <div class="register">
                            <div class="loghead">
                                Update profile
                            </div>
                            <%
                                String ssuccess = request.getParameter("success");
                                if(ssuccess!=null){
                            %>
                            <div class="container" style="margin-top: 20px">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    User updated successfully!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="logemail">
                                Email Address:
                            </div>
                            <input type="email" value="<%=currentUser.getEmail()%>" class="form-control" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px" readonly>
                            <div class="logpass">
                                Full name:
                            </div>
                            <input type="text" value="<%=currentUser.getFullName()%>" class="form-control" name="full_name", style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                            <button class="btn btn-dark" style="margin-left: 30px; margin-right: 30px; margin-top: 20px; width: 82%;">UPDATE PROFILE DATA</button>
                        </div>
                    </form>
                </div>


                <div class="col-4 mx-auto">
                    <form action="/updatepassword" method="post">
                        <div class="register">
                            <div class="loghead">
                                Update password
                            </div>
                            <%
                                String passwordSuccess = request.getParameter("passwordsuccess");
                                if(passwordSuccess!=null){
                            %>
                            <div class="container" style="margin-top: 20px">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    Password updated successfully!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <%
                                String ppasswordError = request.getParameter("passworderror");
                                if(ppasswordError!=null){
                            %>
                            <div class="container" style="margin-top: 20px">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    Couldn't update password!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <%
                                String passwordRepeat = request.getParameter("passwordrepeat");
                                if(passwordRepeat!=null){
                            %>
                            <div class="container" style="margin-top: 20px">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    Your password is used before!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="logemail">
                                Old password:
                            </div>
                            <input type="password" class="form-control" name="old_password" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                            <div class="logpass">
                                New password:
                            </div>
                            <input type="password" class="form-control" name="new_password" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                            <div class="logpass">
                                Retype new password:
                            </div>
                            <input type="password" class="form-control" name="re_new_password" style="width: 82%; margin-left: 30px; margin-top: 10px; border-color: lightgrey; border-radius: 0px">
                            <button class="btn btn-dark" style="margin-left: 30px; margin-right: 30px; margin-top: 20px; width: 82%;">UPDATE PASSWORD</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <%@include file="foot.jsp"%>
</html>
