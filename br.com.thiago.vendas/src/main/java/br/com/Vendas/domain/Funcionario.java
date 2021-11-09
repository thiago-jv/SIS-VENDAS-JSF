package br.com.Vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionarios")
@NamedQueries({ @NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codigo = :codigo") })
public class Funcionario {

	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "fun_codigo")
	private Long codigo;

	@Getter
	@Setter
	@NotEmpty(message = "Insira o Nome")
	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;

	@Getter
	@Setter
	@CPF(message = "CPF Incorreto")
	@Column(name = "fun_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@Getter
	@Setter
	@NotEmpty(message = "Insira a Senha")
	@Size(min = 5, max = 8, message = "Sua senha deve conter de 5 a 8 caracteres")
	@Column(name = "fun_senha", length = 50, nullable = false)
	private String senha;

	@Getter
	@Setter
	@NotEmpty(message = "Insira um cargo")
	@Column(name = "fun_funcao", length = 50, nullable = false)
	private String funcao;

	public Funcionario() {
	}

	public Funcionario(Long codigo, String nome, String cpf, String senha, String funcao) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", funcao="
				+ funcao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
