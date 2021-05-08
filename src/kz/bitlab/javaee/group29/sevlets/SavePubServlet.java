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

@WebServlet(value = "/savepub")
public class SavePubServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "/adminpub";

        Long pub_id = null;
        Double rating = null;

        try {
            pub_id = Long.parseLong(request.getParameter("pub_id"));
            rating = Double.parseDouble(request.getParameter("rating"));

        } catch (Exception e){
            e.printStackTrace();
        }


        if (pub_id != null && rating != null){

            String lang_name = request.getParameter("pub_name");
            String desc = request.getParameter("desc");


            Publications publication = DBManager.getPublication(pub_id);

            if (publication != null){
                publication.setName(lang_name);
                publication.setDescription(desc);
                publication.setRating(rating);

                if (DBManager.savePub(publication)){

                    redirect = "/pubedit?id=" + pub_id + "&success";

                }
            }
        }

        response.sendRedirect(redirect);

    }






    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
