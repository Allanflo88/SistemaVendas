package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;

import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import services.ClienteService;
import services.ItemPedidoService;
import services.PedidoService;
import services.ProdutoService;

@ManagedBean
@SessionScoped
public class PedidoMB {
	private Pedido pedido = new Pedido();
	private PedidoService pedidoService = new PedidoService();
	private ItemPedidoService itemService = new ItemPedidoService();
	private ClienteService clienteService = new ClienteService();
	private ProdutoService produtoService = new ProdutoService();
	private List<ItemPedido> itens = new ArrayList<>();
	private ItemPedido item = new ItemPedido();
	private boolean edicao = false;
	private List<Pedido> filteredPedidos;
	private double valorTotal = 0;
	
	public List<Pedido> getFilteredPedidos() {
		return filteredPedidos;
	}
	public void setFilteredPedidos(List<Pedido> filteredPedidos) {
		this.filteredPedidos = filteredPedidos;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Pedido> getPedidos() {
		return pedidoService.getPedidos();
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	}	
	public ItemPedido getItem() {
		return item;
	}
	public void setItem(ItemPedido itemPedido) {
		this.item = itemPedido;
	}
	
	public boolean isEdicao() {
		return edicao;
	}
	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	public boolean isCriacao() {
		return !edicao;
	}

	public void setCriacao(boolean criacao) {
		this.edicao = !criacao;
	}
	public void selecionar() {
		edicao = true;
	}
	
	public void addItem() {
		item.setPedido(pedido);
		itens.add(item);
		valorTotal += item.getQtdeVendida() * item.getProduto().getPrecoUnit();
		item = new ItemPedido();
	}
	public void removeItem(ItemPedido rmv) {
		itens.remove(rmv);
		valorTotal -= rmv.getQtdeVendida() * rmv.getProduto().getPrecoUnit();
	}
	public void alterItem() {
		itens.remove(item);
		valorTotal -= item.getQtdeVendida() * item.getProduto().getPrecoUnit();
		itens.add(item);
		valorTotal += item.getQtdeVendida() * item.getProduto().getPrecoUnit();
		item = new ItemPedido();
	}
	public void salvar() {
		pedidoService.salvar(pedido);
		for(ItemPedido i : itens) {
			itemService.salvar(i);
			produtoService.atualizar(i.getProduto());
		}
		Cliente cli = pedido.getCliente();
		cli.setLimiteDisp(cli.getLimiteDisp() - valorTotal);
		clienteService.atualizar(pedido.getCliente());
		pedido = new Pedido();
		itens = new ArrayList<>();
	}
	
	public void excluir(Pedido pedido) {
		pedidoService.excluir(pedido);
	}
	
	public void atualizar(RowEditEvent event) {
		Pedido ped = (Pedido) event.getObject();
		pedidoService.atualizar(ped);
	}
	
	public double getValorTotal(){
		return valorTotal;
	}
}
