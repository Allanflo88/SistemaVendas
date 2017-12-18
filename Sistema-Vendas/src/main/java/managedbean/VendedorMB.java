package managedbean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import entity.Vendedor;
import services.VendedorService;

@ManagedBean
@SessionScoped
public class VendedorMB {
	private Vendedor vendedor = new Vendedor();
	private VendedorService service = new VendedorService();
	private boolean edicao = false;
	private List<Vendedor> filter;
	
	public VendedorService getService() {
		return service;
	}

	public void setService(VendedorService service) {
		this.service = service;
	}

	public List<Vendedor> getFilter() {
		return filter;
	}

	public void setFilter(List<Vendedor> filter) {
		this.filter = filter;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Vendedor> getVendedores() {
		return service.getVendedores();
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
		if (service.consultar(vendedor.getCpf()) != null){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_WARN,
				rb.getString("messages.error.Erro.title"),
				rb.getString("messages.error.VendedorExiste.detail")
			);
			fc.addMessage(null, msg);
		} else {
			service.salvar(vendedor);
			vendedor = new Vendedor();
		}
	}
	
	public void excluir() {
		if (!vendedor.getPedidos().isEmpty()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				rb.getString("messages.error.NaoPodeExcluir.title"),
				rb.getString("messages.error.NaoPodeExcluir.vendedor.detail")
			);
			fc.addMessage(null, msg);
		} else {
			service.excluir(vendedor);
			cancelar();			
		}
	}
	
	public void atualizar(RowEditEvent event) {
		Vendedor vendedor = (Vendedor) event.getObject();
		service.atualizar(vendedor);
	}
	
	public void selecionar() {
		edicao = true;
	}
	
	public void cancelar(){
		edicao = false;
		vendedor = new Vendedor();
	}
	
	public void atualizar() {
		service.atualizar(vendedor);
		cancelar();
	}
	
}
