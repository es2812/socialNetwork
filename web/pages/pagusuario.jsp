<%-- 
    Document   : pagusuario
    Created on : Apr 30, 2017, 8:34:30 PM
    Author     : dizzy
--%>

<%@page import="BD.UsuarioBD"%>
<%@page import="modelo.Recetas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
        <%
            Usuario user = (Usuario) request.getAttribute("usuarioPag");
            String fotoPerfil = "";
            String username = "";
            int idUsuario = user.getId();
            String siguiendo = "false";
            //TODO: Lista de recetas del usuario, implementar seguir a un usuario
            
            fotoPerfil = user.getFotoPerfil();
            username = user.getUsername();
            
            ArrayList<Recetas> recetas = (ArrayList<Recetas>) request.getAttribute("recetasPag");
            
            String htmlRecetas = "";            
            
            for(int i = 0;i<recetas.size(); i++){
                if(i%3 == 0){//cambiamos de fila cada 3 recetas
                    htmlRecetas+="<div class='row'>";
                }
                htmlRecetas += "<div class='receta'>";
                htmlRecetas += "<div class='fotoreceta'><img src='"+recetas.get(i).getFoto()+"'></div>";
                htmlRecetas += "<div class='tituloreceta'><a href='/pages/verReceta?rec="+recetas.get(i).getId()+"'><h2>"+recetas.get(i).getTitulo()+"</h2></a></div>";
                htmlRecetas += "</div>";
                if(i%3 == 2 || i==recetas.size()-1){//cerramos la fila cada 3 recetas o cuando sea la ultima
                    htmlRecetas += "</div>";
                }
            }
            
            HttpSession sesion = request.getSession();
            Usuario yo = (Usuario) sesion.getAttribute("usuario");
            
            if(yo == null){
                response.sendRedirect("/pages/login.jsp");
            }
            
            String botonSiguiendo = "";
            String msgSiguiendo = "";
            if(UsuarioBD.siguiendo(yo.getId(),idUsuario)){
                botonSiguiendo="<a class='btn btn-info unfollow_btn' href='/pages/unfollowUser?id="+idUsuario+"'>";
                msgSiguiendo = "Dejar de Seguir";
            }
            else{botonSiguiendo="<a class='btn btn-info follow_btn' href='/pages/followUser?id="+idUsuario+"'>";
                 msgSiguiendo = "Seguir"; }

            %>
    
        <title>Página de <%= username %></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">    
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/style_index.css">
        <link rel="stylesheet" type="text/css" href="../css/pagusuario.css">
        <link rel="stylesheet" type="text/css" href="../css/mensaje.css"/>
        
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>  
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>
        <script type="application/javascript">
            
             $(document).ready(function(){
                 $("#linkmensaje").click(function(){
                     $("#msgmodal").load("/pages/escribirmensaje.jsp?receptor="+<%=idUsuario%>);
                     $("#msgmodal").dialog({
                         modal:true,
                         resizable: false,
                         draggable: false
                         });                     
                 })
             });
        </script>
    
    <body> 
        
        
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
      <header id="header">
  </header>
  <section id="navArea">
    
      <%@include file="cabecera.html" %>
      
    <div id="user">
      <img id="foto_perfil" src=<%= "'"+fotoPerfil+"'" %> >
      <div class="info">
        <h1 id="username"><%= username %></h1>
        <div id="btns">
            <%=botonSiguiendo%><i class="fa fa-plus-square fa-1x" aria-hidden="true"></i> <%=msgSiguiendo%> </a>
            <div id="msgmodal" style="display: none"></div>
            <a class="btn btn-primary msg_btn" id="linkmensaje"><i class="fa fa-envelope-o fa-1x" aria-hidden="true"></i> Enviar mensaje</a>
        </div>
      </div>
    </div>
      <section id="recetas">
          <%= htmlRecetas %>
      </section>
  </section>

      <%@include file="footer.html" %>
</div>
</body>
</html>