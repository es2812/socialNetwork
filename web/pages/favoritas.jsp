<%-- 
    Document   : favoritas
    Created on : 30-abr-2017, 20:39:04
    Author     : Cristina
--%>

<%@page import="BD.UsuarioBD"%>
<%@page import="BD.RecetasBD"%>
<%@page import="modelo.Recetas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
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
        <script type="application/javascript">    
            $(document).ready(function(){
                    $(".fav").attr('id','selected');
            });
        </script>
    </head>
    <body>
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
                  <%@include file="barraInicio.html"%>
            </section>
            <%
                Usuario usu = (Usuario) session.getAttribute("usuario");
                if(usu == null){
                    response.sendRedirect("/pages/login.jsp");
                }
        
                ArrayList<Recetas> recetas = RecetasBD.recogerFavoritos(usu.getId());
                
                
            %>
            <section id="sliderSection">
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8">
                        <div class="slick_slider">
                            <%
                                for(int i=0;i<recetas.size();i++){
                                    //hay que comprobar que la instruccion tenga mas de 100 caracteres o da error
                                    String instruccionesRecetaShort = "";
                                    if(recetas.get(i).getInstrucciones().length()>100){
                                        instruccionesRecetaShort = recetas.get(i).getInstrucciones().substring(0, 100);
                                    }
                                    else{instruccionesRecetaShort = recetas.get(i).getInstrucciones();}
                                    %>
                                    <div class="single_iteam"><img src="<%= recetas.get(i).getFoto() %>" alt="">
                                        <div class="slider_article">
                                            <h2><a class="slider_tittle" href="/pages/verReceta?rec=<%= recetas.get(i).getId()%>"><%= recetas.get(i).getTitulo()%></a></h2>
                                            <h2><a class="slider_tittle" href="/pages/accederPagUsuario?usuario=<%= recetas.get(i).getAutor()%>"><%= UsuarioBD.getUsuarioById(recetas.get(i).getAutor()).getUsername()%></a></h2>
                                            <p><%= instruccionesRecetaShort%>...</p>
                                        </div>
                                     </div>
                                    <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <div class="latest_post">
                            <h2><span>Ranking</span></h2>
                            <div class="latest_post_container">
                                <ul class="latest_postnav">
                                    <%
                                        ArrayList<Recetas> ranking = RecetasBD.crearRanking();
                                        
                                        ArrayList<String> imagenes = new ArrayList<String>();
                                        imagenes.add("../images/uno.png");
                                        imagenes.add("../images/dos.png");
                                        imagenes.add("../images/tres.png");
                                        imagenes.add("../images/cuatro.png");
                                        imagenes.add("../images/cinco.png");
                                        
                                        int tam = Math.min(5, ranking.size());
                                        
                                        for(int k=0;k<tam;k++){
                                            %>
                                            <li>
                                                <div class="rank"><img class="rank" alt="" src="<%= imagenes.get(k)%>"></div>
                                                <div class="media"> <a href="/pages/verReceta?rec=<%= ranking.get(k).getId()%>" class="media-left"> <img alt="" src="<%= ranking.get(k).getFoto()%>"> </a>
                                                    <div class="media-body"> <a href="/pages/verReceta?rec=<%= ranking.get(k).getId()%>" class="catg_title"><%= ranking.get(k).getTitulo() %></a> </div>
                                                </div>
                                            </li>
                                    <%
                                        }
                                        %>
                                        
                                </ul>
                            </div>
                        </div>

                        <div class="latest_post usuarios">
                            <h2><span>Usuarios</span></h2>
                            <div class="latest_post_container">
                                <ul class="latest_postnav">
                            <%
                                ArrayList<Integer> amigos = UsuarioBD.recogerAmigos(usu.getId());
                                
                                for(int j=0;j<amigos.size();j++){
                                    Usuario ami = UsuarioBD.getUsuarioById(amigos.get(j));
                                    %><li>
                                        <div class="media"> <a href="#" class="media-left"> <img alt="" src="<%= ami.getFotoPerfil()%>"> </a>
                                            <div class="media-body"> <a class="nombre" href="/pages/accederPagUsuario?usuario=<%= ami.getId()%>" class="catg_title"><%= ami.getUsername()%> </a>
                                        </div>
                                    </li>
                                  <%
                                }
                            %>    
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%@include file="footer.html"%>
        </div>
    </body>
</html>
