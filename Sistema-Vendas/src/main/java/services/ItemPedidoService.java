package services;

import java.util.List;

import DAO.ItemPedidoDAO;

import entity.ItemPedido;

public class ItemPedidoService extends Service{
	ItemPedidoDAO dao = new ItemPedidoDAO();

	public List<ItemPedido> getItensPedido() {
		List<ItemPedido> itens = dao.getAll(ItemPedido.class);
		dao.closeEntityManager();
		return itens;

	}

	public ItemPedido salvar(ItemPedido item) {
		item = dao.save(item);
		dao.closeEntityManager();
		return item;
	}

	public void excluir(ItemPedido item) {
		item = dao.getById(ItemPedido.class, item.getId());
		dao.remove(item);
		dao.closeEntityManager();
	}

	public void atualizar(ItemPedido item) {
		dao.save(item);
		dao.closeEntityManager();
	}
}
