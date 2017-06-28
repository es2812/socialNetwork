/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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

/**
 *
 * @author Cristina
 */
public class Comentarios {
    
    private int id;
    private int receta;
    private int autor;
    private String texto;
    private Date fecha;
        
    public Comentarios(){
                
        id = 0;
        receta = 0;
        autor = 0;
        texto = "";
        fecha = null;
    }
    
    public Comentarios(int id, int receta, int autor, String texto, Date fecha){
        
        this.id = id;
        this.receta = receta;
        this.autor = autor;
        this.texto = texto;
        this.fecha = fecha;
    }
    
    public int getId(){return id;}
    public int getReceta(){return receta;}
    public int getAutor(){return autor;}
    public String getTexto(){return texto;}
    public Date getFecha(){return fecha;}
    
 }
