/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.RecetasBD;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class EliminarReceta extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Usuario user = (Usuario)request.getSession().getAttribute("usuario");
        if(user == null){
            response.sendRedirect("/pages/login.jsp");
        }
        
        int idReceta = Integer.parseInt(request.getParameter("id"));
        
        
        // si el id del usuario de la sesion es el mismo que el del autor de la receta
        if( RecetasBD.recogerReceta(idReceta).getAutor() == user.getId() ){ 
            try {
                RecetasBD.eliminarReceta(idReceta);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        request.getRequestDispatcher("/pages/miespacio_misrecetas.jsp").forward(request, response);
        
    }
}
