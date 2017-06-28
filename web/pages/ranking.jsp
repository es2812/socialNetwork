<%-- 
    Document   : busqueda
    Created on : Apr 28, 2017, 1:52:07 PM
    Author     : raqlope
--%>


<%@page import="BD.RecetasBD"%>
<%@page import="BD.UsuarioBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Recetas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/ranking.css">
        <link rel="stylesheet" type="text/css" href="../css/pag_busqueda.css">
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <!--script type="application/javascript">    
            $(document).ready(function(){
                    $(".recetas").attr('id','selected');
                    $(".espacio").addClass('active');
            });
        </script-->
    </head>
    
    <body>
        
    <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
        <div class="container">
                 <header id="header"></header>

        <section id="navArea">       

            <%@include file="cabecera.html"%>
            
              <!--Cuerpo de la pagina-->

            <div class="contenido">
              <div class="tablaRanking">
                  <% 
                    ArrayList<Recetas> lista = RecetasBD.crearRanking();
                    
                    if(lista==null || lista.isEmpty()){
                        %><h1> No se ha podido crear el ranking. </h1><%
                        }else{
                            %>
                  <table>
                    <tr>
                     <th>  </th><th>Receta</th><th>Usuario</th><th>Likes</th>
                    </tr>
                    <%
                        for(int i = 0; i < lista.size(); i++){
                        %>
                        <tr>   
                            <th><%= i+1%></th>
                            <td><a href="/pages/verReceta?rec=<%= lista.get(i).getId()%>"><%= lista.get(i).getTitulo() %></a></a></td>
                            <td><a href="/pages/accederPagUsuario?usuario=<%= lista.get(i).getAutor()%>"><%= UsuarioBD.obtenerAutor(lista.get(i).getAutor())%></a></td>
                            <td><%= lista.get(i).getLikes() %></td>
                        </tr>
                        <%
                      }
                    }
                    %>
                  
                </table>
              </div>
            </div>


        </section>

        <%@include file="footer.html" %>
        
    </div>
    </body>
</html>
