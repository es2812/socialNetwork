/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaRecetas;

import BD.RecetasBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Recetas;

/**
 *
 * @author raqlope
 */
public class filtrarRecetas extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException{ 
        
        //request
        String etiqueta = req.getParameter("etiqueta");
        RecetasBD receta = new RecetasBD();
        ArrayList<Recetas> lista = new ArrayList<Recetas>();
        RequestDispatcher rd;
        
        try {
            lista = receta.filtrarRecetas(etiqueta);
            
            req.setAttribute("lista", lista);
            
            if(lista.isEmpty()){
                //redirect a un jsp con un texto de que no existe la etiqueta
                String msg = "No se han encontrado resultados para "+etiqueta;
                rd = req.getRequestDispatcher("/pages/busqueda.jsp?error="+msg);
                rd.forward(req, resp);
            } 
            else{
                rd = req.getRequestDispatcher("/pages/busqueda.jsp?etiqueta="+etiqueta);
                rd.forward(req, resp);
            }
        }catch(SQLException ex){
                ex.printStackTrace();
                rd = req.getRequestDispatcher("/pages/busqueda.jsp?error=sql"+ex.getMessage());
                rd.forward(req, resp);
        }
              
        
    }
}
