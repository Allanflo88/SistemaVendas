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
    	if(validaCPF(cpf)) {
    		this.cpf = cpf;
    	}
		
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
    
    public boolean validaCPF(String cpf) {
    	/*cpf = cpf.replace("-","").replaceAll(".","");
    	int[] vetor = new int[11];
    	int dig1 = Integer.parseInt(cpf.substring(8,9));
    	int dig2 = Integer.parseInt(cpf.substring(9,10));
    	int i = 2;
    	for(int c = 0;c < cpf.length();c++) {
    		vetor[c] = Integer.parseInt(cpf.substring(c, c+1)) * i;
    		i++;
    	}
    	if(dig1 == 1) {
    		i = 2;
    		for(int c = 0;c < cpf.length();c++) {
        		vetor[c] = Integer.parseInt(cpf.substring(c, c+1)) * i;
        		i++;
        	}
    		if(dig2 == 1) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}*/
    	return true;
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
    
    
    
    
}
