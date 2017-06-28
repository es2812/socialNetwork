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
import modelo.Mensaje;

/**
 *
 * @author dizzy
 */
public class MensajeBD {    
    public static ArrayList<Mensaje> getMensajesUsuario(int usuario)
        throws SQLException{        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM MENSAJE M WHERE M.IDRECEPTOR = "+usuario+"ORDER BY M.FECHAENVIO DESC");
        
        ArrayList<Mensaje> resultado = new ArrayList();
        
        while(products.next()){
            
            Mensaje msg = new Mensaje();
                     
            msg.setId(products.getInt("idmensaje"));
            msg.setEmisor(UsuarioBD.getUsuarioById(products.getInt("idemisor")));
            msg.setCuerpo(products.getString("cuerpo"));
            msg.setFechaEnvio(products.getDate("fechaenvio"));
            msg.setLeido(products.getBoolean("leido"));
            
            resultado.add(msg);
        }
        
        pool.freeConnection(conex);
        return resultado;
        
    }
    
    public static Mensaje getMensaje(int idMensaje)
        throws SQLException{        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM MENSAJE M WHERE M.IDMENSAJE = "+idMensaje);
        
        products.next();
        
        //marcamos el mensaje como leido si no lo estaba ya:
        if(!products.getBoolean("leido")){
            Statement statement2 = conex.createStatement();
            statement2.executeUpdate("UPDATE MENSAJE SET LEIDO=true WHERE IDMENSAJE = "+idMensaje);
        
        }
        Mensaje resultado = new Mensaje();
        
        resultado.setId(idMensaje);
        resultado.setEmisor(UsuarioBD.getUsuarioById(products.getInt("idemisor")));
        resultado.setReceptor(UsuarioBD.getUsuarioById(products.getInt("idreceptor")));
        resultado.setCuerpo(products.getString("cuerpo"));
        resultado.setLeido(true);
        resultado.setFechaEnvio(products.getDate("fechaEnvio"));   
        
        pool.freeConnection(conex);
        return resultado;
        
    }
    
    public static void registraMensaje(int emisor, int receptor, String cuerpo, Date fechaEnvio, boolean leido)
        throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        String query = "INSERT INTO MENSAJE(IDEMISOR,IDRECEPTOR,CUERPO,FECHAENVIO,LEIDO) VALUES (";
        
        query += emisor+",";
        query += receptor+",'";
        query += cuerpo+"','";
        query += fechaEnvio+"',";
        query += leido+")";
        
        statement.executeUpdate(query);
        
        pool.freeConnection(conex);
    }
    
}
