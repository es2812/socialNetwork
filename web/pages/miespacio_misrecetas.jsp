<%-- 
    Document   : miespacio_misrecetas
    Created on : Apr 28, 2017, 1:52:07 PM
    Author     : dizzy
--%>

<%@page import="modelo.Recetas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<%@page import="BD.RecetasBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Mis Recetas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">  
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/miespacio.css">
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script type="application/javascript">    
            $(document).ready(function(){
                    $(".recetas").attr('id','selected');
                    $(".espacio").addClass('active');
            });
            
            function avisoEliminar(id){
                if(window.confirm("¿Seguro que desea eliminar la receta? (Esta acción es irreversible)")){
                  window.location.replace("/pages/eliminarReceta?id="+id);
                }
            }
        </script>
    </head>
    <%
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        if(user == null){
            response.sendRedirect("/pages/login.jsp");
        }
        
        ArrayList<Recetas> recetas = RecetasBD.recogerRecetasUsuario(user.getId());

        String htmlRecetas = "";
        String titulo = "";
        for(int i=0; i<recetas.size(); i++){
            htmlRecetas +="<ul class='receta'>";
            
            titulo = recetas.get(i).getTitulo();
            
            if(titulo.length()>21){
                titulo = titulo.substring(0,21) + "<br>" + titulo.substring(21);
            }
            
            
            htmlRecetas += "<li class='name'><a href='/pages/verReceta?rec="+recetas.get(i).getId()+"'>"+titulo+"</li>";
            htmlRecetas += "<li class='date'>"+recetas.get(i).getFechaPubl().toString()+"</li>";
            
            htmlRecetas += "<br><li class='buttons'>";
                htmlRecetas += "<div class='wrap'><div class='likes'>"+recetas.get(i).getLikes()+"</div><img src='/images/Like-icon.png'></div>";
                htmlRecetas += "<a class='btn btn-primary' name='idRece' href='/pages/modificarReceta?id="+recetas.get(i).getId()+"'>";
                htmlRecetas += "<i class='fa fa-pencil-square-o fa-1x' aria-hidden='true'></i>Editar</a>";
                htmlRecetas += "<a class='btn btn-danger' onclick='avisoEliminar("+recetas.get(i).getId()+")'>";
                htmlRecetas += "<i class='fa fa-trash-o fa-1x' aria-hidden='true'></i>Eliminar</a></li>";
                
            htmlRecetas +="</ul>";
        }

        %>
    
    <body>  
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
      <header id="header">
  </header>
  <section id="navArea">
    
    
      <%@include file="cabecera.html"%>
      
      <%@include file="barranavMiespacio.html"%>

  <section id="recetas">
       <%=htmlRecetas%>
  </section>
    
  </section>
  
 
 <%@include file="footer.html" %>
 </div>
</body>
</html>
