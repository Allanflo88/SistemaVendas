package services;


import java.util.List;
import DAO.PedidoDAO;

import entity.Pedido;

public class PedidoService extends Service{
	PedidoDAO dao = new PedidoDAO();

	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = dao.getAll(Pedido.class);
		dao.closeEntityManager();
		return pedidos;

	}

	public Pedido salvar(Pedido pedido) {
		pedido = dao.save(pedido);
		dao.closeEntityManager();
		return pedido;
	}

	public void excluir(Pedido pedido) {
		pedido = dao.getById(Pedido.class, pedido.getNumero());
		dao.remove(pedido);
		dao.closeEntityManager();
	}

	public void atualizar(Pedido pedido) {
		dao.save(pedido);
		dao.closeEntityManager();
	}
}
