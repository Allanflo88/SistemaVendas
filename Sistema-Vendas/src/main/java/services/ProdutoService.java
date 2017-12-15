package services;

import java.util.List;
import DAO.ProdutoDAO;
import entity.Produto;

public class ProdutoService extends Service {
	ProdutoDAO dao = new ProdutoDAO();

	public List<Produto> getProdutos() {
		List<Produto> produtos = dao.getAll(Produto.class);
		dao.closeEntityManager();
		return produtos;

	}

	public Produto salvar(Produto produto) {
		produto = dao.save(produto);
		dao.closeEntityManager();
		return produto;
	}

	public void excluir(Produto produto) {
		produto = dao.getById(Produto.class, produto.getCodigo());
		dao.remove(produto);
		dao.closeEntityManager();
	}

	public void atualizar(Produto produto) {
		dao.save(produto);
		dao.closeEntityManager();
	}
}
