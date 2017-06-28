/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;


import data.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import modelo.Comentarios;
import modelo.Recetas;

/**
 *
 * @author Cristina
 */
public class RecetasBD {

    public static Recetas recogerReceta(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        

        String [] ingre, eti;
        ArrayList<Comentarios> com = new ArrayList<Comentarios>();

        try {
            Statement statement = conex.createStatement();
            ResultSet products = statement.executeQuery("SELECT * FROM RECETA R WHERE R.idreceta="+ id +"");
            
            if(products.next()){

                Recetas recetas = new Recetas();
              
                recetas.setAutor(products.getInt("autor"));
                recetas.setComensales(products.getInt("numerocomensales"));
                recetas.setLikes(products.getInt("likes"));
                recetas.setTitulo(products.getString("titulo"));
                recetas.setEtiq(products.getString("etiquetas"));
                recetas.setInstrucciones(products.getString("intrucciones"));
                recetas.setFoto(products.getString("foto"));
                recetas.setVideo(products.getString("video"));
                recetas.setIngr(products.getString("ingredientes"));
                
                ingre = new String[products.getString("ingredientes").split(";").length];
                ingre = products.getString("ingredientes").split(";");
                for(int i=0;i<ingre.length;i++){
                    recetas.ayadirIngrediente(ingre[i]);
                }
                
               
                recetas.setFecha(products.getDate("fechapubl"));
                recetas.setComentarios(ComentariosBD.recogerComentarios(id));
                        
                pool.freeConnection(conex);
                
                return recetas;
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pool.freeConnection(conex);
        
        return null;
    }
    
    public static ArrayList<Recetas> recogerRecetasAmigos(int id){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        ArrayList<Integer> amigos = new ArrayList<Integer>();
        amigos = UsuarioBD.recogerAmigos(id);
        ArrayList<Recetas> recetas = new ArrayList<Recetas>();
        
        try {
            Statement statement = conex.createStatement();
            
            for(int i=0;i<amigos.size();i++){
                ResultSet products = statement.executeQuery("SELECT * FROM RECETA R WHERE R.autor="+ amigos.get(i) +"");

                while(products.next()){

                    Recetas receta = new Recetas();

                    receta.setId(products.getInt("idReceta"));
                    receta.setAutor(products.getInt("autor"));
                    receta.setTitulo(products.getString("titulo"));
                    receta.setInstrucciones(products.getString("intrucciones"));
                    receta.setFoto(products.getString("foto"));
                    receta.setVideo(products.getString("video"));
                    receta.setFecha(products.getDate("fechapubl"));

                    recetas.add(receta);
                }
            }
            
            Collections.sort(recetas, new Comparator<Recetas>(){
                @Override
                public int compare(Recetas r1, Recetas r2) {
                        return r1.getFechaPubl().compareTo(r2.getFechaPubl());
                }

            });
            
            
            pool.freeConnection(conex);
            
            return recetas;
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pool.freeConnection(conex);
        
        return null;
        
    }    
    
    public static ArrayList<Recetas> recogerFavoritos(int id){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        ArrayList<Recetas> recetas = new ArrayList<Recetas>();
        
        try {
            Statement statement = conex.createStatement();
            
            ResultSet products = statement.executeQuery("SELECT * FROM RECETA R INNER JOIN FAVORITO F on r.idreceta=f.idreceta WHERE F.idusuario="+id+"");

            while(products.next()){

                Recetas receta = new Recetas();

                receta.setId(products.getInt("idReceta"));
                receta.setAutor(products.getInt("autor"));
                receta.setTitulo(products.getString("titulo"));
                receta.setInstrucciones(products.getString("intrucciones"));
                receta.setFoto(products.getString("foto"));
                receta.setVideo(products.getString("video"));
                receta.setFecha(products.getDate("fechapubl"));

                recetas.add(receta);
            }
            
            
            pool.freeConnection(conex);
            
            return recetas;
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pool.freeConnection(conex);
        
        return null;
        
    }    
    
     public static ArrayList<Recetas> descubrirRecetas(int id){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        ArrayList<Recetas> recetas = new ArrayList<Recetas>();
        
        try {
            Statement statement = conex.createStatement();
            
            ResultSet products = statement.executeQuery("SELECT * FROM RECETA R WHERE R.autor NOT IN (SELECT A.USUARIOSEGUIDO FROM SEGUIR A WHERE A.USUARIOSEGUIDOR="+ id +") AND R.autor!="+id+" ORDER BY R.fechapubl");

            while(products.next()){

                Recetas receta = new Recetas();

                receta.setId(products.getInt("idReceta"));
                receta.setAutor(products.getInt("autor"));
                receta.setTitulo(products.getString("titulo"));
                receta.setInstrucciones(products.getString("intrucciones"));
                receta.setFoto(products.getString("foto"));
                receta.setVideo(products.getString("video"));
                receta.setFecha(products.getDate("fechapubl"));

                recetas.add(receta);
            }
            
            
            pool.freeConnection(conex);
            
            return recetas;
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pool.freeConnection(conex);
        
        return null;
        
    }    
    
    public static void anyadirLike(int id, int autor){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            statement.executeUpdate("INSERT INTO GUSTADO (IDUSUARIO, IDRECETA) VALUES ("+autor+","+id+")");
            Recetas rec = recogerReceta(id);
            int likes = rec.getLikes() + 1;
            statement.executeUpdate("UPDATE RECETA SET LIKES ="+likes+" WHERE IDRECETA="+id+"");
             
            pool.freeConnection(conex);

        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void anyadirFav(int id, int autor){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            statement.executeUpdate("INSERT INTO FAVORITO (IDUSUARIO, IDRECETA) VALUES ("+autor+","+id+")");
        
            pool.freeConnection(conex);
        
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void eliminarLike(int id, int autor){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            statement.executeUpdate("DELETE FROM GUSTADO WHERE IDUSUARIO="+autor+" AND IDRECETA="+id);
            Recetas rec = recogerReceta(id);
            int likes = rec.getLikes() - 1;
            statement.executeUpdate("UPDATE RECETA SET LIKES ="+likes+" WHERE IDRECETA="+id+"");
             
            pool.freeConnection(conex);

        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarFav(int id, int autor){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            statement.executeUpdate("DELETE FROM FAVORITO WHERE IDUSUARIO="+autor+" AND IDRECETA="+id);
        
            pool.freeConnection(conex);
        
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public static ArrayList<Recetas> filtrarRecetas(String etiqueta) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();

        ArrayList<Recetas> listaRecetas = new ArrayList<Recetas>();

        String query = "SELECT idreceta, titulo, intrucciones, foto, video FROM receta WHERE UPPER(etiquetas) LIKE UPPER ('%"+etiqueta+"%') ";

        Statement statement = conex.createStatement();
        ResultSet products = statement.executeQuery(query);
        
        

        while(products.next()){
            Recetas recetas = new Recetas();
            recetas.setId(products.getInt ("idreceta"));
            recetas.setTitulo(products.getString ("titulo"));
            recetas.setInstrucciones(products.getString ("intrucciones"));
            recetas.setFoto(products.getString ("foto"));
            recetas.setVideo(products.getString("video"));
            listaRecetas.add(recetas);
        }

        products.close();
        statement.close();
        
        pool.freeConnection(conex);
        
        return listaRecetas;

    }

    public static void subirReceta(Recetas recetaSubir) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        try {
            Statement statement = conex.createStatement();
            String query = "INSERT INTO RECETA (AUTOR,TITULO,NUMEROCOMENSALES,INTRUCCIONES,INGREDIENTES,FOTO,VIDEO,ETIQUETAS,FECHAPUBL) VALUES (";
            query += recetaSubir.getAutor()+",'";
            query += recetaSubir.getTitulo()+"',";
            query += recetaSubir.getNumCom()+",'";
            query += recetaSubir.getInstrucciones()+"','";
            query += recetaSubir.getIngr()+"','";
            query += recetaSubir.getFoto()+"','";
            query += recetaSubir.getVideo()+"','";
            query += recetaSubir.getEtiq()+"','";
            query += recetaSubir.getFechaPubl();
            query += "')";
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            //int result = statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            rs.next();
            int id = rs.getInt(1);    
            recetaSubir.setId(id);
           
            pool.freeConnection(conex);            
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     public static void actualizarReceta(Recetas recetaSubir) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        try {
            Statement statement = conex.createStatement();
            String query="";
                query = "UPDATE RECETA SET TITULO ='"+recetaSubir.getTitulo()+"', ";
                query += "NUMEROCOMENSALES="+recetaSubir.getNumCom()+",INTRUCCIONES='"+recetaSubir.getInstrucciones()+"', ";
                query += "INGREDIENTES='"+recetaSubir.getIngr()+"', ";
                query += "FOTO='"+recetaSubir.getFoto()+"', VIDEO='"+recetaSubir.getVideo()+"', ";
                query += "ETIQUETAS='"+recetaSubir.getEtiq()+"' WHERE IDRECETA ="+recetaSubir.getId();
          
            statement.executeUpdate(query);
                     
            pool.freeConnection(conex);            
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
    public static ArrayList<Recetas> crearRanking() throws SQLException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        ArrayList<Recetas> rankingRecetas = new ArrayList<Recetas>();
        
        String query = "SELECT * FROM receta ORDER BY likes DESC";
        
        Statement statement = conex.createStatement();
        statement.setMaxRows(10); 
        ResultSet products = statement.executeQuery(query);
        int i=0;
        while(products.next() && i<10){
            Recetas recetas = new Recetas();
            recetas.setId(products.getInt ("idreceta"));
            recetas.setTitulo(products.getString ("titulo"));
            recetas.setAutor(products.getInt("autor"));
            recetas.setFoto(products.getString("foto"));
            recetas.setLikes(products.getInt("likes"));
            rankingRecetas.add(recetas);
            i++;
        }

        products.close();
        statement.close();
        
        pool.freeConnection(conex);
        
        return rankingRecetas;
    }
    
    public static ArrayList<Recetas> recogerRecetasUsuario(int id) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        ArrayList<Recetas> recetas = new ArrayList<Recetas>();
        
        Statement statement = conex.createStatement();
            
        
        ResultSet products = statement.executeQuery("SELECT * FROM RECETA R WHERE R.autor="+ id +"");
        
        while(products.next()){
            Recetas receta = new Recetas();
    
            receta.setId(products.getInt("idReceta"));
            receta.setAutor(products.getInt("autor"));
            receta.setTitulo(products.getString("titulo"));
            receta.setInstrucciones(products.getString("intrucciones"));
            receta.setFoto(products.getString("foto"));
            receta.setVideo(products.getString("video"));
            receta.setFecha(products.getDate("fechapubl"));
            receta.setLikes(products.getInt("likes"));
            recetas.add(receta);
        }
        return recetas;
            
    }
    
    public static void eliminarReceta(int idReceta) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        
        statement.executeUpdate("DELETE FROM RECETA R WHERE R.IDRECETA="+idReceta);
    }
    
    
    public static boolean existeGustado(int usuario, int receta) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        
        ResultSet resultados = statement.executeQuery("SELECT * FROM GUSTADO WHERE IDUSUARIO="+usuario+" AND IDRECETA="+receta);
        return resultados.next();
    }
    
    public static boolean existeFavorito(int usuario, int receta) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        
        ResultSet resultados = statement.executeQuery("SELECT * FROM FAVORITO WHERE IDUSUARIO="+usuario+" AND IDRECETA="+receta);
        return resultados.next();
    }       
}