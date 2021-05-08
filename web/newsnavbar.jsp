<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.Publications" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kz.bitlab.javaee.group29.db.AuthUser" %>
<%
    AuthUser currentUser = (AuthUser)session.getAttribute("CURRENT_USER");
%>
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
<nav class="navstyle" style="height: 80px">
    <div style="display: flex">
        <div style="margin-top: 20px; display: flex;">
            <div style="padding-left: 15px; font-family: Open Sans">
                <form action="/setlangcookie" method="post">
                    <button type="submit" class="btn btn-link" style="color: grey; font-size: 18px; font-family: 'Open Sans'; text-decoration: none" value="RUS" name="langcode">РУС</button>
                </form>
            </div>
            <div style="margin-left: 15px">
                <form action="/setlangcookie" method="post">
                    <button type="submit" class="btn btn-link" style="color: grey; font-size: 18px; font-family: 'Open Sans'; text-decoration: none" value="ENG" name="langcode">ENG</button>
                </form>
            </div>
        </div>
        <div style="margin: auto; justify-content: center; font-family: Unique; font-size: 40px; font-weight: bold; padding-top: 10px">
            <a href="/worldnews" style="color: black; text-decoration: none"> THE WORLD NEWS </a>

        </div>
        <%
            if (currentUser!=null){
        %>
        <div style="margin-top: 20px">
            <ul class="navbar-nav ml-auto" style="display: flex">
                <div style="display: flex">
                    <li class="nav-item" style="margin-right: 22px;">
                        <a class="nav-link" href="/profile" style="color: grey; font-size: 15px; font-family: 'Open Sans'"><%=currentUser.getFullName()%></a>
                    </li>
                    <% if (currentUser.getEmail().equals("ruslan@gmail.com")){ %>
                        <li class="nav-item" style="margin-right: 22px;">
                            <a class="nav-link" href="/adminnew" style="color: grey; font-size: 15px; font-family: 'Open Sans'">Admin Panel</a>
                        </li>
                    <% } %>
                    <li class="nav-item" style="margin-right: 30px;">
                        <a class="nav-link" href="/logout" style="color: grey; font-size: 15px; font-family: 'Open Sans'">Logout</a>
                    </li>
                </div>
            </ul>
        </div>
        <%
        }else{
        %>
        <div style="margin-top: 20px">
            <ul class="navbar-nav mr-auto" style="display: flex">
                <div style="display: flex">
                    <li class="nav-item" style="margin-right:22px;">
                        <a class="nav-link" href="/login" style="color: grey; font-size: 18px; font-family: 'Open Sans'">Login</a>
                    </li>
                    <li class="nav-item" style="margin-right: 30px; ">
                        <a class="nav-link" href="/register" style="color: grey; font-size: 18px; font-family: 'Open Sans'">Register</a>
                    </li>
                </div>
            </ul>
        </div>
        <%
            }
        %>
    </div>
</nav>



















