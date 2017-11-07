package entity;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;


@Entity
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
    private int numero;
    private String dataEmissaoPedido;
    private String dataPagto;
    private boolean status;
    private Cliente cliente;
    private Vendedor vendedor;
    private ArrayList<ItemPedido> itensPedidos;

    public Pedido(int numero, String dataEmissaoPedido) {
        this.numero = numero;
        this.dataEmissaoPedido = dataEmissaoPedido;
        itensPedidos = new ArrayList<ItemPedido>();
        status = false;
    }
    
    public String getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(String dataPagto) {
        this.dataPagto = dataPagto;
        status = true;
    }

    public boolean getStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public int getNumero() {
        return numero;
    }

    public String getDataEmissaoPedido() {
        return dataEmissaoPedido;
    }
    
    public ArrayList<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void addItemPedido(ItemPedido item){
        itensPedidos.add(item);
        item.setPedido(this);
        cliente.setLimiteDisp(cliente.getLimiteDisp() - (item.getQtdeVendida() * item.getProduto().getPrecoUnit()));
    }
}
