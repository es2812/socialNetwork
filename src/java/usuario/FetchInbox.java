/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.MensajeBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class FetchInbox extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
         HttpSession sesion = request.getSession();
         Usuario usuario = (Usuario) sesion.getAttribute("usuario"); 
         
         if(usuario == null){
            response.sendRedirect("/pages/login.jsp");
        }
        
         
         ArrayList <Mensaje> mensajes = new ArrayList();
        
        try {
            mensajes = MensajeBD.getMensajesUsuario(usuario.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
         request.setAttribute("mensajes",mensajes);
        
         getServletContext().getRequestDispatcher("/pages/miespacio_inbox.jsp").forward(request,response);
    }
}
