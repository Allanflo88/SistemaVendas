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
	
	

}
