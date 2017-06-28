<%-- 
    Document   : registro
    Created on : Apr 26, 2017, 2:04:42 PM
    Author     : Esther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Registro</title>
    
  <link href="../css/style_regis.css" rel="stylesheet" type="text/css" />

</head>

<body>
  <div class="body"></div>
		<!--<div class="grad"></div>-->
    <% 
       String err = "";
       String viejousuario = "";
       String viejocorreo = "";
       String respuesta ="";
       if(request.getParameterNames().hasMoreElements()){ 
        err = request.getAttribute("error").toString();
        viejousuario = "";
        viejocorreo ="";
       if(err.equals("contrasenya")){
           respuesta = "Las contraseñas no coinciden.";
           viejousuario = request.getAttribute("usuario").toString();
           viejocorreo = request.getAttribute("correo").toString();
       }
       else if(err.equals("user")){
           respuesta = "Este nombre de usuario ya está en uso";
           viejocorreo = request.getAttribute("correo").toString();
       }
       else if(err.equals("correo")){
           respuesta = "Este correo ya está en uso";
           viejousuario = request.getAttribute("usuario").toString();
       }
       else if(err.equals("sql")){
           respuesta = "Error interno. Inténtelo de nuevo";
       }
       }
       %>
                
    <div id="recuadro"></div>
		<div class="header">
                    <div>Registro:</div>
		</div>
		<br>
                <form action='registro' method="post" class="login">
                    <input type="text" required="true" placeholder="Usuario" name="user" value=<%= "'"+viejousuario+"'" %> ><br>
                    <input type="text" required="true" placeholder="Correo Electrónico" name="email" value=<%= "'"+viejocorreo+"'" %>><br>
                    <input type="password" required="true" placeholder="Contraseña" name="password"><br>
                    <input type="password" required="true"  placeholder="Repita la contraseña" name="password2"><br>
                    <div id="msgerror"><%= respuesta %></div><br>
                    <input class="registro" type="submit" value="Crear Cuenta">
		</form>
    
</body>
</html>
