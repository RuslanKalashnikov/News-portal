package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;
import kz.bitlab.javaee.group29.db.News;
import kz.bitlab.javaee.group29.db.Comments;
import kz.bitlab.javaee.group29.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addcomment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "/login";

        request.setCharacterEncoding("UTF8");

        AuthUser authUser = (AuthUser)request.getSession().getAttribute("CURRENT_USER");
        if(authUser!=null) {

            redirect = "/";

            Long blogId = 0L;
            try {
                blogId = Long.parseLong(request.getParameter("news_id"));
            } catch (Exception e) {
            }

            News blog = DBManager.getNew(blogId);

            if(blog!= null) {

                String commentText = request.getParameter("comment");

                Comments comment = new Comments(null, authUser, blog, commentText, null);

                if(DBManager.addComment(comment)){

                    redirect = "/readmore?id="+blog.getId()+"#commentDiv";

                }

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
