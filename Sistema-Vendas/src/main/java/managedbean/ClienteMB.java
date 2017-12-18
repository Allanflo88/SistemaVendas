package managedbean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import entity.Cliente;
import services.ClienteService;

@ManagedBean
@SessionScoped
public class ClienteMB {
	private Cliente cliente = new Cliente();
	private ClienteService service = new ClienteService();
	private boolean edicao = false;
	private List<Cliente> filter;
	
	

	public List<Cliente> getFilter() {
		return filter;
	}

	public void setFilter(List<Cliente> filter) {
		this.filter = filter;
	}
	public ClienteService getService() {
		return service;
	}

	public void setService(ClienteService service) {
		this.service = service;
	}


	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientes() {
		return service.getClientes();
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
		if (service.consultar(cliente.getCpf()) != null){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_WARN,
				rb.getString("messages.error.Erro.title"),
				rb.getString("messages.error.ClienteExiste.detail")
			);
			fc.addMessage(null, msg);
		} else {
			cliente.setLimiteDisp(cliente.getLimiteCred());
			service.salvar(cliente);
			cliente = new Cliente();
		}
	}
	
	public void excluir() {
		if (!cliente.getPedidos().isEmpty()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				rb.getString("messages.error.NaoPodeExcluir.title"),
				rb.getString("messages.error.NaoPodeExcluir.cliente.detail")
			);
			fc.addMessage(null, msg);
		} else {
			service.excluir(cliente);
			cancelar();			
		}
	}
	
	public void atualizar(RowEditEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		service.atualizar(cliente);
	}
	
	public void selecionar() {
		edicao = true;
	}
	
	public void cancelar(){
		edicao = false;
		cliente = new Cliente();
	}
	
	public void atualizar() {
		service.atualizar(cliente);
		cancelar();
	}
	
}
