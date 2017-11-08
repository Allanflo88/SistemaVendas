package services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.Produto;

public class ProdutoService {
	private Produto produto;
	private List<Produto> produtos = new ArrayList<Produto>();
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendas");
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select * From Produto;", Produto.class);
		produtos = q.getResultList();
		em.close();
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	public void Salvar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
	}
	public void excluir(Produto produto) {
		Produto rmv;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		rmv = em.find(Produto.class, produto.getCodigo());
		em.remove(rmv);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualizar(Produto produto) {;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
		em.close();
	}

}
