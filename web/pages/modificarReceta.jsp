<%@page import="BD.UsuarioBD"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modificar Receta</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/font.css">  
        <link rel="stylesheet" type="text/css" href="../css/marco.css">
        <link rel="stylesheet" type="text/css" href="../css/anyadirReceta.css">        
        <script type="application/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script> 
            $(document).ready(function() {
                $(".add").click(function() {
                    var id = $("#masIng div").length ;
                    var fieldWrapper = $("<div class=\"ingredientesAñadidos\" id=\"div" + id + "\"/>");
                    var fName = $("<input type=\"text\" name=\"Ingr"+ id + "\"   placeholder= \"Añadir ingrediente...\"   />");
                    var removeButton = $('<img/>', {
                           "class" :"del", 
                            src:"../images/quita.png"
                         });
                    removeButton.click(function() {
                        $(this).parent().remove();
                    });                                   
                    fieldWrapper.append(fName);
                    fieldWrapper.append(removeButton);
                    $("#masIng").append(fieldWrapper);
                 });
                $('.del').click(function(){
                    $(this).parent().remove();
                })
                 
                });            
                
            function validarCom() {
                var x, text;

                // Get the value of the input field with id="numb"
                x = document.getElementById("comensales").value;

                // If x is Not a Number or less than one or greater than 10
                if (isNaN(x)) {
                    alert("Comensales no validos!");
                    return false;
                } 
                document.getElementById("demo").innerHTML = text;
}
        </script>
    </head>
    <body>  
<a class="scrollToTop" href="#"></a>
<div class="container">
  <header id="header">
  </header>
  <section id="navArea">
    <nav class="navbar navbar-inverse" role="navigation">
      <%@include file="cabecera.html"%>
    </nav>
      <div>
           <div class="general">
            
                <form  action="actualizarReceta" method="post" enctype="multipart/form-data" onsubmit="return validarCom()">   
                    <div id="cuerpoReceta">
                    <input type="hidden" name="idReceta" value="<%=request.getAttribute("id")%>">
                    <div>
                       <input type="text" class="titulo" name="titulo" value= "<%= (String)request.getAttribute("titulo") %>"  /> 
                       <input type="text" class="comensales" name="comensales" id="comensales" value= "<%= (String)request.getAttribute("comensales") %>"  />
                    </div>
                    <div><textarea id="prep" name="prep"> <%= (String)request.getAttribute("instrucciones") %>                 

                    </textarea></div>
                    <div> 
                        <div>
                            <img class="mini" name="mini" src="<%= (String)request.getAttribute("foto") %>">
                        </div>
                            
                        <input type="text" name="ffoto" hidden value="<%= (String)request.getAttribute("foto") %>"/>
                        <label for="file">Foto<input type="file" name="foto" id="file" class="inputfile" value="<%= (String)request.getAttribute("foto") %>"/></label>
                        <label for="file">Video<input type="file" name="video" id="file" class="inputfile" /></label>
                       
                    </div>
                    <div id="etiq">
                        <input type="text" id="etiqueta" name="etiqueta" value= "<%=request.getAttribute("etiquetas")%>"/> 
                    <div>
                        <input class="ok" type="submit" value="Modificar">
                        <input class="cancel" type="button" value="Cancelar">
                    </div>
                    </div>                           
            
            </div>
            <div class="ingredientes">
                <div>
                <h2>Ingredientes:</h2>
                </div>
                <div>
                    <% 
                        ArrayList <String> ingredientes = (ArrayList<String>) request.getAttribute("ingredientes");
                        String htmlIng ="<fieldset id='masIng'>";
                        for(int i=0;i<ingredientes.size();i++){
                            if(i==0){
                                htmlIng +="<div class='ingredientesAñadidos' id='div"+i+"' ><input type='text' name='Ingr"+i+"' value= '"+ingredientes.get(i)+"'/> <img class='add' src='../images/mas.png'></div>";
                            }else if(i<ingredientes.size()-1){
                                
                                htmlIng +="<div class='ingredientesAñadidos' id='div"+i+"' ><input type='text' name='Ingr"+i+"' value= '"+ingredientes.get(i)+"'/> <img class='del' src='../images/quita.png'></div>";
                            }else{
                                htmlIng +="<div class='ingredientesAñadidos' id='div"+i+"'><input type='text' name='Ingr"+i+"' value= '"+ingredientes.get(i)+"'/> <img class='del' src='../images/quita.png'></div>";
                                htmlIng +="<div class='ingredientesAñadidos' id='div"+(i+1)+"'><input type='text' name='Ingr"+(i+1)+"' placeholder='Añadir ingrediente...'  /> <img class='del' src='../images/quita.png'></div>";
                            }    
                            
                                                        
                        }
                        htmlIng+="</fieldset>";
                     %>                    
                     <%=htmlIng%>
                    
                </div>
            </div>
            <!--<div class="tipoReceta" >
               <label>Tipo Receta:</label>
                   <select name="tipoReceta" required>
                       <option value="Entrante"  >Entrante</option>
                   <option value="Primero" selected >Primero</option>
                   <option value="Segundo">Segundo</option>
                   <option value="Postre">Postre</option>
               </select>
           </div>-->
            </form>
        </div>
      </div>
  </section>     
    <footer id="footer">
         <%@include file="footer.html"%>
  </footer>
  
</div>
</body>
</html>

