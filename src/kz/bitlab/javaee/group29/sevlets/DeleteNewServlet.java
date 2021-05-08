package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/deletenew")
public class DeleteNewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = null;
        String redirect = "/adminnew";

        try {
            id = Long.parseLong(request.getParameter("new_id"));

            if (id != null){
                News news = DBManager.getNew(id);
                DBManager.deleteNew(news);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}


