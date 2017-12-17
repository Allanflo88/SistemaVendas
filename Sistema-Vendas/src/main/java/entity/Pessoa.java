package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    private String cpf;
	
    private String nome;
    private String endereco;
    private String cidade;
    private String cep;
    private String uf;
    private String ddd;
    private String telefone;


    public void setCpf(String cpf) {
    	this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public static boolean cpfValido(String cpf){
    	cpf = cpf.replace(".", "").replace("-", "");
        int[] numeros = new int[11];
        int verif1, verif2, soma, contIgual;
        if (cpf.length() != 11){
            return false;
        } else {
            for (int c = 0; c < 11; c++){
                try {
                    numeros[c] = Integer.parseInt(cpf.substring(c,c+1));
                } catch (NumberFormatException ex){
                    return false;
                }
            }
            contIgual = 0;
            for (int c = 1; c < 11; c++){
                if (numeros[c] == numeros[c-1]){
                    contIgual++;
                }
            }
            if (contIgual == 10){
                return false;
            }
            soma = 0;
            for (int c = 0; c < 9; c++){
            	soma += numeros[c] * (c + 1);
            }
            verif1 = (soma % 11) % 10 ;
            if (numeros[9] != verif1){
                return false;
            } else {
                soma = 0;
                for (int c = 0, d = 11; c < 10; c++, d--){
                    soma += numeros[c] * d;
                }
                verif2 = ((soma * 10) % 11) % 10;
                if (numeros[10] != verif2){
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
    
    @Override
    public String toString(){
    	return nome;
    }
    
}
