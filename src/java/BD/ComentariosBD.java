/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import data.ConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Comentarios;
import modelo.Recetas;

/**
 *
 * @author Cristina
 */
public class ComentariosBD {
    
     public static ArrayList<Comentarios> recogerComentarios(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        ArrayList<Comentarios> comentarios = new ArrayList<Comentarios>();
        
        try {
            Statement statement = conex.createStatement();
            ResultSet products = statement.executeQuery("SELECT C.IDCOMENTARIO, C.IDRECETA, C.IDAUTOR, C.TEXTO, C.FECHAPUBL FROM COMENTARIORECETA C WHERE C.IDRECETA="+ id +"");
            while(products.next()){
                comentarios.add(new Comentarios(products.getInt("idcomentario"),products.getInt("idreceta"), products.getInt("idautor"), products.getString("texto"), products.getDate("FECHAPUBL")));
            }
            
            pool.freeConnection(conex);
            
            return comentarios;
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pool.freeConnection(conex);
        
        return null;
    }   
    
    public static void anyadirComentario(int autor, int receta, String texto){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            statement.executeUpdate("INSERT INTO COMENTARIORECETA (IDRECETA, IDAUTOR, TEXTO, FECHAPUBL) VALUES ("+receta+","+autor+",'"+texto+"','"+date+"')");
            
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
