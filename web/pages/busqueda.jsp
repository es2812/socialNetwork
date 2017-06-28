<%-- 
    Document   : busqueda
    Created on : Apr 28, 2017, 1:52:07 PM
    Author     : raqlope
--%>


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

       
       

            <div class="cuerpo-busqueda">
              
                  <% 
                      ArrayList<Recetas> lista = (ArrayList<Recetas>) request.getAttribute("lista");
                      
                      if(lista==null || lista.isEmpty()){
                        %><h1>
                            <%=
                                request.getParameter("error")
                            %></h1><%
                        }else{
                            %><div class="titulo-pagina">
                            <h3>Resultados de la búsqueda para: </h3>
                              <button type="button">#<%= request.getParameter("etiqueta")%></button></div>
                              <div class="lista-recetas">
                                
                                    <%
                                        int tamDescripcion = 0;
                                        for(int i = 0; i < lista.size(); i++){
                                            tamDescripcion = Math.min(lista.get(i).getInstrucciones().length(), 45);

                                            %>
                                            <div class="receta">
                                            <a href="/pages/verReceta?rec=<%= lista.get(i).getId()%>"><img src="<%= lista.get(i).getFoto() %>" alt="imagen de la receta">
                                            <h4><%= lista.get(i).getTitulo() %></h4></a>
                                            <p><%= lista.get(i).getInstrucciones().substring(0, tamDescripcion) %>...</p><br>
                                            <a href="/pages/verReceta?rec=<%= lista.get(i).getId()%>"><p class="mas">leer más...</p></a>
                                            </div>
                                            <%
                                        }
                                    %>

                                
                               </div>
                       <% }%>
              </div>
              
              
            

        </section>

        <%@include file="footer.html" %>
        
    </div>
    </body>
</html>