<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.group29.db.Languages" %>
<%@ page import="kz.bitlab.javaee.group29.db.Publications" %>
<%@ page import="kz.bitlab.javaee.group29.db.News" %>
<%@ page import="kz.bitlab.javaee.group29.db.Categories" %>
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
        <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
        <script>tinymce.init({selector:'textarea'});</script>
    </head>
    <body>
        <%@include file="adminnavbar.jsp"%>
       <div class="row mt-3">
           <div class="col-2">
               <div class="container">
                   <h4>ADMIN PANEL</h4>
                   <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical" style="padding-top: 10px;">
                       <a class="nav-link"  href="/adminlang" style="color:black;">Languages</a>
                       <a class="nav-link"  href="/adminpub" style="color:black;">Publications</a>
                       <a class="nav-link"  href="/adminnew" style="color:black;">News</a>
                       <a class="nav-link" id="v-pills-sitename-tab"  href="/setsitename" role="tab"style="color:black;">Site name</a>
                   </div>
               </div>
           </div>
           <% ArrayList<Publications> publications = (ArrayList<Publications>) request.getAttribute("publications");
                ArrayList<Languages> languages = (ArrayList<Languages>) request.getAttribute("languages");
                ArrayList<Categories> categories = (ArrayList<Categories>) request.getAttribute("categories");
           %>
           <div class="col-10">
               <div class="container btn-block">
                   <div class="modal fade" id="addPubModal" tabindex="-1" aria-labelledby="addPubModalLabel" aria-hidden="true">
                       <div class="modal-dialog modal-xl">
                           <div class="modal-content">
                               <div class="modal-header">
                                   <h5 class="modal-title" id="exampleModalLabel">ADDING NEW</h5>
                                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                   </button>
                               </div>
                               <form action="/addnew" method="post">
                                   <div class="modal-body">
                                       <div class="form-group">
                                           <label for="recipient-name" class="col-form-label">Title: </label>
                                           <input type="text" class="form-control" id="recipient-name" name="title">
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Short content:</label>
                                           <textarea type="text" class="form-control" id="message-text" name="short_content" rows="5"> </textarea>
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Content:</label>
                                           <textarea type="text" class="form-control" id="content-text" name="content" rows="10"> </textarea>
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Language:</label>
                                           <select class="form-control" name="language">
                                               <% for (Languages l: languages){
                                               %>
                                               <option value="<%=l.getId()%>">
                                                   <%=l.getName()%>
                                               </option>
                                            <%
                                               }
                                               %>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Publication:</label>
                                           <select class="form-control" name="publication">
                                               <% for (Publications p: publications){
                                               %>
                                               <option value="<%=p.getId()%>">
                                                   <%=p.getName()%>
                                               </option>
                                               <%
                                                   }
                                               %>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label for="message-text" class="col-form-label">Publication:</label>
                                           <select class="form-control" name="category">
                                               <% for (Categories c: categories){
                                               %>
                                               <option value="<%=c.getId()%>">
                                                   <%=c.getName()%>
                                               </option>
                                               <%
                                                   }
                                               %>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label for="recipient-name" class="col-form-label">Picture URL: </label>
                                           <input type="text" class="form-control" id="pic_url" name="url">
                                       </div>
                                   </div>
                                   <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                       <button type="submit" class="btn btn-success">ADD NEW</button>
                                   </div>
                               </form>
                           </div>
                       </div>
                   </div>
                   <div style="display:  flex;">
                       <div>
                           <h2>NEWS</h2>
                       </div>
                       <div class="ml-auto">
                           <button class="btn btn-success" type="button" data-toggle="modal" data-target="#addPubModal">ADD NEW</button>
                       </div>
                   </div>
                   <%
                       String success = request.getParameter("success");
                       if (success != null){


                   %>
                   <div class="alert alert-success  alert-dismissible fade show" role="alert">
                       New has been added successfully!
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
                               <th>TITLE </th>
                               <th>LANGUAGE</th>
                               <th>ADDED DATE </th>
                               <th>PUBLICATION </th>
                               <th>OPERATION </th>
                           </tr>
                       </thead>
                       <tbody>
                       <% ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                           if (news != null){
                               for (News n: news){ %>
                           <tr>
                               <td>
                                   <%=n.getId()%>
                               </td>
                               <td>
                                   <%=n.getTitle()%>
                               </td>
                               <td>
                                   <%=n.getLanguage().getName()%>
                               </td>
                               <td>
                                   <%=n.getPost_date()%>
                               </td>
                               <td>
                                   <%=n.getPublication().getName()%>
                               </td>
                               <td>
                                   <a href="/newedit?id=<%=n.getId()%>" class="btn btn-primary btn-sm btn-block">EDIT</a>
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
