package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Cliente;
import entity.ItemPedido;

public class ItemPedidoService {
	private ItemPedido itemPedido = new ItemPedido();
	private List<ItemPedido> itemPedidos = new ArrayList<>();
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendas");

	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	public List<ItemPedido> getItemPedidos() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select * from ItemPedido", ItemPedido.class);
		itemPedidos = q.getResultList();
		return itemPedidos;
	}
	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
	
	

}
