package kz.bitlab.javaee.group29.sevlets;


import kz.bitlab.javaee.group29.db.DBManager;
import kz.bitlab.javaee.group29.db.Languages;
import kz.bitlab.javaee.group29.db.News;
import kz.bitlab.javaee.group29.db.Publications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/savelang")
public class SaveLangServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "/adminpub";

        Long lang_id = null;


        try {
            lang_id = Long.parseLong(request.getParameter("lang_id"));

        } catch (Exception e){
            e.printStackTrace();
        }


        if (lang_id != null){

            String lang_name = request.getParameter("lang_name");
            String code = request.getParameter("code");


            Languages language = DBManager.getLanguage(lang_id);

            if (language != null){
                language.setName(lang_name);
                language.setCode(code);


                if (DBManager.saveLanguage(language)){

                    redirect = "/pubedit?id=" + lang_id + "&success";

                }
            }
        }

        response.sendRedirect(redirect);

    }






    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
