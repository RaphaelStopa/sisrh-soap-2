package sisrh.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import io.swagger.annotations.Api;
import sisrh.banco.Banco;
import sisrh.dto.Usuario;

//http://localhost:8080/sisrh/rest/swagger.json
@Api
@Path("/usuario")
public class UsuarioRest {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuarios() throws Exception {
		List<Usuario> lista = Banco.listarUsuarios();		
		GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(lista) {};
		return Response.ok().entity(entity).build();
	}
	
	

	
	@GET
	@Path("{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterUsuario(@PathParam("matricula") String matricula) throws Exception {
		try {
			Usuario Usuario = Banco.buscarUsuarioPorMatricula(matricula);
			if ( Usuario != null ) {
				return Response.ok().entity(Usuario).build();
			}else {
				return Response.status(Status.NOT_FOUND)
						.entity("{ \"mensagem\" : \"Usuario nao encontrado!\" }").build();
			}
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha para obter Usuario!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}
	}
	
	

	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response incluirUsuario(Usuario Usuario) {
		try {
			Usuario emp = Banco.incluirUsuario(Usuario);
			return Response.ok().entity(emp).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha na inclusao do Usuario!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}		
	}
	
	
	@PUT	
	@Path("{matricula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarUsuario(@PathParam("matricula") String matricula, Usuario Usuario)  {
		try {			
			if ( Banco.buscarUsuarioPorMatricula(matricula) == null ) {				
				return Response.status(Status.NOT_FOUND)
						.entity("{ \"mensagem\" : \"Usuario nao encontrado!\" }").build();
			}				
			Usuario emp = Banco.alterarUsuario(matricula, Usuario);	
			return Response.ok().entity(emp).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha na alteracao do Usuario!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}
	}

	
	@DELETE
	@Path("{matricula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirUsuario(@PathParam("matricula") String matricula) throws Exception {
		try {
			if ( Banco.buscarUsuarioPorMatricula(matricula) == null ) {				
				return Response.status(Status.NOT_FOUND).
						entity("{ \"mensagem\" : \"Usuario nao encontrado!\" }").build();
			}				
			Banco.excluirUsuario(matricula);
			return Response.ok().entity("{ \"mensagem\" : \"Usuario "+ matricula + " excluido!\" }").build();	
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity("{ \"mensagem\" : \"Falha na exclusao do Usuario!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}		
	}


}
