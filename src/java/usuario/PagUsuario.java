/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.RecetasBD;
import BD.UsuarioBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.Recetas;
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class PagUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
                    {
           int id = Integer.parseInt(request.getParameter("usuario"));
        
        Usuario usuario = new Usuario();
        try {
            usuario = UsuarioBD.getUsuarioById(id);
        } catch (SQLException ex) {
            Logger.getLogger(PagUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("usuarioPag",usuario);
        
        ArrayList<Recetas> recetas = null;
        
        try {
            recetas = RecetasBD.recogerRecetasUsuario(usuario.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        request.setAttribute("recetasPag",recetas);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/pagusuario.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
           ex.printStackTrace();
        }
        
    }
}
