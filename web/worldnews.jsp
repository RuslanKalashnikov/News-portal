<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.Publications" %>
<%@ page import="kz.bitlab.javaee.group29.db.Categories" %>
<%@ page import="kz.bitlab.javaee.group29.db.News" %>
<%@ page import="javax.print.DocFlavor" %>
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
    <body>
        <%@include file="newsnavbar.jsp"%>
        <nav class="downnav" style="margin-top: 15px;">
            <ul class="nav nav-fill mx-auto" style="background-color: black; font-family: 'Open Sans'; font-size: 20px">
                <%
                    boolean categories = (boolean) request.getAttribute("categories");
                if (!categories) {
                    ArrayList<Publications> pubs = (ArrayList<Publications>) request.getAttribute("pubs");%>
                    <% if (pubs != null){
                            for (Publications publication: pubs) { %>
                    <li class="nav-item">
                        <a class="nav-link" style="color: white" href="/worldnews?pub=<%=publication.getId()%>"><%=publication.getName()%></a>
                    </li>
                    <% }
                    }%>
                <%} else {
                    Publications publication = (Publications) request.getAttribute("neededpub");
                    ArrayList<Categories> categories1 = (ArrayList<Categories>) request.getAttribute("categors");
                    for (Categories cats: categories1){
                %>
                    <li class="nav-item">
                        <a class="nav-link" style="color: white" href="/worldnews?pub=<%=publication.getId()%>&category=<%=cats.getId()%>"><%=cats.getName()%></a>
                    </li>
                <% }
                }%>
            </ul>
        </nav>
        <div class="container">
            <form action="#" method="post">
                <div class="row mt-3">
                    <div class="col-12 mx-auto">
                        <% if (!categories){%>
                            <div class="container" style="background-color: black ; height: 140px; width: 100%;">
                                <h1 style="font-family: Unique; color: white; font-weight: lighter; padding-top: 20px; margin-left: 20px">All World News</h1>
                                <div style="color: white; margin-top: 15px; margin-left: 20px; font-family: 'Open Sans'; font-size: 18px">
                                    You can read all news in different languages around the World
                                </div>
                            </div>
                        <% } else{
                            Publications publication = (Publications) request.getAttribute("neededpub");%>
                            <h1 style="font-family: Unique; color: black; font-weight: bold; padding-top: 20px; margin:auto; justify-content: center; text-align: center"><%=publication.getName()%> news: </h1>
                        <% } %>
                        <% ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");%>
                        <%  if (news != null) {
                            int count = 0;
                            String langcode = (String) request.getAttribute("langcode"); %>
                        <div>
                            <div class="row mt-3">
                                <% for (News n: news){
                                    if (langcode.equals("ALL") || n.getLanguage().getCode().equals(langcode)) { %>
                                        <div class="col-6" style=" margin-top: 25px; <% if (count % 2 != 0){ out.print("margin-left: 60px;");};%>">
                                            <div class="card" style="width: 100%; border: none;">
                                                <img class="card-img-top" src="<%=n.getPicture_url()%>" alt="Card image cap" style="height: 400px">
                                                <div class="card-body" style="background-color: #f4f7f9">
                                                    <h5 class="cardtitle" style="font-family: 'Open Sans';"><%=n.getTitle()%></h5>
                                                    <p class="cardcontent" style="font-family: 'Open Sans';"><%=n.getShort_content()%></p>
                                                    <div class="but">
                                                        <a href="/readmore?id=<%=n.getId()%>" class="cardread" style="text-decoration: none; font-family: 'Open Sans'" >READ MORE</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                <%      }
                                    }
                                }
                                if (news == null) { %>
                                    <h1 style="font-family: 'Open Sans'"> NOT FOUND NEWS </h1>
                               <% } %>
                            </div>
                            <div style="margin-top: 15px; display:flex;">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>

