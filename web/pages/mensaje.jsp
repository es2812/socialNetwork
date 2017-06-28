<%-- 
    Document   : miespacio_inbox.jsp
    Created on : Apr 28, 2017, 1:51:01 PM
    Author     : dizzy
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.Mensaje"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    
    <head>
        <title>Inbox</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">  
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/miespacio.css">
        <link rel="stylesheet" type="text/css" href="../css/leer_mensaje.css">
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script type="application/javascript">    
            $(document).ready(function(){
                    $(".inbox").attr('id','selected');
                    $(".espacio").addClass('active');
            });
        </script>
        
    </head>
    <body>
        
        <%
            Mensaje msg = (Mensaje) request.getAttribute("mensaje");
            String cuerpo = msg.getCuerpo();
            String emisor = msg.getEmisor().getUsername();
            String fechaEnvio = msg.getFechaEnvio().toString();
            String fotoPerfil = msg.getEmisor().getFotoPerfil();
            String linkPerfil = "/pages/accederPagUsuario?usuario="+msg.getEmisor().getId();
            %>
        
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
      <header id="header">
  </header>
  <section id="navArea">
    
    
      <%@include file="cabecera.html"%>
      
      <%@include file="barranavMiespacio.html"%>

  <div id="inbox">
      <div class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <div class="list-group">
                
               <div class="list-group-item">
                   <a id="back" href="/pages/fetchInbox">< Volver</a><br>
                   <img id="foto_perfil" src=<%= "'"+fotoPerfil+"'" %> />
                   <a id="user" href= <%= "'"+linkPerfil+"'" %>><%=emisor %></a>
                   <span class="badge"><%=fechaEnvio %></span><br>
                   <span id="content"><%=cuerpo %></span>
               </div>
                
            </div>
        </div>
      </div>
  </div>
  </section>
    
 <%@include file="footer.html" %>
 </div>
</body>
</html>
