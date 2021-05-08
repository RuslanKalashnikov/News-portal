package kz.bitlab.javaee.group29.sevlets;


import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.Languages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/addlang")
public class AddLangServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");



        if (name != null && code != null && DBManager.addNewLang(new Languages(name, code))){
            response.sendRedirect("/adminlang?success");
            System.out.println(1);

        }else {

            response.sendRedirect("/adminlang?error");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}



