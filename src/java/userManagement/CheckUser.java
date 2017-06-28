/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userManagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class CheckUser extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String urlRedireccion;
        
        HttpSession sesion = request.getSession();
        
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");        
                
        if(usuario == null){
            response.sendRedirect("/pages/login.jsp");
               
        }
        else{response.sendRedirect("/pages/principal.jsp");}
        
        
        }
    }
