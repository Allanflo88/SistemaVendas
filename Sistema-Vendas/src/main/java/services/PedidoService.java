package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Pedido;

public class PedidoService extends Service{
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getPedidos() {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("select * from Pedido", Pedido.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void Salvar(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pedido);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void excluir(Pedido pedido) {
		EntityManager em = emf.createEntityManager();
		try {
			Pedido rmv;
			em.getTransaction().begin();
			rmv = em.find(Pedido.class, pedido.getNumero());
			em.remove(rmv);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void atualizar(Pedido pedido) {;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(pedido);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
}
