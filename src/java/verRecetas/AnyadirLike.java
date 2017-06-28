/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verRecetas;

import BD.RecetasBD;
import java.io.IOException;
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
public class AnyadirLike extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{
        
        int id = Integer.parseInt(req.getParameter("rec"));
        
        HttpSession sesion = req.getSession();
        Usuario usu = (Usuario) sesion.getAttribute("usuario");
        
        RecetasBD.anyadirLike(id, usu.getId());
        
        resp.sendRedirect("verReceta?rec="+id+"");
    }
    
}
