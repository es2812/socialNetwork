/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verRecetas;

import BD.RecetasBD;
import BD.UsuarioBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Recetas;
import modelo.Usuario;
 /**
  *
  * @author Cristina
  */
 @WebServlet("/verReceta")
 public class VerReceta extends HttpServlet {
     public void doGet(HttpServletRequest req, HttpServletResponse resp) 
             throws IOException, ServletException{
        String encoding = req.getCharacterEncoding();
        if (encoding==null) {
            req.setCharacterEncoding("UTF-8");
        }
        Usuario user = (Usuario) req.getSession().getAttribute("usuario");
        
        if(user == null){
            resp.sendRedirect("/pages/login.jsp");
        }
         
        int id = Integer.parseInt(req.getParameter("rec"));
                
        Recetas rec = new Recetas();
        rec = RecetasBD.recogerReceta(id);
         
        RequestDispatcher rd = req.getRequestDispatcher("/pages/receta.jsp");
        
        req.setAttribute("autor", UsuarioBD.obtenerAutor(rec.getAutor()));
        req.setAttribute("idautor", rec.getAutor());
        req.setAttribute("numCom", rec.getNumCom());
        req.setAttribute("likes", rec.getLikes());
        req.setAttribute("titulo", rec.getTitulo());
        req.setAttribute("ingredientes", rec.getIngredientes());
        req.setAttribute("instrucciones", rec.getInstrucciones());
        req.setAttribute("foto", rec.getFoto());
        req.setAttribute("etiquetas", rec.getEtiquetas());
        req.setAttribute("fechaPubl", rec.getFechaPubl());
        req.setAttribute("id", id);
        req.setAttribute("comentarios", rec.getComentarios());
        
        boolean existeGustado = false, existeFavorito = false;
        
         try {
             existeGustado = RecetasBD.existeGustado(user.getId(),id);
             existeFavorito = RecetasBD.existeFavorito(user.getId(),id);
        
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         
        req.setAttribute("gustado", existeGustado);
        req.setAttribute("favorito", existeFavorito);
        rd.forward(req, resp);
     }     
 }
