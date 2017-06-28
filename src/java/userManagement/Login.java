/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userManagement;

import BD.UsuarioBD;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import security.Hash;

/**
 *
 * @author dizzy
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        String urlRedirect;
        //el usuario se puede loguear con su username o su email
        String id = request.getParameter("id");
        String contrasenya = request.getParameter("password");
        
        String contrasenyaC = "";
        
        try{
            contrasenyaC = new Hash().doHash(contrasenya);
        }catch(NoSuchAlgorithmException e){e.printStackTrace(); return;}
            
        Usuario instanciaUser = new Usuario();
            
        try {
          if(!(UsuarioBD.checkExisteUsername(id)) && !(UsuarioBD.checkExisteCorreo(id))){
            request.setAttribute("error","id");
            urlRedirect="/pages/login.jsp";
            request.getRequestDispatcher(urlRedirect).forward(request, response);
          }
          else{
            //si si que existe el id como correo o como usuario, queremos guardar el identificador
            //del usuario en una variable para añadirlo a la sesion
            instanciaUser = UsuarioBD.logUsuario(id, contrasenyaC);
            
            if(instanciaUser == null){
             request.setAttribute("error","pass");
             request.setAttribute("id",id);
             urlRedirect="/pages/login.jsp";
             request.getRequestDispatcher(urlRedirect).forward(request, response);
            }
            else{
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario",instanciaUser);
            
            response.sendRedirect("/pages/principal.jsp");
            }
            }
        
        }catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("error","sql");
            urlRedirect= "/pages/login.jsp";
            request.getRequestDispatcher(urlRedirect).forward(request, response);
        }        
        
        }
 }

