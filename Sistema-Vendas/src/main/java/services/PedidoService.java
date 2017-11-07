package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Pedido;

public class PedidoService {
	private Pedido pedido = new Pedido();
	private List<Pedido> pedidos = new ArrayList<>();
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendas");
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Pedido> getPedidos() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select * from Pedido", Pedido.class);
		pedidos = q.getResultList();
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

}
