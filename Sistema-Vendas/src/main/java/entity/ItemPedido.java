package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	@ManyToOne 
	private Produto produto;
	@ManyToOne
	private Pedido pedido;
    private int qtdeVendida;
    
    
    public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getQtdeVendida() {
        return qtdeVendida;
    }
    
    public void setQtdeVendida(int qtdeVendida) {
    	System.out.println("Before = " + produto.getQtdeDisponivel());
        produto.setQtdeDisponivel(produto.getQtdeDisponivel() + (this.qtdeVendida - qtdeVendida));
        this.qtdeVendida = qtdeVendida;
        System.out.println("After = " + produto.getQtdeDisponivel());
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


