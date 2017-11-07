package com.poo.model;

/**
 *
 * @author Airton Brasil 0030481611002
 * @author Allan Flores 0030481611001
 * @author Caique Siqueira 0030481611009
 */
public class Produto {
    private int codigo;
    private String descricao;
    private int qtdeDisponivel;
    private double precoUnit;
    private int estoqueMin;

    public Produto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public int getQtdeDisponivel() {
        return qtdeDisponivel;
    }
    
    public void setQtdeDisponivel(int qtdeDisponivel) {
        this.qtdeDisponivel = qtdeDisponivel;
    }
    
    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }
    
    public int getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }  
    
    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
