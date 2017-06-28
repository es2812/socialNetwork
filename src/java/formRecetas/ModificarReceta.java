package formRecetas;

import BD.RecetasBD;
import BD.UsuarioBD;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Recetas;
import modelo.Usuario;
import java.util.ArrayList;
/**
 *
 * @author marta
 */
@WebServlet("/modificarReceta")
public class ModificarReceta extends HttpServlet {       
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws IOException, ServletException{ 
        
        request.setCharacterEncoding("UTF-8");
        
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");         
        if(usuario == null){
            response.sendRedirect("/pages/login.jsp");
        }              
        int autor = usuario.getId();
        Recetas receta = new Recetas();
        int idReceta  = Integer.parseInt(request.getParameter("id"));
        receta = RecetasBD.recogerReceta(idReceta);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/modificarReceta.jsp");
            
        request.setAttribute("titulo", receta.getTitulo());
        request.setAttribute("comensales", Integer.toString(receta.getNumCom()));        
        request.setAttribute("ingredientes", receta.getIngredientes());
        request.setAttribute("instrucciones", receta.getInstrucciones());
        request.setAttribute("foto", receta.getFoto()); 
        request.setAttribute("ffoto", receta.getFoto());   
        request.setAttribute("etiquetas",receta.getEtiq());
        request.setAttribute("id", Integer.toString(idReceta));
        if(receta.getVideo()!=null){
           
            request.setAttribute("video", receta.getVideo());
        }
        //      
         
        rd.forward(request, response);
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
