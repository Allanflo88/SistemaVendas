package services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.Produto;

public class ProdutoService extends Service {
	
	@SuppressWarnings("unchecked")
	public List<Produto> getProdutos() {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("Select * From Produto;", Produto.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void Salvar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void excluir(Produto produto) {
		EntityManager em = emf.createEntityManager();
		try {
			Produto rmv;
			em.getTransaction().begin();
			rmv = em.find(Produto.class, produto.getCodigo());
			em.remove(rmv);
			em.getTransaction().commit();
		} finally { 
			em.close();
		}
		
	}
	
	public void atualizar(Produto produto) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
