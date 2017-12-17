package services;

import java.util.List;

import DAO.VendedorDAO;
import entity.Vendedor;

public class VendedorService extends Service {
	
	VendedorDAO dao = new VendedorDAO();
	
	public List<Vendedor> getVendedores() {
		List<Vendedor> vendedores = dao.getAll(Vendedor.class);
		dao.closeEntityManager();
		return vendedores;
	}
	
	public Vendedor salvar(Vendedor vendedor) {
		vendedor = dao.save(vendedor);
		dao.closeEntityManager();
		return vendedor;
	}
	
	public void excluir(Vendedor vendedor) {
		vendedor = dao.getById(Vendedor.class, vendedor.getCpf());
		dao.remove(vendedor);
		dao.closeEntityManager();
	}
	
	public void atualizar(Vendedor vendedor) {
		dao.save(vendedor);
		dao.closeEntityManager();
	}
	
	public Vendedor consultar(String cpf) {
		Vendedor vendedor = dao.getById(Vendedor.class, cpf);
		return vendedor;
	}

}
