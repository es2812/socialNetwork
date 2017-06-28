/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verRecetas;

import BD.ComentariosBD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author Cristina
 */
@WebServlet("/anyadirCom")
public class AnyadirComent extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        request.setCharacterEncoding("UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String texto = request.getParameter("texto");
        
        HttpSession sesion = request.getSession();
        Usuario usu = (Usuario) sesion.getAttribute("usuario");
        
        ComentariosBD.anyadirComentario(usu.getId(), id, texto); //necesito un idUsuario
        
        response.sendRedirect("verReceta?rec="+id+"");
    }
}
