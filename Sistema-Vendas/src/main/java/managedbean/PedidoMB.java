package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import entity.ItemPedido;
import entity.Pedido;

import services.PedidoService;

@ManagedBean
@SessionScoped
public class PedidoMB {
	private Pedido pedido = new Pedido();
	private PedidoService service = new PedidoService();
	private List<ItemPedido> itens = new ArrayList<>();
	private ItemPedido item = new ItemPedido();
	private List<Pedido> filteredPedidos;
	
	public List<Pedido> getFilteredPedidos() {
		return filteredPedidos;
	}
	public void setFilteredPedidos(List<Pedido> filteredPedidos) {
		this.filteredPedidos = filteredPedidos;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
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
	
	public List<ItemPedido> getItens() {
		return itens;
	}	
	public ItemPedido getItem() {
		return item;
	}
	public void setItem(ItemPedido itemPedido) {
		this.item = itemPedido;
	}
	public void addItem() {
		item.setPedido(pedido);
		itens.add(item);
		item = new ItemPedido();
	}
	public void salvar() {
		ItemPedidoMB itemPedido = new ItemPedidoMB();
		service.salvar(pedido);
		System.out.println(pedido.getCliente().getLimiteDisp());
		for(ItemPedido i : itens) {
			itemPedido.setItemPedido(i);
			itemPedido.salvar();
			System.out.println(item.getProduto().getQtdeDisponivel());
		}
		
		
		pedido = new Pedido();
		itens = new ArrayList<>();
	}
	
	public void excluir(Pedido pedido) {
		service.excluir(pedido);
	}
	
	public void atualizar(RowEditEvent event) {
		Pedido ped = (Pedido) event.getObject();
		service.atualizar(ped);
	}
	
	public int getQtdProduto(){
		if (item.getProduto() == null){
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Selecione um produto primeiro"));
			return 0;
		} else {
			return item.getProduto().getQtdeDisponivel() - item.getProduto().getEstoqueMin();
		}
	}
	
	
}
