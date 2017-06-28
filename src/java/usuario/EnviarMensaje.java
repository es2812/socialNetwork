/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.MensajeBD;
import BD.UsuarioBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class EnviarMensaje extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
        
        req.setCharacterEncoding("UTF-8");
        
        Usuario emisor = null;
        Usuario receptor = null;
        String cuerpo = "";
        java.sql.Date fechaEnvio = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        boolean leido = false;
        
        emisor = (Usuario) req.getSession().getAttribute("usuario");
        if(emisor == null){
            resp.sendRedirect("/pages/login.jsp");
        }
        
        
        try {
            receptor = UsuarioBD.getInstanciaUsuario(req.getParameter("receptor"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        cuerpo = req.getParameter("cuerpo");
        
        try {
            MensajeBD.registraMensaje(emisor.getId(),receptor.getId(),cuerpo,fechaEnvio,leido);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        resp.sendRedirect("/pages/accederPagUsuario?usuario="+receptor.getId());
        
    }
    
}
