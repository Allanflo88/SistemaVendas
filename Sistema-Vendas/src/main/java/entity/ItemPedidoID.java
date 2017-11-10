package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class ItemPedidoID implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int pedido;
    private int produto;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pedido;
		result = prime * result + produto;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoID other = (ItemPedidoID) obj;
		if (pedido != other.pedido)
			return false;
		if (produto != other.produto)
			return false;
		return true;
	}
	
    
    

}
