package formRecetas;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.http.Part;
import modelo.Recetas;
import modelo.Usuario;
import BD.RecetasBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marta
 */
@MultipartConfig
public class ActualizarReceta extends HttpServlet {       
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws IOException, ServletException{ 
        
        request.setCharacterEncoding("UTF-8");
        
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario"); 
        
        if(usuario == null){
            response.sendRedirect("/pages/login.jsp");
        }    
        int autor = usuario.getId();
        
        Recetas receta = new Recetas();              
        receta.setId(Integer.parseInt(request.getParameter("idReceta")));
        receta.setAutor(autor);
        receta.setTitulo(request.getParameter("titulo"));
        receta.setComensales(Integer.parseInt(request.getParameter("comensales")));
        receta.setPreparacion(request.getParameter("prep"));
        String etiquetas = request.getParameter("etiqueta");
        
        
        String[] etiq = etiquetas.split(";");
        String etiqValue="";
        for(int i=0;i<etiq.length;i++){
            etiqValue+=etiq[i]+";";     
            
        }
        
        receta.setEtiq(etiqValue);        
    Enumeration <String> parameterNames = request.getParameterNames() ;
    String ingredientes;
    String ingValues="";
    while (parameterNames.hasMoreElements()) {
        ingredientes = parameterNames.nextElement();        
        if("Ing".equals(ingredientes.substring(0,3))){
            String value = request.getParameter(ingredientes);
            if(!value.isEmpty()){
                ingValues += value+";";   
            }
        }
     
    }  
    receta.setIngr(ingValues);       
    Part filePartFoto = request.getPart("foto");
    if(filePartFoto.getSize()!=0){
            File uploads = new File(getServletContext().getRealPath("/subidas"));            
            File file = File.createTempFile("fotoReceta-",".jpg",uploads);        
            try(InputStream fileContent = filePartFoto.getInputStream()){
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }                
            String foto = "/subidas/"+file.getName();
           receta.setFoto(foto);
    }else{
        receta.setFoto(request.getParameter("ffoto"));
    }    
    /*Part filePartVideo = request.getPart("video");
    if(filePartVideo.getSize()!=0){
        File uploads = new File(getServletContext().getRealPath("/subidas"));            
        File file = File.createTempFile("VideoReceta-",".avi",uploads);        
        try(InputStream fileContent = filePartVideo.getInputStream()){
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }                
        String video = "/subidas/"+file.getName();
       receta.setVideo(video);
    }else{
        receta.setVideo("../default");
    }*/
        try {
            RecetasBD.actualizarReceta(receta);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
 
    response.sendRedirect("/pages/verReceta?rec="+receta.getId());
     
      

      
        
    }
}