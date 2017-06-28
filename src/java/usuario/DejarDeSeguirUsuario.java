/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import BD.UsuarioBD;
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
public class DejarDeSeguirUsuario extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idSeguido = Integer.parseInt(request.getParameter("id"));
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        if(user == null){
            response.sendRedirect("/pages/login.jsp");
        }
        
        int idSeguidor = user.getId();
        
        try {
            UsuarioBD.eliminarSeguido(idSeguidor,idSeguido);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("/pages/accederPagUsuario?usuario="+idSeguido).forward(request, response);
    }
}
