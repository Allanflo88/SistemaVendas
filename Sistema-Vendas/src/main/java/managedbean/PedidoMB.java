package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	private boolean edicao = false;
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
	
	public boolean isEdicao() {
		return edicao;
	}
	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	public boolean isCriacao() {
		return !edicao;
	}

	public void setCriacao(boolean criacao) {
		this.edicao = !criacao;
	}
	public void selecionar() {
		edicao = true;
	}
	
	public void addItem() {
		item.setPedido(pedido);
		itens.add(item);
		item = new ItemPedido();
	}
	public void removeItem(ItemPedido rmv) {
		itens.remove(rmv);
	}
	public void alterItem() {
		itens.remove(item);
		itens.add(item);
		item = new ItemPedido();
	}
	public void salvar() {
		ItemPedidoMB itemPedido = new ItemPedidoMB();
		service.salvar(pedido);
		for(ItemPedido i : itens) {
			itemPedido.setItemPedido(i);
			itemPedido.salvar();
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
	
}
