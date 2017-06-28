<%-- 
    Document   : miespacio_editarperfil
    Created on : Apr 28, 2017, 1:16:36 PM
    Author     : dizzy
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Editar Perfil</title>
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
                    $(".edit").attr('id','selected');
                    $(".espacio").addClass('active');
            });
        </script>
        
    </head>
    <body>  
        <%
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario"); 
            if(usuario == null){
                response.sendRedirect("/pages/login.jsp");
            }
        
            if(usuario == null){System.out.println("ERROR");return;}
            
            String fotoPerfilActual = usuario.getFotoPerfil();
            
            String msgerror = "";
            if(request.getParameterNames().hasMoreElements()){
                String err = request.getAttribute("error").toString();
            
                if(err.equals("sql")){
                    msgerror = "Error interno. Por favor inténtelo de nuevo.";
                }
                else if(err.equals("usuario")){
                    msgerror = "Ese usuario ya está en uso.";
                }
                else if(err.equals("contrasenya")){
                    msgerror = "Las contraseñas no coinciden.";
                }
                else if(err.equals("none")){
                    msgerror = "Datos cambiados con éxito.";
                }
            }
        %>
        
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
      <header id="header">
  </header>
  <section id="navArea">
    
      <%@include file="cabecera.html"%>
      <%@include file="barranavMiespacio.html"%>
    
  

      <form id="perfil" method="post" action="editarPerfil" enctype="multipart/form-data">
      <img id="foto_perfil" src=<%= "'"+ fotoPerfilActual +"'" %> >
      <input type="file" name="fotoPerfil"/><br>
      <input type="text" placeholder="Nuevo nombre de usuario" name="user"><br>
      <input type="password" placeholder="Nueva contraseña" name="new_password">
      <input type="password" placeholder="Repita la nueva contraseña" name="new_password_rpt"><br>
      <div id="msgerror"><%= msgerror %></div><br>
      <input class="ok" type="submit" value="Guardar Cambios">
      <input class="cancel" type="button" value="Cancelar" onclick="location='principal.jsp'">
  </form>
    
  </section>
    
   
 <%@include file="footer.html"%>
 </div>
</body>
</html>

