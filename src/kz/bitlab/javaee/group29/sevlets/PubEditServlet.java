package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.Languages;
import kz.bitlab.javaee.group29.db.Publications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pubedit")
public class PubEditServlet extends HttpServlet {

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

            Publications publication = DBManager.getPublication(id);
            request.setAttribute("publication", publication);

        }


        request.getRequestDispatcher("/pubedit.jsp").forward(request, response);



    }
}


