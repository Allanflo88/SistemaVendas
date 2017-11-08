package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.Vendedor;

public class VendedorService {
	private Vendedor vendedor = new Vendedor();
	private List<Vendedor> vendedores = new ArrayList<>();
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendas");
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public List<Vendedor> getVendedores() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select * from Vendedor", Vendedor.class);
		vendedores = q.getResultList();
		return vendedores;
	}
	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	
	public void Salvar(Vendedor vendedor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(vendedor);
		em.getTransaction().commit();
		em.close();
	}
	public void excluir(Vendedor vendedor) {
		Vendedor rmv;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		rmv = em.find(Vendedor.class, vendedor.getCpf());
		em.remove(rmv);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualizar(Vendedor vendedor) {;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(vendedor);
		em.getTransaction().commit();
		em.close();
	}

}
