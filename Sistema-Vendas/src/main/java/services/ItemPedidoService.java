package services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
	public void Salvar(ItemPedido itemPedido) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(itemPedido);
		em.getTransaction().commit();
		em.close();
	}
	public void excluir(ItemPedido itemPedido) {
		ItemPedido rmv;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		rmv = em.find(ItemPedido.class, itemPedido);
		em.remove(rmv);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualizar(ItemPedido itemPedido) {;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(itemPedido);
		em.getTransaction().commit();
		em.close();
	}
	

}
