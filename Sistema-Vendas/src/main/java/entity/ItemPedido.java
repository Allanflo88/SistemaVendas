package entity;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
    private int numeroItem;
    private int qtdeVendida;
    private Pedido pedido;
    private Produto produto;

    public ItemPedido(int numeroItem, int qtdeVendida) {
        this.numeroItem = numeroItem;
        this.qtdeVendida = qtdeVendida;
    }

    public int getQtdeVendida() {
        return qtdeVendida;
    }
    
    public void setQtdeVendida(int qtdeVendida) {
        produto.setQtdeDisponivel(produto.getQtdeDisponivel() + (this.qtdeVendida - qtdeVendida));
        this.qtdeVendida = qtdeVendida;
    }
    
    public int getNumeroItem() {
        return numeroItem;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        produto.setQtdeDisponivel(produto.getQtdeDisponivel() - qtdeVendida);
    }
}
