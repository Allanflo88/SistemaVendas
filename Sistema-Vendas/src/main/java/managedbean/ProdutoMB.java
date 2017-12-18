package managedbean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import entity.Produto;
import services.ProdutoService;

@ManagedBean
@SessionScoped
public class ProdutoMB {
	private Produto produto = new Produto();
	private ProdutoService service = new ProdutoService();
	private boolean edicao = false;
	private List<Produto> filter;
	
	public ProdutoService getService() {
		return service;
	}

	public void setService(ProdutoService service) {
		this.service = service;
	}

	public List<Produto> getFilter() {
		return filter;
	}

	public void setFilter(List<Produto> filter) {
		this.filter = filter;
	}

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
		if (service.consultar(produto.getCodigo()) != null){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_WARN,
				rb.getString("messages.error.Erro.title"),
				rb.getString("messages.error.ProdutoExiste.detail")
			);
			fc.addMessage(null, msg);
		} else {
			service.salvar(produto);
			produto = new Produto();
		}
	}
	
	public void excluir() {
		if (!produto.getItensPedidos().isEmpty()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				rb.getString("messages.error.NaoPodeExcluir.title"),
				rb.getString("messages.error.NaoPodeExcluir.produto.detail")
			);
			fc.addMessage(null, msg);
		} else {
			service.excluir(produto);
			cancelar();			
		}
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
