<%-- 
    Document   : miespacio_inbox.jsp
    Created on : Apr 28, 2017, 1:51:01 PM
    Author     : dizzy
--%>

<%@page import="java.util.Iterator"%>
<%@page import="modelo.Mensaje"%>
<%@page import="BD.MensajeBD"%>
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
        <%
            String htmlMensajes = "";
            String cuerpo = "";
        %>
        <title>Inbox</title>
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
                    $(".inbox").attr('id','selected');
                    $(".espacio").addClass('active');
                    
            });
                
            function mostrar(num,cuerpo){
                console.log($("#"+num+" .content").text());
                $("#"+num+" .content").text(cuerpo);
            }
            
        </script>
        
    </head>
    <body>
        
        <%
            ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) request.getAttribute("mensajes");
            Iterator<Mensaje> i = mensajes.iterator();
            Mensaje msg;
            String cuerpoCompleto;
            while(i.hasNext()){
                       
                msg = i.next();
                //si está leido tiene una clase distinta que los no leidos
                
                cuerpoCompleto = '"'+msg.getCuerpo()+'"';
                if(msg.getLeido()){
                //colocamos el enlace al mensaje
                    htmlMensajes += "<a id='"+msg.getId()+"' href='/pages/fetchMessage?id="+msg.getId()+"' class='list-group-item read'>";
                                
                    htmlMensajes += "<span class='envelope'><i class='fa fa-envelope-open'></i></span>";
                }
                else{
                    htmlMensajes += "<a id='"+msg.getId()+"' href='/pages/fetchMessage?id="+msg.getId()+"' class='list-group-item'>";
                    htmlMensajes += "<span class='envelope'><i class='fa fa-envelope-o'></i></span>";
                }
                
                //colocamos el nombre del emisor
                htmlMensajes += "<span class='name'>"+msg.getEmisor().getUsername()+"</span>";
                
                
                //colocamos un trozo del contenido, mientras el cuerpo sea mayor que 25 
                cuerpo = msg.getCuerpo();
                if(cuerpo.length() > 25){
                    htmlMensajes += "<span class='content'>"+cuerpo.substring(0, 25)+"...</span>";
                }
                else htmlMensajes += "<span class='content'>"+cuerpo+"...</span>";
                //por ultimo colocamos la fecha y el final del mensaje
                
                htmlMensajes += "<span class='badge'>"+msg.getFechaEnvio().toString()+"</span></a>";
            }

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
                
                <%= htmlMensajes %>
                
            </div>
        </div>
      </div>
  </div>
  </section>
    
 <%@include file="footer.html" %>
 </div>
</body>
</html>
