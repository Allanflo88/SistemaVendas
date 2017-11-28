package managedbean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import entity.Pedido;
import services.PedidoService;

@ManagedBean
@SessionScoped
public class PedidoMB {
	private Pedido pedido = new Pedido();
	private PedidoService service = new PedidoService();

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public PedidoService getService() {
		return service;
	}
	public void setService(PedidoService service) {
		this.service = service;
	}
	public List<Pedido> getPedidos() {
		return service.getPedidos();
	}
	
	public void salvar() {
		service.salvar(pedido);
		pedido = new Pedido();
	}
	
	public void excluir(Pedido pedido) {
		service.excluir(pedido);
	}
	
	public void atualizar(RowEditEvent event) {
		Pedido ped = (Pedido) event.getObject();
		service.atualizar(ped);
	}
	
	
}