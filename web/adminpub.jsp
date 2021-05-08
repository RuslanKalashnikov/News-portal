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

                   <div class="modal fade" id="addLangModal" tabindex="-1" aria-labelledby="addLangModalLabel" aria-hidden="true">
                       <div class="modal-dialog">
                           <div class="modal-content">
                               <div class="modal-header">
                                   <h5 class="modal-title" id="exampleModalLabel">ADDING PUBLICATION</h5>
                                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                   </button>
                               </div>
                               <form action="/addpub" method="post">
                                   <div class="modal-body">
                                       <div class="form-group">
                                           <label for="recipient-name" class="col-form-label">Name: </label>
                                           <input type="text" class="form-control" id="recipient-name" name="name">
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Description:</label>
                                           <textarea type="text" class="form-control" id="message-text" name="desc"> </textarea>
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Rating:</label>
                                           <input type="number" class="form-control" id="message-text2" name="rating">
                                       </div>
                                   </div>
                                   <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                       <button type="submit" class="btn btn-success">ADD PUBLICATION</button>
                                   </div>
                               </form>
                           </div>
                       </div>
                   </div>
                   <div style="display:  flex;">
                       <div>
                           <h2>PUBLICATIONS</h2>
                       </div>
                       <div class="ml-auto">
                           <button class="btn btn-success" type="button" data-toggle="modal" data-target="#addLangModal">ADD PUBLICATION</button>
                       </div>
                   </div>
                   <%
                       String success = request.getParameter("success");
                       if (success != null){


                   %>
                   <div class="alert alert-success  alert-dismissible fade show" role="alert">
                       Publication has been added successfully!
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
                   <table class="table table-striped mt-3">
                       <thead>
                           <tr>
                               <th>ID</th>
                               <th>NAME </th>
                               <th>DESCRIPTION</th>
                               <th>RATING </th>
                               <th>OPERATIONS </th>
                           </tr>
                       </thead>
                       <tbody>

                       <% ArrayList<Publications> publications = (ArrayList<Publications>) request.getAttribute("publications");
                       if (publications != null){
                           for (Publications pub: publications){ %>
                           <tr>
                               <td>
                                   <%=pub.getId()%>
                               </td>
                               <td>
                                   <%=pub.getName()%>
                               </td>
                               <td>
                                   <%=pub.getDescription()%>
                               </td>
                               <td>
                                   <%=pub.getRating()%>
                               </td>
                               <td>
                                   <a href="/pubedit?id=<%=pub.getId()%>" class="btn btn-primary btn-sm btn-block">EDIT</a>
                               </td>
                           </tr>
                       <%
                           }
                       } %>
                       </tbody>
                   </table>
               </div>
           </div>
       </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>
