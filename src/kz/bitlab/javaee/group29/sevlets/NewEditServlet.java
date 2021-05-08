package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/newedit")
public class NewEditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.parseLong(request.getParameter("id"));

        }catch (Exception e){
            e.printStackTrace();
        }


        if (id != null){

            News news = DBManager.getNew(id);
            request.setAttribute("new", news);

        }
        ArrayList<Languages> languages = DBManager.getLanguages();
        ArrayList<Publications> publications = DBManager.getPublications();
        ArrayList<Categories> categories = DBManager.getCategories();

        request.setAttribute("categories", categories);
        request.setAttribute("languages", languages);
        request.setAttribute("publications", publications);


        request.getRequestDispatcher("/newedit.jsp").forward(request, response);



    }
}


