<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <% String sitename = (String) request.getAttribute("sitename");
            if(sitename == null){
                sitename = "NewsBlog";
            }%>

        <title><%=sitename%></title>
        <link rel="stylesheet" type="text/css" href="css/fa/css/all.css">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://cdn0.iconfinder.com/data/icons/simplicity/512/news_article_blog-512.png" rel="icon" type="image/icon type">
        <%@include file="head.jsp"%>
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
                <% ArrayList<Publications> pubs = (ArrayList<Publications>) request.getAttribute("pubs");%>
                <% if (pubs != null){
                    for (Publications publication: pubs) { %>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="/worldnews?pub=<%=publication.getId()%>"><%=publication.getName()%></a>
                </li>
                <% }
                } %>
            </ul>
        </nav>
        <div class="container pb-5">
            <div class="row mt-3">
                <div class="col-12">
                    <%
                        News news = (News) request.getAttribute("news");
                        if (news != null){
                    %>
                    <div style="display: flex">
                        <div class="col-8">
                            <div class="readmorehead" style="font-family: Unique">
                                <%=news.getTitle()%>
                            </div>
                            <div class="readmoredate" style="font-family: 'Open Sans'">
                                <%  String postdate = news.getPost_date();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                %>
                                <%=format.parse(postdate)%> by <%=news.getPublication().getName()%>
                            </div>
                            <div style="margin-top: 15px">
                                <img src="<%=news.getPicture_url()%>" style="width: 660px; height: auto">
                            </div>
                            <div style="margin-top: 20px; font-size: 18px; font-family: 'Open Sans';">
                                <%=news.getContent()%>
                            </div>
                            <p>
                            <table cellpadding="5px" style="margin-top: 40px">
                                <tr>
                                    <td>
                                        <%
                                            String color = "color:black;";
                                            boolean liked = (boolean)request.getAttribute("liked");
                                            if(liked){
                                                color = "color:red;";
                                            }
                                        %>
                                        <a id = "heart" href="JavaScript:void(0)" style="<%=color%> font-size: 30px; text-decoration: none;" <% if(currentUser!=null) {%> onclick="toLike()" <% } %>><i class="far fa-thumbs-up"></i></a>
                                    </td>
                                    <td>
                                        <span id = "like_amount" style="font-size: 25px; font-family: Unique"><%=news.getLikeAmount()%></span>
                                        <span style="font-size: 25px; font-family: 'Open Sans'; margin-left: 10px">likes</span>
                                    </td>
                                </tr>
                            </table>
                            </p>
                            <%
                                if(currentUser!=null){
                            %>
                            <script type="text/javascript">
                                function toLike(){
                                    $.post("/tolike",
                                        {
                                            blog_id: <%=news.getId()%>
                                        }, function (result) {
                                            if(result!="ERROR"){
                                                var likeStat = JSON.parse(result);
                                                $("#like_amount").html(likeStat['likes']);
                                                if(likeStat['is_liked']==true){
                                                    $("#heart").css("color","red");
                                                }else{
                                                    $("#heart").css("color","black");
                                                }
                                            }
                                        }
                                    );
                                }
                            </script>
                            <%
                                }
                            %>
                            <p>


                            <h2 style="margin-top: 60px; font-family: Unique; font-weight: bold">Comments:</h2>
                            <%
                                if(currentUser!=null){
                            %>
                            <div class="row mt-3" id = "commentDiv">
                                <div class="col-12">
                                    <form action="/addcomment" method="post">
                                        <input type="hidden" name="news_id" value="<%=news.getId()%>">
                                        <div class="form-group" style="font-family: 'Open Sans'">
                                            <textarea class="form-control" name="comment" placeholder="Insert comment"></textarea>
                                            <button class="btn btn-dark btn-sm mt-3">ADD COMMENT</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="row mt-3">
                                <div class="col-12">
                                    <div class="list-group">
                                        <%
                                            ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("comments");
                                            if(comments!=null){
                                                for(Comments c : comments){
                                        %>
                                        <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                                            <div class="d-flex w-100 justify-content-between">
                                                <h5 class="mb-1">
                                                    <%=c.getUser().getFullName()%>
                                                </h5>
                                                <small>
                                                    <%  Timestamp ts = c.getPostDate();
                                                        Date date = new Date();
                                                        date.setTime(ts.getTime());
                                                        String formattedDate = new SimpleDateFormat("dd-MM-yyyy    HH:mm").format(date);
                                                    %>

                                                    <%=formattedDate%>


                                                </small>
                                            </div>
                                            <p class="mb-1">
                                                <%=c.getComment()%>
                                            </p>
                                        </a>
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div style="min-height: 50px; width: 100%; background-color: black; border-radius: 15px">
                                <div style="font-family: Unique; justify-content: center; font-size: 30px; margin: auto; width: 50%; color: white; padding-top: 20px; padding-left: 10px">
                                    About <%=news.getPublication().getName()%>
                                </div>
                                <div style="font-family: 'Open Sans'; font-size: 18px; color: white; padding-top: 10px; padding-left: 10px; padding-right: 10px">
                                    <%=news.getPublication().getDescription()%>
                                </div>
                                <div style="font-family: Unique; font-size: 25px; font-weight: bold; padding-left: 20px; color: white; margin-top: 60px">
                                    RATING:  <%=news.getPublication().getRating()%>
                                </div>
                            </div>
                        </div>
                    </div>


                    <%
                        }else{
                    %>
                        <h1 class="text-center">
                            404 NOT FOUND
                        </h1>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
    <%@include file="foot.jsp"%>
</html>
