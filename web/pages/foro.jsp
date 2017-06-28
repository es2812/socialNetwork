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
        <title>Foro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/foro.css">
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
            
            
        <div class="general">

        <div class="divforo">
            <table class="foro" >
                <tr>
                    <th>Hora</th>
                    <th>Tema</th>
                    <th>Autor</th>
                    <th>Respuestas</th>
                </tr>
                <tr>
                    <td>12:01</td>
                    <td>
                        <a href="hiloForo.jsp" title="hilo1">Técnica: Masa madre</a>
                        <p>Visitas: 10</p>
                    </td>
                    <td><a href="pagusuario.jsp">Romsey25</a></td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>12:01</td>
                    <td>Utensilios: Robots de cocina
                        <p>Visitas: 5</p>
                    </td>
                    <td>Cristina</td>
                   <td>2</td>
                </tr>
                <tr>
                   <td>12:01</td>
                   <td>Técnicas: Pasta fresca perfecta
                        <p>Visitas: 5</p>
                    </td>
                    <td>Raquel</td>
                    <td>2</td>
                </tr>   
                <tr>
                   <td>12:01</td>
                   <td>Técnicas: Pasta fresca perfecta
                        <p>Visitas: 25</p>
                    </td>
                    <td>Raquel</td>
                    <td>10</td>
                </tr> 

            </table>    

        </div>
        </div>
        

        </section>

        <%@include file="footer.html" %>
        
    </div>
    </body>
</html>