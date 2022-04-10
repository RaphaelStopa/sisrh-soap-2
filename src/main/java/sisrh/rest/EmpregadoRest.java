package sisrh.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import sisrh.banco.Banco;
import sisrh.dto.Empregado;
import sisrh.dto.Usuario;

//http://localhost:8080/sisrh/rest/swagger.json
@Api
@Path("/empregado")
public class EmpregadoRest {	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEmpregados() throws Exception {
		List<Empregado> lista = Banco.listarEmpregados();		
		GenericEntity<List<Empregado>> entity = new GenericEntity<List<Empregado>>(lista) {};
		return Response.ok().entity(entity).build();
	}
	
	
	@GET()
	@Path("inativos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEmpregadosInativos() throws Exception {
		List<Empregado> lista = Banco.listarEmpregadosInativos();		
		GenericEntity<List<Empregado>> entity = new GenericEntity<List<Empregado>>(lista) {};
		return Response.ok().entity(entity).build();
	}
	
	@GET()
	@Path("ativos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEmpregadosAtivos() throws Exception {
		List<Empregado> lista = Banco.listarEmpregadosAtivos();		
		GenericEntity<List<Empregado>> entity = new GenericEntity<List<Empregado>>(lista) {};
		return Response.ok().entity(entity).build();
	}
	
	
	@GET
	@Path("{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterEmpregado(@PathParam("matricula") String matricula) throws Exception {
		try {
			Empregado empregado = Banco.buscarEmpregadoPorMatricula(matricula);
			if ( empregado != null ) {
				return Response.ok().entity(empregado).build();
			}else {
				return Response.status(Status.NOT_FOUND)
						.entity("{ \"mensagem\" : \"Empregado nao encontrado!\" }").build();
			}
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha para obter empregado!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}
	}
	
	
	
	

	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response incluirEmpregado(Empregado empregado) {
		try {
			Empregado emp = Banco.incluirEmpregado(empregado);
			return Response.ok().entity(emp).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha na inclusao do empregado!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}		
	}
	
	
	@PUT	
	@Path("{matricula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarEmpregado(@PathParam("matricula") String matricula, Empregado empregado)  {
		try {			
			if ( Banco.buscarEmpregadoPorMatricula(matricula) == null ) {				
				return Response.status(Status.NOT_FOUND)
						.entity("{ \"mensagem\" : \"Empregado nao encontrado!\" }").build();
			}				
			Empregado emp = Banco.alterarEmpregado(matricula, empregado);	
			return Response.ok().entity(emp).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"mensagem\" : \"Falha na alteracao do empregado!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}
	}

	
	@DELETE
	@Path("{matricula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirEmpregado(@PathParam("matricula") String matricula) throws Exception {
		try {
			if ( Banco.buscarEmpregadoPorMatricula(matricula) == null ) {				
				return Response.status(Status.NOT_FOUND).
						entity("{ \"mensagem\" : \"Empregado nao encontrado!\" }").build();
			}				
			Banco.excluirEmpregado(matricula);
			return Response.ok().entity("{ \"mensagem\" : \"Empregado "+ matricula + " excluido!\" }").build();	
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).
					entity("{ \"mensagem\" : \"Falha na exclusao do empregado!\" , \"detalhe\" :  \""+ e.getMessage() +"\"  }").build();
		}		
	}
	
	
	@GET
	@Path("usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuarios() throws Exception {
		List<Usuario> lista = Banco.listarUsuarios();		
		GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(lista) {};
		return Response.ok().entity(entity).build();
	}
	
	

	
	@GET
	@Path("usuario/{matricula}")
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
	@Path("usuario")
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
	@Path("usuario/{matricula}")
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
	@Path("usuario/{matricula}")
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
