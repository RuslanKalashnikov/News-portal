<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.Languages" %>
<%@ page import="kz.bitlab.javaee.group29.db.Publications" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <% String sitename = (String) request.getAttribute("sitename");
            if(sitename == null){
                sitename = "NewsBlog";
            }%>

        <title><%=sitename%></title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn0.iconfinder.com/data/icons/simplicity/512/news_article_blog-512.png" rel="icon" type="image/icon type">
    </head>
    <body>
        <%@include file="adminnavbar.jsp"%>
       <div class="row mt-3">
           <div class="col-2">
               <div class="container">
                   <h4>ADMIN PANEL</h4>
                   <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical" style="padding-top: 10px;">
                       <a class="nav-link" id="v-pills-home-tab" href="/adminlang" role="tab" style="color:black;">Languages</a>
                       <a class="nav-link" id="v-pills-profile-tab"  href="/adminpub" role="tab" style="color:black;">Publications</a>
                       <a class="nav-link" id="v-pills-messages-tab"  href="/adminnew" role="tab"style="color:black;">News</a>
                       <a class="nav-link" id="v-pills-sitename-tab"  href="/setsitename" role="tab"style="color:black;">Site name</a>
                   </div>
               </div>
           </div>
           <div class="col-10">
               <div class="container btn-block">
                   <% Publications pub = (Publications) request.getAttribute("publication");
                        if (pub != null){
                   %>

                   <div style="display:  flex;">
                       <div>
                           <h2>PUBLICATIONS</h2>
                       </div>
                   </div>
                   <%
                       String success = request.getParameter("success");
                       if (success != null){


                   %>
                   <div class="alert alert-success  alert-dismissible fade show" role="alert">
                       Publication has been changed successfully!
                       <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                           <span aria-hidden="true">&times;</span>
                       </button>
                   </div>
                   <%
                       }
                   %>
                   <%
                       String error = request.getParameter("error");
                       if (error != null){


                   %>
                   <div class="alert alert-danger  alert-dismissible fade show" role="alert">
                       Error!!!
                       <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                           <span aria-hidden="true">&times;</span>
                       </button>
                   </div>
                   <%
                       }
                   %>

                   <div class="modal fade" id="deleteHotelModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                       <div class="modal-dialog" role="document">
                           <div class="modal-content">
                               <form action="/deletepub" method="post">
                                   <input type="hidden" value="<%=pub.getId()%>" name="pub_id">
                                   <div class="modal-header">
                                       <h5 class="modal-title">Confirm delete</h5>
                                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                           <span aria-hidden="true">&times;</span>
                                       </button>
                                   </div>
                                   <div class="modal-body">
                                       Are you sure to delete?
                                   </div>
                                   <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>
                                       <button class="btn btn-danger">YES</button>
                                   </div>
                               </form>
                           </div>
                       </div>
                   </div>
                   <form action="/savepub" method="post">
                       <input type="hidden" value="<%=pub.getId()%>" name="pub_id">
                       <div class="form-group">
                           <label>
                               Name :
                           </label>
                           <input type="text" name="pub_name" class="form-control" value="<%=pub.getName()%>">
                       </div>
                       <div class="form-group">
                           <label>
                               Description :
                           </label>
                           <textarea type="text" name="desc" class="form-control" rows="8"><%=pub.getDescription()%></textarea>
                       </div>
                       <div class="form-group">
                           <label>
                               Rating:
                           </label>
                           <input type="number" class="form-control" id="message-text2" name="rating" value="<%=pub.getRating()%>">
                       </div>

                       <div class="form-group">
                           <button class="btn btn-success">SAVE</button>
                           <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteHotelModal">DELETE</button>
                       </div>
                   </form>
                   <% }
                        else {
                   %>
                   <h1>404 NOT FOUND</h1>
                   <% }
                   %>
               </div>
           </div>
       </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>
