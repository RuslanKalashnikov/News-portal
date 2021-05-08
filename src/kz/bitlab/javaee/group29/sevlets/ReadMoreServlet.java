package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/readmore")
public class ReadMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthUser authUser = (AuthUser)request.getSession().getAttribute("CURRENT_USER");

        Long id = 0L;

        try{

            id = Long.parseLong(request.getParameter("id"));

        }catch (Exception e){

        }
        ArrayList<Publications> publications = DBManager.getPublications();
        request.setAttribute("pubs", publications);
        News news = DBManager.getNew(id);

        if(news != null){
            request.setAttribute("news", news);
            ArrayList<Comments> comments = DBManager.getCommentsByBlogId(news.getId());
            boolean liked = false;
            if(authUser!=null) {
                liked = DBManager.checkLike(news, authUser);
            }
            request.setAttribute("liked", liked);
            request.setAttribute("comments", comments);

        }

        request.getRequestDispatcher("/readmore.jsp").forward(request, response);
    }
}
