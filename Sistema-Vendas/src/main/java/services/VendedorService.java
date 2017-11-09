package services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.Vendedor;

public class VendedorService extends Service {
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> getVendedores() {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("select * from Vendedor", Vendedor.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void salvar(Vendedor vendedor) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(vendedor);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void excluir(Vendedor vendedor) {
		EntityManager em = emf.createEntityManager();
		try {
			Vendedor rmv;
			em.getTransaction().begin();
			rmv = em.find(Vendedor.class, vendedor.getCpf());
			em.remove(rmv);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void atualizar(Vendedor vendedor) {;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(vendedor);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
