package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;



@Entity
@IdClass(ItemPedidoID.class)
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id private Produto produto;
	@Id private Pedido pedido;
    private int numeroItem;
    private int qtdeVendida;
    
    
    public void setNumeroItem(int numeroItem) {
		this.numeroItem = numeroItem;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		produto.setQtdeDisponivel(produto.getQtdeDisponivel() - qtdeVendida);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
		
	}
    
    
    
    
}


