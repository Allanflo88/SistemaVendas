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
import entity.Pessoa;
import rest.exception.RestException;
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
	public Cliente save(Cliente cliente) throws RestException{
		ClienteService service = new ClienteService();
		String cpf = cliente.getCpf();
		if (cpf == null || cpf.isEmpty()){
			throw new RestException("CPF attribute cannot be empty");
		}
		if (!Pessoa.cpfValido(cpf)){
			throw new RestException(cpf + " is an invalid CPF code");
		}
		if (service.consultar(cpf) != null){
			throw new RestException("Client with CPF " + cpf + " already exists");
		}
		if (cliente.getNome() == null || cliente.getNome().isEmpty()){
			throw new RestException("Name attribute cannot be empty");
		}
		return service.salvar(cliente);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente update(Cliente cliente) throws RestException {
		ClienteService service = new ClienteService();
		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()){
			throw new RestException("CPF attribute cannot be empty");
		}
		if (service.consultar(cliente.getCpf()) == null){
			throw new RestException("Client with CPF " + cliente.getCpf() + " is not registered");
		}
		service.atualizar(cliente);
		return service.consultar(cliente.getCpf());
	}
	
	@DELETE
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@QueryParam("cpf") String cpf) throws RestException {
		ClienteService service = new ClienteService();
		Cliente cliente = service.consultar(cpf);
		if (cliente == null){
			throw new RestException("Client with CPF " + cpf + " is not registered");
		} else {
			service.excluir(cliente);
		}
	}
	
}
