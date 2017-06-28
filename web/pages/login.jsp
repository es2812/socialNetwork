<%-- 
    Document   : login
    Created on : Apr 28, 2017, 11:48:28 AM
    Author     : dizzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>¡Bienvenid@ a cocinillas!</title>
    
  <link href="../css/style_login.css" rel="stylesheet" type="text/css" />

</head>

<body>
  <div class="body"></div>
		<!--<div class="grad"></div>-->
       <% 
       String err = "";
       String viejoid = "";
       String respuesta ="";
       if(request.getParameterNames().hasMoreElements()){ 
        err = request.getAttribute("error").toString();
        viejoid = "";
        if(err.equals("id")){
            respuesta = "No existe cuenta con ese <br> nombre de usuario o email.";
        }
        else if(err.equals("pass")){
           respuesta = "Contraseña incorrecta.";
           viejoid = request.getAttribute("id").toString();
        }
        else if(err.equals("sql")){
           respuesta = "Error interno. Inténtelo de nuevo";
        }
       }
       %>
    <div id="recuadro"></div>
		<div class="header">
                    <div><span>Cocinillas</span></div>
		</div>
		<br>
                <form class="login" action="login" method="post">
                    <input type="text" required="true" placeholder="Usuario o Email" name="id" value=<%= "'"+viejoid+"'" %>><br>
                    <input type="password" required="true" placeholder="Contraseña" name="password"><br>
                    <div id="msgerror"><%= respuesta %></div><br>
                    <input class="singin" type="submit" value="Login">
                    <input class="registro" type="button" value="Registrarse" onclick="location='/pages/registro.jsp'">
		</form>
    
</body>
</html>
