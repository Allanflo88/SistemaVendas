package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="cpf")
public class Vendedor extends Pessoa{
	private static final long serialVersionUID = 1L;
    private double salarioBase;
    private double comissao;
    @OneToMany(mappedBy="vendedor")
    private List<Pedido> pedidos;

    
    public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getComissao() {
        return comissao;
    }
    
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
        pedido.setVendedor(this);
    }
    
    
}
