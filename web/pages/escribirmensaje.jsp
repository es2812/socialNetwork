    <%@page import="modelo.Usuario"%>
<%@page import="BD.UsuarioBD"%>
<%        
            int idReceptor = Integer.parseInt(request.getParameter("receptor"));
            String receptor = UsuarioBD.getUsuarioById(idReceptor).getUsername();
    %>
    
<form method="post" action='enviarMensaje' name="mensaje" >
  <div class="modal-dialog">
    <div class="modal-header">
        <h2><i class="fa fa-envelope-square"></i> Mensaje a <span> <%= receptor %> </span></h2>
    </div>
    <div class="modal-body">
        <textarea name="cuerpo" rows="6" cols="50" maxlength="500" placeholder="Mensaje..."></textarea>
        <input name="receptor" type="hidden" value=<%="'"+receptor+"'"%>/>
    </div>
    <div class="modal-footer">
        <a href="" class="btn btn-danger">Cancelar</a>
        <input type="submit" class="btn btn-primary" value="Enviar"/>
    </div>
  </div>
</form>