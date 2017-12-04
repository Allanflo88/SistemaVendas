package managedbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;
import entity.ItemPedido;
import entity.Pedido;
import services.ItemPedidoService;

@ManagedBean
@SessionScoped
public class ItemPedidoMB {

	private ItemPedido itemPedido = new ItemPedido();
	private ItemPedidoService service = new ItemPedidoService();

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public ItemPedidoService getService() {
		return service;
	}

	public void setService(ItemPedidoService service) {
		this.service = service;
	}

	public List<ItemPedido> getItensPedido() {
		return service.getItemPedidos();
	}

	public void salvar() {
		service.salvar(itemPedido);
		itemPedido = new ItemPedido();
	}

	public void excluir() {
		service.excluir(itemPedido);
	}

	public void atualizar(RowEditEvent event) {
		ItemPedido itemPedido = (ItemPedido) event.getObject();
		service.atualizar(itemPedido);
	}

	public void atualizar() {
		service.atualizar(itemPedido);
	}

}
