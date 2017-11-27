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
	private boolean edicao = false;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return service.getProdutos();
	}
	
	public boolean isCriacao() {
		return !edicao;
	}

	public void setCriacao(boolean criacao) {
		this.edicao = !criacao;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	public void salvar() {
		service.salvar(produto);
		produto = new Produto();
	}
	
	public void excluir(Produto produto) {
		service.excluir(produto);
		cancelar();
	}
	
	public void atualizar(RowEditEvent event) {
		Produto prod = (Produto) event.getObject();
		service.atualizar(prod);
	}
	
	public void selecionar() {
		edicao = true;
	}
	
	public void cancelar(){
		edicao = false;
		produto = new Produto();
	}
	
	public void atualizar() {
		service.atualizar(produto);
		cancelar();
	}
	
}
