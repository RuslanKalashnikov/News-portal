package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/deletelang")
public class DeleteLangServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = null;
        String redirect = "/adminlang";

        try {
            id = Long.parseLong(request.getParameter("lang_id"));

            if (id != null){
                DBManager.deleteLang(id);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}


