package kz.bitlab.javaee.group29.sevlets;

import kz.bitlab.javaee.group29.db.AuthUser;
import kz.bitlab.javaee.group29.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthUser authUser = (AuthUser)request.getSession().getAttribute("CURRENT_USER");

        if(authUser!=null) {

            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            String reNewPassword = request.getParameter("re_new_password");

            if(oldPassword.equals(authUser.getPassword()) && newPassword.equals(reNewPassword)) {

                authUser.setPassword(newPassword);
                if(DBManager.savePassword(authUser)){
                    response.sendRedirect("/profile?passwordsuccess");
                }else{
                    response.sendRedirect("/profile?passwordrepeat");
                }

            }else{

                response.sendRedirect("/profile?passworderror");

            }
        }else{

            response.sendRedirect("/login");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
