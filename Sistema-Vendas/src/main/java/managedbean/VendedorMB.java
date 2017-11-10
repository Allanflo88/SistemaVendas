package managedbean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import entity.Vendedor;
import services.VendedorService;

@ManagedBean
@SessionScoped
public class VendedorMB {
	private Vendedor vendedor = new Vendedor();
	private VendedorService service = new VendedorService();
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Vendedor> getVendedores() {
		return service.getVendedores();
	}
	
	public VendedorService getService() {
		return service;
	}
	
	public void setService(VendedorService service) {
		this.service = service;
	}
	
	public void salvar() {
		service.salvar(vendedor);
		vendedor = new Vendedor();
	}
	
	public void excluir(Vendedor vendedor) {
		service.excluir(vendedor);
	}
	
	public void atualizar(RowEditEvent event) {
		Vendedor vendedor = (Vendedor) event.getObject();
		service.atualizar(vendedor);
	}
}
