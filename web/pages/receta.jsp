<%-- 
    Document   : receta
    Created on : 30-abr-2017, 13:28:57
    Author     : Cristina
--%>

<%@page import="modelo.Usuario"%>
<%@page import="BD.UsuarioBD"%>
<%@page import="modelo.Comentarios"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cocinillas</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">  
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/style_index.css">
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>
            function validarFormulario(){
                var x=document.forms["com"]["texto"].value;
                if (x==null || x=="") {
                    alert("Debe dejar algún comenario.");
                    return false;
                } 
            }
        </script>
    </head>
    <body>
        <!--div id="preloader">
        <div id="status">&nbsp;</div>
        </div-->
        <!--COMIENZA EL MARCO-->
        <a class="scrollToTop" href="#"></a>
        <div class="container">
            <header id="header">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="header_bottom"></div>
                    </div>
                </div>
            </header>
            <section id="navArea">
                <%@include file="cabecera.html"%>  
            </section>
    
            <!--ACABA EL MARCO-->

            <section id="sliderSection">
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8">
                        <h3 class="titulo"><%= (String)request.getAttribute("titulo") %></h3>
                        <img class="imgReceta" src="<%= (String)request.getAttribute("foto") %>"/>
                        <div class="preparacion">
                            <h5>Número de comensales:  <%= request.getAttribute("numCom") %></h5><br>
                            <h5>Preparación:</h5>
                            <p><%= (String)request.getAttribute("instrucciones") %></p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <div class="latest_post">
                            <%
                                boolean existeGustado, existeFavorito;
                                
                                existeGustado = (boolean) request.getAttribute("gustado");
                                existeFavorito = (boolean) request.getAttribute("favorito");
                                
                                String pathImgGustado = "";
                                String urlGustado = "";
                                String pathImgFav = "";
                                String urlFav = "";
                                
                                if(existeGustado){
                                    pathImgGustado = "/images/Like-icon-active-2.png";
                                    urlGustado = "/pages/eliminarLike?rec="+request.getAttribute("id");
                                }
                                else{
                                    pathImgGustado = "/images/Like-icon.png";
                                    urlGustado = "/pages/anyadirLike?rec="+request.getAttribute("id");
                                }
                                
                                if(existeFavorito){
                                    pathImgFav = "/images/fav-active-2.png";
                                    urlFav = "/pages/eliminarFav?rec="+request.getAttribute("id");
                                }
                                else{
                                    pathImgFav = "/images/fav.png";
                                    urlFav = "/pages/anyadirFav?rec="+request.getAttribute("id");
                                }        
                                 
                                %>
                            <a href="<%= urlGustado%>"><input type="image" src="<%=  pathImgGustado %>" class="like" title="Like"/></a>
                            <a href="<%= urlFav%>"><input type="image" src="<%= pathImgFav %>" class="fav" title="Favorita"/></a>
                            
                            <h2><span>Ingredientes:</span></h2>
                            <div class="latest_post_container">
                                <ul class="latest_postnav">
                                    <% 
                                        ArrayList<String> ing = (ArrayList<String>)request.getAttribute("ingredientes");

                                        for ( int i=0; i<ing.size();i++){
                                            %><li><%= ing.get(i)%></li><%
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                        <div class="latest_post">
                            <h2><span>Usuario</span></h2>
                            <div class="media"> <a href="#" class="media-left"> <img alt="" src="<%= UsuarioBD.getInstanciaUsuario((String)request.getAttribute("autor")).getFotoPerfil() %>"> </a>
                                    <div class="media-body"> <a class="nombre" href="/pages/accederPagUsuario?usuario=<%= request.getAttribute("idautor") %>" class="catg_title"><%= request.getAttribute("autor")%> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <div class="comentario">
                <div class="comentarios">
                    <h3>Deja tu comentario:</h3>
                    <form name="com" method="post" action="/pages/anyadirCom" onsubmit="return validarFormulario()">
                        <textarea name="texto" cols="90" class="textoCom"></textarea><br>
                        <input type="submit" value="Comentar" class="coment btn btn-primary msg_btn enviar"/>
                        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"/>
                    </form>
                </div>
                
                    <% 
                        ArrayList<Comentarios> com = (ArrayList<Comentarios>)request.getAttribute("comentarios");
                        UsuarioBD autor = new UsuarioBD();
                        Usuario au = new Usuario();
                        
                        if(com.size()!=0){
                            for ( int j=0; j<com.size();j++){
                                au = autor.getUsuarioById(com.get(j).getAutor());
                                
                                %><div class="comentario"><a href=<%="'/pages/accederPagUsuario?usuario="+au.getId()+"'"%>><%= au.getUsername()%></a>
                                <p><%= com.get(j).getFecha()%></p><br>
                                <p id="comentario"><%= com.get(j).getTexto()%> </div><%
                            }
                        }
                    %>
               
            </div>
                                
            <%@include file="footer.html"%>
        </div>
    </body>
</html>
