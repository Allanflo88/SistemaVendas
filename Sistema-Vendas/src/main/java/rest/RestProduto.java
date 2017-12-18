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

import entity.Produto;
import rest.exception.RestException;
import services.ProdutoService;

@Path("/produto")
public class RestProduto {
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getList(){
		ProdutoService service = new ProdutoService();
		return service.getProdutos();
	}
	
	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto find(@QueryParam("codigo") int codigo){
		ProdutoService service = new ProdutoService();
		return service.consultar(codigo);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto save(Produto produto) throws RestException{
		ProdutoService service = new ProdutoService();
		if (service.consultar(produto.getCodigo()) != null){
			throw new RestException("Produto with code " + produto.getCodigo() + " already exists");
		}
		if (produto.getDescricao() == null || produto.getDescricao().isEmpty()){
			throw new RestException("Description attribute cannot be empty");
		}
		return service.salvar(produto);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto update(Produto produto) throws RestException {
		ProdutoService service = new ProdutoService();
		if (service.consultar(produto.getCodigo()) == null){
			throw new RestException("Produto with code " + produto.getCodigo() + " is not registered");
		}
		service.atualizar(produto);
		return service.consultar(produto.getCodigo());
	}
	
	@DELETE
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@QueryParam("codigo") int codigo) throws RestException {
		ProdutoService service = new ProdutoService();
		Produto produto = service.consultar(codigo);
		if (produto == null){
			throw new RestException("Produto with code " + codigo + " is not registered");
		} else {
			service.excluir(produto);
		}
	}
	
}
