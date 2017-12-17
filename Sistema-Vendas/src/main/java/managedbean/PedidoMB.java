package managedbean;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private ItemPedido itemSelec = new ItemPedido();
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
		itemSelec = item;
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
		cancelaItem();
	}
	public void alterItem() {
		itens.remove(itemSelec);
		valorTotal -= itemSelec.getQtdeVendida() * itemSelec.getProduto().getPrecoUnit();
		itemSelec = new ItemPedido();
		
		itens.add(item);
		valorTotal += item.getQtdeVendida() * item.getProduto().getPrecoUnit();
		item = new ItemPedido();
		cancelaItem();
	}
	
	public void salvar() {
		if (itens.isEmpty()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
			
			FacesMessage msg = new FacesMessage(
				FacesMessage.SEVERITY_WARN,
				rb.getString("messages.error.VendaSemItens.title"),
				rb.getString("messages.error.VendaSemItens.detail")
			);
			
			fc.addMessage(null, msg);	
		} else {
			if (pedidoService.consultar(pedido.getNumero()) != null){
				FacesContext fc = FacesContext.getCurrentInstance();
				ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
				
				FacesMessage msg = new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					rb.getString("messages.error.Erro.title"),
					rb.getString("messages.error.PedidoExiste.detail")
				);
				fc.addMessage(null, msg);
			} else {
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
				valorTotal = 0;
				
				FacesContext fc = FacesContext.getCurrentInstance();
				ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
				
				FacesMessage msg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					rb.getString("messages.info.VendaRealizada.title"),
					rb.getString("messages.info.VendaRealizada.detail")
				);
				fc.addMessage(null, msg);
			}
		}
	}
	
	public void excluir(Pedido pedido) {
		pedidoService.excluir(pedido);
	}
	
	public void atualizar(RowEditEvent event) {
		Pedido ped = (Pedido) event.getObject();
		pedidoService.atualizar(ped);
	}
	public void cancelaItem(){
		edicao = false;
		item = new ItemPedido();
		itemSelec = new ItemPedido();
	}
	public double getValorTotal(){
		return valorTotal;
	}
}
