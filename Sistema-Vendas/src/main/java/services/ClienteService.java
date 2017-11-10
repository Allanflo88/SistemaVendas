package services;


import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import entity.Cliente;

public class ClienteService extends Service{


	@SuppressWarnings("unchecked")
	public List<Cliente> getClientes() {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("select * from Cliente", Cliente.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void salvar(Cliente cliente) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void excluir(Cliente cliente) {
		EntityManager em = emf.createEntityManager();
		try {
			Cliente rmv;
			em.getTransaction().begin();
			rmv = em.find(Cliente.class, cliente.getCpf());
			em.remove(rmv);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void atualizar(Cliente cliente) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(cliente);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
}
