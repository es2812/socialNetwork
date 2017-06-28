/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userManagement;

import BD.UsuarioBD;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Usuario;
import security.Hash;

/**
 *
 * @author Esther
 */
@MultipartConfig
//TODO: Cambiar foto de perfil
public class EditarPerfil extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
              
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario"); 
        
               
        request.setAttribute("error","none");
        
        if(usuario == null){
            response.sendRedirect("/pages/login.jsp");
        }
                
        
        Part filePart = request.getPart("fotoPerfil");
        //si no se ha subido un archivo el tamaño sera 0 y no se cambiara la foto de perfil
        if(filePart.getSize()!=0){
            File uploads = new File(getServletContext().getRealPath("/subidas"));
            
            File file = File.createTempFile("fotPer-",".jpg",uploads);
        
            try(InputStream fileContent = filePart.getInputStream()){
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
                
            String fotoPerfil = "/subidas/"+file.getName();
            usuario.setFotoPerfil(fotoPerfil);
        }
        
        String username = request.getParameter("user");
        String contrasenya = request.getParameter("new_password");
        String contrasenyaDebeCoincidir = request.getParameter("new_password_rpt");
        
        String contrasenyaC = "";
        
        if(!(username.equals(""))&& !(username.equals(usuario.getUsername()))){
            try {            
                if(!UsuarioBD.checkExisteUsername(username)){
                    usuario.setUsername(username);
                }
                else{
                    request.setAttribute("error", "usuario");
                }
            } catch (SQLException ex) {
                request.setAttribute("error","sql");
            }
        }
        if(!contrasenya.equals("")){
            if(!contrasenya.equals(contrasenyaDebeCoincidir)){
                request.setAttribute("error","contrasenya");
            }
            else{
                Hash h = new Hash();
                try {
                 contrasenyaC = h.doHash(contrasenya);
                } catch (NoSuchAlgorithmException ex) {ex.printStackTrace(); return;}
        
                usuario.setContrasenya(contrasenyaC);
            }
        }
        try {
            UsuarioBD.updateUser(usuario.getUsername(), usuario.getContrasenya(), usuario.getFotoPerfil(),usuario.getId());
        
            } catch (SQLException ex) {
                    ex.printStackTrace();
                    request.setAttribute("error","sql");
            }
              
        getServletContext().getRequestDispatcher("/pages/miespacio_editarperfil.jsp").forward(request,response);
        
    }
}
