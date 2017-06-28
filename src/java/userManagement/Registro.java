/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userManagement;

import BD.UsuarioBD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

import security.Hash;
/**
 *
 * @author Esther
 */

public class Registro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        String usuario = request.getParameter("user");
        String correo = request.getParameter("email");
        String contrasenya = request.getParameter("password");
        String contrasenyaDebeCoincidir = request.getParameter("password2");
        
        
        if(!(contrasenya.equals(contrasenyaDebeCoincidir))){
            request.setAttribute("error", "contrasenya");
            request.setAttribute("usuario", usuario);
            request.setAttribute("correo", correo);
            request.getRequestDispatcher("/pages/registro.jsp").forward(request, response);
        }
        else{   
            String contrasenyaC = "";
            
            try{
                contrasenyaC = new Hash().doHash(contrasenya);
            }catch(NoSuchAlgorithmException e){e.printStackTrace(); return;}
            
            
            Usuario instanciaUser = new Usuario();
            
            try {
                if(UsuarioBD.checkExisteUsername(usuario)){                    
                    request.setAttribute("error", "user");
                    request.setAttribute("correo", correo);
                    request.getRequestDispatcher("/pages/registro.jsp").forward(request, response);
                }
                else if(UsuarioBD.checkExisteCorreo(correo)){
                    request.setAttribute("error", "correo");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("/pages/registro.jsp").forward(request, response);
                }
                else{
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                
                    instanciaUser = UsuarioBD.addUsuario(correo, usuario, contrasenyaC, date, 1);
                
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario",instanciaUser);
                    response.sendRedirect("/pages/principal.jsp");
                }
            }
            catch (SQLException ex) {
                   ex.printStackTrace();
                   return;
            }
            
        }
    }
}
