package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entity.ItemPedido;
import entity.ItemPedidoID;

public class ItemPedidoService extends Service {

	@SuppressWarnings("unchecked")
	public List<ItemPedido> getItemPedidos() {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("select ip from ItemPedido ip", ItemPedido.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void salvar(ItemPedido itemPedido) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(itemPedido);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void excluir(ItemPedidoID itemPedido) {
		EntityManager em = emf.createEntityManager();
		try {
			ItemPedido rmv;
			em.getTransaction().begin();
			rmv = em.find(ItemPedido.class, itemPedido);
			em.remove(rmv);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void atualizar(ItemPedido itemPedido) {;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(itemPedido);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	

}
