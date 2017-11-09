package managedbean;

import java.util.ArrayList;
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
	private List<Vendedor> vendedores = new ArrayList<>();
	private VendedorService service = new VendedorService();
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Vendedor> getVendedores() {
		if(vendedores == null) {
			vendedores = service.getVendedores();
		}
		return vendedores;
	}
	
	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	
	public VendedorService getService() {
		return service;
	}
	
	public void setService(VendedorService service) {
		this.service = service;
	}
	
	public void salvar(Vendedor vendedor) {
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
