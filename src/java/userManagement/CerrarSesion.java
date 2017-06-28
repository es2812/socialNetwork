/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userManagement;

import BD.UsuarioBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author Cristina
 */

public class CerrarSesion extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        
        HttpSession sesion = req.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        sesion.invalidate();
        if(usuario == null){
            resp.sendRedirect("/pages/login.jsp");
        }
        
        try {
             if(usuario != null){
                UsuarioBD.desconectar(usuario.getId());
                
                resp.sendRedirect("/pages/login.jsp");
             }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return;
        }
    }
    
}
