package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import entity.Cliente;
import services.ClienteService;

@ManagedBean
@SessionScoped
public class ClienteMB {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<>();
	private ClienteService service = new ClienteService();
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		if(clientes == null) {
			clientes = service.getClientes();
		}
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ClienteService getService() {
		return service;
	}
	public void setService(ClienteService service) {
		this.service = service;
	}
	public void salvar(Cliente cliente) {
		service.Salvar(cliente);
		cliente = new Cliente();
	}
	public void excluir(Cliente cliente) {
		service.excluir(cliente);
		
	}
	public void atualizar(RowEditEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		service.atualizar(cliente);
	}
	
}
