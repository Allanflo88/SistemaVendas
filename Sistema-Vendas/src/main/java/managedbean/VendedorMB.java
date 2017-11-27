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
	private boolean edicao = false;
	
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
