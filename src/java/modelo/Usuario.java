/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author dizzy
 */
public class Usuario {
    
    private int id;
    private String username;
    private String contrasenya;
    private String email;
    private String fotoPerfil;
    private Date fechaAlta;
    boolean conectado;
    private int tipoUsuario;
    
    public Usuario(){
        id = 0;
        username = "";
        contrasenya = "";
        email = "";
        fotoPerfil = null;
        fechaAlta = null;
        conectado = false;
        tipoUsuario = 1;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setFotoPerfil(String fotoPerfil){
        this.fotoPerfil = fotoPerfil;
    }
    
    
    public void setContrasenya (String contrasenya){
        this.contrasenya = contrasenya;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setCorreo(String correo){
        email = correo;
    }
    
    public void setFechaAlta(Date fecha){
        fechaAlta = fecha;
    }
    
    public void setTipoUsuario(int tipo){
        tipoUsuario = tipo;
    }
    
    public void setConectado (boolean conectado){
        this.conectado = conectado;
    }
    
    public int getId(){
        return id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getCorreo(){
        return email;
    }
    
    public String getFotoPerfil(){
        return fotoPerfil;
    }
    
    
    public Date getFechaAlta(){
        return fechaAlta;
    }
    
    public String getTipoUsuario(){
        if(tipoUsuario==1) return "usuario";
        else return "admin";
    }
    
    public boolean getConectado(){
        return conectado;
    }
    
    public String getContrasenya(){
        return contrasenya;
    }
}