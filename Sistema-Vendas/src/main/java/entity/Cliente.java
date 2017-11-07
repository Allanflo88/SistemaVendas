package com.poo.model;

import java.util.ArrayList;

/**
 *
 * @author Airton Brasil 0030481611002
 * @author Allan Flores 0030481611001
 * @author Caique Siqueira 0030481611009
 */
public class Cliente extends Pessoa {
    private double limiteCred;
    private double limiteDisp;
    private ArrayList<Pedido> pedidos;
    
    public Cliente(String cpf, String nome, double limiteCred) {
        super(cpf, nome);
        this.limiteCred = limiteCred;
        limiteDisp = limiteCred;
        pedidos = new ArrayList<Pedido>();
    }

    public double getLimiteCred() {
        return limiteCred;
    }

    public double getLimiteDisp() {
        return limiteDisp;
    }

    public void setLimiteDisp(double limiteDisp) {
        this.limiteDisp = limiteDisp;
    }
    
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
        pedido.setCliente(this);
    }
}
