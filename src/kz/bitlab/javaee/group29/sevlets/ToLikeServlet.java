package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;
import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(value = "/tolike")
public class ToLikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String message = "ERROR";

        AuthUser authUser = (AuthUser)request.getSession().getAttribute("CURRENT_USER");

        if(authUser!=null) {

            Long blogId = 0L;
            try {
                blogId = Long.parseLong(request.getParameter("blog_id"));
            } catch (Exception e) {
            }

            News blog = DBManager.getNew(blogId);

            if(blog!=null) {

                HashMap<String, String> result = DBManager.likeBlog(blog, authUser);
                message = "{\"likes\":"+result.get("likes")+",\"is_liked\":"+result.get("liked")+"}";

            }
        }

        out.print(message);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
