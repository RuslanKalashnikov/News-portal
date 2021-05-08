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

@WebServlet(value = "/addpub")
public class AddPubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String code = request.getParameter("desc");
        Double rating = null;


        try {
            rating = Double.parseDouble(request.getParameter("rating"));

        } catch (Exception e){
            e.printStackTrace();
        }



        if (name != null && code != null && rating != null && DBManager.addNewPub(new Publications(name, code, rating))){
            response.sendRedirect("/adminpub?success");
            System.out.println(1);

        }else {

            response.sendRedirect("/adminpub?error");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}



