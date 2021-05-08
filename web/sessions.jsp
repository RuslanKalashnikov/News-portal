<%@ page import="kz.bitlab.javaee.group29.db.Users" %>
<%@ page import="kz.bitlab.javaee.group29.db.SessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>

        <% String sitename = (String) request.getAttribute("sitename");
            if(sitename == null){
                sitename = "NewsBlog";
            }%>

        <title><%=sitename%></title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container">
            <div class="row mt-5">
                <div class="col-6 mx-auto">
                    <form action="/addsession" method="post">
                        <div class="form-group">
                            <label>NAME : </label>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>SURNAME : </label>
                            <input type="text" name="surname" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>AGE : </label>
                            <select name="age" class="form-control">
                                <%
                                    for(int i=0;i<=120;i++){
                                %>
                                    <option value="<%=i%>"><%=i%> years</option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success">ADD SESSION</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <h1 class="text-center">
                        <%
                            SessionUser user = (SessionUser) request.getAttribute("user");

                            String name = (user!=null?user.getName():"No Name");
                            String surname = (user!=null?user.getSurname():"No Surname");
                            int age = (user!=null?user.getAge():0);
                        %>
                        Welcome <%=name%> <%=surname%> <%=age%> years old
                    </h1>
                </div>
            </div>
        </div>
    </body>
    <%@include file="foot.jsp"%>
</html>
