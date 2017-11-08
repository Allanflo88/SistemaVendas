package entity;

import java.util.ArrayList;

import javax.persistence.Entity;


@Entity
public class Vendedor extends Pessoa{
	private static final long serialVersionUID = 1L;
    private double salarioBase;
    private double comissao;
    private ArrayList<Pedido> pedidos;

    
    public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
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