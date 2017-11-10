package managedbean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import entity.Produto;
import services.ProdutoService;

@ManagedBean
@SessionScoped
public class ProdutoMB {
	private Produto produto = new Produto();
	private ProdutoService service = new ProdutoService();
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return service.getProdutos();
	}
	
	public void salvar() {
		service.salvar(produto);
		produto = new Produto();
	}
	
	public void excluir(Produto produto) {
		service.excluir(produto);
	}
	
	public void atualizar(RowEditEvent event) {
		Produto prod = (Produto) event.getObject();
		service.atualizar(prod);
	}
	
}
