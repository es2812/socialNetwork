/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import BD.RecetasBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristina
 */
public class Recetas {

    private int id;
    private int autor;
    private String titulo;
    private int numCom;
    private String instrucciones;
    private ArrayList<String> ingredientes;
    private String foto;
    private String video;
    private int likes;
    private ArrayList<String> etiquetas;
    private Date fechaPubl;
    private String ingred;
    private String etiq;
    private String tipo;

    private ArrayList<Comentarios> comentarios;
    private ArrayList<Integer> amigos;

    public Recetas(){

        id = 0;
        autor = 0;
        titulo = "";
        numCom = 0;
        instrucciones = "";
        ingredientes = new ArrayList<String>();
        tipo = "";
        foto = "";
        video = "";
        likes = 0;
        etiquetas = new ArrayList<String>();
        fechaPubl = null;
        comentarios = null;
        amigos = null;
    }

    public Recetas(int id, int autor, String titulo, int numCom, String instrucciones,
            ArrayList<String> ingredientes, String foto, String video, int likes, ArrayList<String> etiquetas, Date fechaPubl, ArrayList<Comentarios> comentarios,
            ArrayList<Integer> amigos){

        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.numCom = numCom;
        this.instrucciones = instrucciones;
        this.ingredientes = ingredientes;
        this.foto = foto;
        this.video = video;
        this.likes = likes;
        this.etiquetas = etiquetas;
        this.fechaPubl = fechaPubl;
        this.comentarios = comentarios;
        this.amigos = amigos;
    }

    public Recetas(int id, String titulo, String instrucciones, String foto, String video){
        this.id = id;
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.foto = foto;
        this.video = video;
    }

    public int getId(){return id;}
    public int getAutor(){return autor;}
    public String getTitulo(){return titulo;}
    public int getNumCom(){return numCom;}
    public String getTipo(){return tipo;}
    public String getInstrucciones(){return instrucciones;}
    public ArrayList<String> getIngredientes(){return ingredientes;}
    public String getFoto(){return foto;}
    public String getVideo(){return video;}
    public int getLikes(){return likes;}
    public ArrayList<String> getEtiquetas(){return etiquetas;}
    public Date getFechaPubl(){return fechaPubl;}
    public ArrayList<Comentarios> getComentarios(){return comentarios;}
    public ArrayList<Integer> getAmigos(){return amigos;}
    public String getEtiq(){return this.etiq;};
    public String getIngr(){return this.ingred;};

    public void setAutor(int autor){this.autor = autor;}
    public void setComensales(int comensales){this.numCom = comensales; }
    public void setPreparacion(String preparacion) {this.instrucciones = preparacion;}
    public void setTitulo (String titulo){this.titulo = titulo;}
    public void setEtiquetas (ArrayList etiquetas){this.etiquetas = etiquetas;}
    public void setFecha(Date fecha){this.fechaPubl = fecha;}
    public void setLikes(int likes){this.likes = likes;}
    public void setInstrucciones(String instrucciones){this.instrucciones = instrucciones;}
    public void setFoto(String foto){this.foto = foto;}
    public void setVideo(String video){this.video = video;}
    public void setId(int id) {this.id = id;}
    public void setComentarios (ArrayList<Comentarios> comentarios){this.comentarios = comentarios;}
    public void setAmigos (ArrayList<Integer> amigos){this.amigos = amigos;}
    public void setEtiq(String etiq){this.etiq=etiq;}
    public void setIngr(String ingred){this.ingred=ingred;}
    public void setTipo(String tipo){this.tipo=tipo;}
    
    public void ayadirIngrediente(String ingrediente){
        this.ingredientes.add(ingrediente);
    } 
    
    public void ayadirEtiqueta(String etiqueta){
        this.etiquetas.add(etiqueta);
    } 
   

}
