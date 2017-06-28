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
import modelo.Usuario;

/**
 *
 * @author dizzy
 */
public class UsuarioBD {
   
    public static Usuario getInstanciaUsuario(String username)
        throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        Statement statement = conex.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE USERNAME='"+username+"'");
        
        Usuario resultado = new Usuario();
                
        products.next();
        
        resultado.setId(products.getInt("idUsuario"));
        resultado.setUsername(username);
        resultado.setCorreo(products.getString("email"));
        resultado.setFotoPerfil(products.getString("fotoPerfil"));
        resultado.setFechaAlta(products.getDate("fechaAlta"));
        resultado.setConectado(products.getBoolean("conectado"));
        resultado.setTipoUsuario(products.getInt("tipousuario"));
        
        pool.freeConnection(conex);
        return resultado;
    }
    
    
    public static boolean checkExisteUsername(String username) throws SQLException{   
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE U.USERNAME = '"+username+"'");
        boolean result = products.next();
        pool.freeConnection(connection);  
        return result;
    }
    
    public static boolean checkExisteCorreo(String email) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE U.EMAIL = '"+email+"'");
        boolean result = products.next();
        pool.freeConnection(connection); 
        return result;
    }
    
    //Registra los datos en la instancia en la DB
    public static Usuario addUsuario(String email, String username, String contrasenya, Date fechaAlta, int tipoUsuario)
        throws SQLException {
        String query = "INSERT INTO USUARIO (EMAIL,USERNAME, CONTRASENYA, FOTOPERFIL, FECHAALTA, TIPOUSUARIO) VALUES ('";
        query += email;
        query += "','";
        query += username;
        query += "','";
        query += contrasenya;
        query += "','/images/default.jpg','";
        query += fechaAlta;
        query += "',";
        query += tipoUsuario;
        query += ")";        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);        
        //una vez ejecutada la query tendremos que obtener el id del usuario recien registrado y ponerlo en la instancia actual
        statement = connection.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE U.USERNAME ='"+username+"'");
        Usuario resultado = new Usuario();
                
        products.next();
        
        resultado.setId(products.getInt("idusuario"));
        resultado.setUsername(username);
        resultado.setCorreo(email);
        resultado.setFechaAlta(fechaAlta);
        resultado.setFotoPerfil("/images/default.jpg");
        resultado.setTipoUsuario(tipoUsuario);
        
        pool.freeConnection(connection);
        return resultado;
             
    }
    
    
    //Dado un id (usuario o email) y una contrasenya, comprueba que son correctas 
    //y en ese caso devuelve la instancia de Usuario con los datos de la DB para ese usuario.
    public static Usuario logUsuario(String id, String contrasenya) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE (U.USERNAME='"+id+"' OR U.EMAIL ='"+id+"') AND U.CONTRASENYA='"+contrasenya+"'");
        if(products.next()){
         statement = connection.createStatement();
         statement.executeUpdate("UPDATE USUARIO SET CONECTADO=TRUE WHERE IDUSUARIO="+products.getInt("IDUSUARIO"));
         Usuario resultado = new Usuario();
                        
         resultado.setId(products.getInt("idusuario"));
         resultado.setUsername(products.getString("username"));
         resultado.setCorreo(products.getString("email"));
         resultado.setFotoPerfil(products.getString("fotoPerfil"));
         resultado.setFechaAlta(products.getDate("fechaAlta"));
         resultado.setConectado(products.getBoolean("conectado"));
         resultado.setTipoUsuario(products.getInt("tipousuario"));
         resultado.setContrasenya(products.getString("contrasenya"));
         pool.freeConnection(connection);
         return resultado;
        }
        else{
            pool.freeConnection(connection);
            return null;
        }
    }
   
    //Actualiza los datos de la DB con los datos actuales en la instancia.
    public static void updateUser(String username, String contrasenya, String fotoPerfil, int id)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        
        statement.executeUpdate("UPDATE USUARIO U SET USERNAME='"+username+"', CONTRASENYA='"+contrasenya+"',FOTOPERFIL ='"+fotoPerfil+"' WHERE U.IDUSUARIO="+id);
        pool.freeConnection(connection);
    }
    
    public static Usuario getUsuarioById(int id)
            throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet products = statement.executeQuery("SELECT * FROM USUARIO U WHERE IDUSUARIO="+id);
        Usuario resultado = new Usuario();
                
        products.next();
        
        resultado.setId(id);
        resultado.setUsername(products.getString("username"));
        resultado.setCorreo(products.getString("email"));
        resultado.setFotoPerfil(products.getString("fotoPerfil"));
        resultado.setFechaAlta(products.getDate("fechaAlta"));
        resultado.setConectado(products.getBoolean("conectado"));
        resultado.setTipoUsuario(products.getInt("tipousuario"));
        
        pool.freeConnection(connection);
        return resultado;
    }
    
    public static void desconectar(int id)
        throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE USUARIO U SET CONECTADO=false WHERE U.IDUSUARIO="+id);
        pool.freeConnection(connection);
    }
    
    public static ArrayList<Integer> recogerAmigos(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        ArrayList<Integer> amigos = new ArrayList<Integer>();
        
        try {
            Statement statement = conex.createStatement();
            ResultSet products = statement.executeQuery("SELECT A.USUARIOSEGUIDOR, A.USUARIOSEGUIDO FROM SEGUIR A WHERE A.USUARIOSEGUIDOR="+ id +"");
            while(products.next()){
                amigos.add(products.getInt("USUARIOSEGUIDO"));
            }
            
            pool.freeConnection(conex);
            return amigos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        pool.freeConnection(conex);
        return null;
    }
    
    public static boolean siguiendo (int seguidor, int seguido){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        boolean result = false; 
        try {
            Statement statement = conex.createStatement();
            ResultSet products = statement.executeQuery("SELECT * FROM SEGUIR A WHERE A.USUARIOSEGUIDOR="+ seguidor +" AND A.USUARIOSEGUIDO="+ seguido);
            result = products.next();
            
            pool.freeConnection(conex);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public static void anyadirSeguido(int seguidor, int seguido) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        statement.executeUpdate("INSERT INTO SEGUIR(USUARIOSEGUIDOR,USUARIOSEGUIDO) VALUES ("+seguidor+","+seguido+")");
        pool.freeConnection(conex);
    }
    
    public static void eliminarSeguido(int seguidor, int seguido) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        Statement statement = conex.createStatement();
        statement.executeUpdate("DELETE FROM SEGUIR S WHERE S.USUARIOSEGUIDOR="+seguidor+" AND S.USUARIOSEGUIDO="+seguido);
        pool.freeConnection(conex);
    }
    
    
    public static String obtenerAutor(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conex = pool.getConnection();
        
        try {
            Statement statement = conex.createStatement();
            ResultSet products = statement.executeQuery("SELECT U.USERNAME FROM USUARIO U WHERE U.IDUSUARIO="+ id +"");
            if(products.next()){
                String resultado = products.getString("username");
                pool.freeConnection(conex);
                return resultado;
            }
            else{
                pool.freeConnection(conex);
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        pool.freeConnection(conex);
        return null;
    }
    
    
}
