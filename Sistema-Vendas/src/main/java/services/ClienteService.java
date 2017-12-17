package services;

import java.util.List;

import DAO.ClienteDAO;
import entity.Cliente;

public class ClienteService extends Service {

	ClienteDAO dao = new ClienteDAO();

	public List<Cliente> getClientes() {
		List<Cliente> clientes = dao.getAll(Cliente.class);
		dao.closeEntityManager();
		return clientes;

	}

	public Cliente salvar(Cliente cliente) {
		cliente = dao.save(cliente);
		dao.closeEntityManager();
		return cliente;
	}

	public void excluir(Cliente cliente) {
		cliente = dao.getById(Cliente.class, cliente.getCpf());
		dao.remove(cliente);
		dao.closeEntityManager();
	}

	public void atualizar(Cliente cliente) {
		dao.save(cliente);
		dao.closeEntityManager();
	}

	public Cliente consultar(String cpf){
		Cliente cliente = dao.getById(Cliente.class, cpf);
		return cliente;
	}
}
