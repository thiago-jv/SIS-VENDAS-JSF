package br.com.Vendas.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_produtos")
@NamedQueries({ 
	    @NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.codigo = :codigo") })
public class Produto {

	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "pro_codigo")
	private Long codigo;

	@Getter
	@Setter
	@NotEmpty(message = "Insira a Descri��o")
	@Column(name = "pro_descricao", length = 50, nullable = false)
	private String descricao;

	@Getter
	@Setter
	@NotNull(message = "Insira o Preço")
	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0")
	@Column(name = "pro_preco", nullable = false, scale = 2, precision = 7)
	private BigDecimal preco;

	@Getter
	@Setter
	@NotNull(message = "Insira a Quantidade")
	@Min(value = 0, message = "O valor não pode ser menor que 0")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@Getter
	@Setter
	@NotNull(message = "Insira um Fornecedor")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_fornecedores_for_codigo", referencedColumnName = "for_codigo", nullable = false)
	private Fornecedor fornecedor;

	public Produto() {
	}
	
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", quantidade="
				+ quantidade + ", fornecedor=" + fornecedor + "]";
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Produto(Long codigo, String descricao, BigDecimal preco, Integer quantidade, Fornecedor fornecedor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
	}

	
	
}
