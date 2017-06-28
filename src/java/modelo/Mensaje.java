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
public class Mensaje {
    private int id;
    private Usuario emisor;
    private Usuario receptor;
    private String cuerpo;
    private Date fechaEnvio;
    private boolean leido;
    
    public Mensaje(){
        id = 0;
        emisor = null;
        receptor = null;
        cuerpo = "";
        fechaEnvio = null;
        leido = false;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setEmisor(Usuario user){
        emisor = user;
    }
    public void setCuerpo(String cuerpo){
        this.cuerpo = cuerpo;
    }
    public void setReceptor(Usuario user){
        receptor = user;
    }
    
    public void setFechaEnvio(Date fecha){
        fechaEnvio = fecha;
    }
    
    public void setLeido(boolean leido){
        this.leido = leido;
    }
    
    public int getId(){
        return id;
    }
    
    public Usuario getEmisor(){
        return emisor;
    }
    
    public Usuario getReceptor(){
        return receptor;
    }
    
    public String getCuerpo(){
        return cuerpo;
    }
    
    public Date getFechaEnvio(){
        return fechaEnvio;
    }
    
    public boolean getLeido(){
        return leido;
    }
    
}
