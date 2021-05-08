<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <% String sitename = (String) request.getAttribute("sitename"); %>
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
                        <a class="nav-link"  href="/adminlang" style="color:black;">Languages</a>
                        <a class="nav-link"  href="/adminpub" style="color:black;">Publications</a>
                        <a class="nav-link"  href="/adminnew" style="color:black;">News</a>
                        <a class="nav-link" id="v-pills-sitename-tab"  href="/setsitename" role="tab"style="color:black;">Site name</a>
                    </div>
                </div>
            </div>
            <div class="col-10">
                <div class="container">
                    <form action="/changesitename" method="post">
                        <div class="row mt-5">
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="form-control-lg">
                                        Enter site name:
                                    </label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" placeholder="Site name" name = "sitename">
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <button class="btn btn-dark btn-lg">Set site name</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>

