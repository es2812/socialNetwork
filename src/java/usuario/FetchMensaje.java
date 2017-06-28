/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.MensajeBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class FetchMensaje extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String id = request.getParameter("id");
        
        int idMensaje = Integer.parseInt(id);
        
        Mensaje msg = null;
        try {
            msg = MensajeBD.getMensaje(idMensaje);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        //Por seguridad solo dejaremos acceder a los mensajes cuyo receptor sea el usuario de la sesion
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        if(user == null){
            response.sendRedirect("/pages/login.jsp");
        }
        
        if(msg==null || user.getId()!=msg.getReceptor().getId()){
            System.out.println("Error de seguridad");
            response.sendRedirect("/pages/fetchInbox");
        }
        
        request.setAttribute("mensaje", msg);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/mensaje.jsp");
        rd.forward(request, response);
    }
}
