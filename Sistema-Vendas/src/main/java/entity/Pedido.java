package entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int numero;
	
	@Temporal(TemporalType.DATE)
    private Date dataEmissaoPedido;
	@Temporal(TemporalType.DATE)
    private Date dataPagto;
    private boolean status;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor vendedor;
    @OneToMany(mappedBy="pedido")
    private List<ItemPedido> itensPedidos;

    public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setDataEmissaoPedido(Date dataEmissaoPedido) {
		this.dataEmissaoPedido = dataEmissaoPedido;
	}

	public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
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

    public Date getDataEmissaoPedido() {
        return dataEmissaoPedido;
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
    
    public List<ItemPedido> getItensPedidos(){
    	return itensPedidos;
    }
    
    public void setItensPedidos(List<ItemPedido> itensPedidos){
    	this.itensPedidos = itensPedidos;
    }

    public double getValorTotal(){
    	double total = 0;
    	for (ItemPedido item : itensPedidos){
    		total += item.getQtdeVendida() * item.getProduto().getPrecoUnit();
    	}
    	return total;
    }
}
