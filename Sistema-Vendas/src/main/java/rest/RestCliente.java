package rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import entity.Cliente;
import services.ClienteService;

@Path("/cliente")
public class RestCliente {
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getList(){
		ClienteService service = new ClienteService();
		return service.getClientes();
	}
	
	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente find(@QueryParam("cpf") String cpf){
		ClienteService service = new ClienteService();
		return service.consultar(cpf);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente save(Cliente cliente){
		ClienteService service = new ClienteService();
		return service.salvar(cliente);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente update(Cliente cliente){
		ClienteService service = new ClienteService();
		service.atualizar(cliente);
		return service.consultar(cliente.getCpf());
	}
	
	@DELETE
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@QueryParam("cpf") String cpf){
		ClienteService service = new ClienteService();
		Cliente cliente = service.consultar(cpf);
		if (cliente != null){
			service.excluir(cliente);
		}
	}
	
}
