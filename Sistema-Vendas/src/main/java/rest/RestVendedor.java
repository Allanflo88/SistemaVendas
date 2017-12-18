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

import entity.Vendedor;
import entity.Pessoa;
import rest.exception.RestException;
import services.VendedorService;

@Path("/vendedor")
public class RestVendedor {
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendedor> getList(){
		VendedorService service = new VendedorService();
		return service.getVendedores();
	}
	
	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendedor find(@QueryParam("cpf") String cpf){
		VendedorService service = new VendedorService();
		return service.consultar(cpf);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendedor save(Vendedor vendedor) throws RestException{
		VendedorService service = new VendedorService();
		String cpf = vendedor.getCpf();
		if (cpf == null || cpf.isEmpty()){
			throw new RestException("CPF attribute cannot be empty");
		}
		if (!Pessoa.cpfValido(cpf)){
			throw new RestException(cpf + " is an invalid CPF code");
		}
		if (service.consultar(cpf) != null){
			throw new RestException("Vendedor with CPF " + cpf + " already exists");
		}
		if (vendedor.getNome() == null || vendedor.getNome().isEmpty()){
			throw new RestException("Name attribute cannot be empty");
		}
		return service.salvar(vendedor);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendedor update(Vendedor vendedor) throws RestException {
		VendedorService service = new VendedorService();
		if (vendedor.getCpf() == null || vendedor.getCpf().isEmpty()){
			throw new RestException("CPF attribute cannot be empty");
		}
		if (service.consultar(vendedor.getCpf()) == null){
			throw new RestException("Vendedor with CPF " + vendedor.getCpf() + " is not registered");
		}
		service.atualizar(vendedor);
		return service.consultar(vendedor.getCpf());
	}
	
	@DELETE
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@QueryParam("cpf") String cpf) throws RestException {
		VendedorService service = new VendedorService();
		Vendedor vendedor = service.consultar(cpf);
		if (vendedor == null){
			throw new RestException("Vendedor with CPF " + cpf + " is not registered");
		} else {
			service.excluir(vendedor);
		}
	}
	
}
