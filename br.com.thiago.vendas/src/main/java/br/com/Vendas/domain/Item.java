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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_itens")
@NamedQueries({ 
	    @NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
		@NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE item.codigo = :codigo") })
public class Item {
	
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "ite_codigo")
	private Long codigo;

	@Getter
	@Setter
	@Column(name = "ite_quantidade", nullable = false)
	private Integer quantidade;

	@Getter
	@Setter
	@Column(name = "ite_valor_parcial", nullable = false, scale = 2, precision = 7)
	private BigDecimal valor_parcial;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_produtos_pro_codigo", referencedColumnName = "pro_codigo", nullable = false)
	private Produto produto;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_vendas_ven_codigo", referencedColumnName = "ven_codigo", nullable = false)
	private Vendas venda;

	public Item() {
	}
	
	public Item(Long codigo, Integer quantidade, BigDecimal valor_parcial, Produto produto, Vendas venda) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.valor_parcial = valor_parcial;
		this.produto = produto;
		this.venda = venda;
	}
	
	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", quantidade=" + quantidade + ", valor_parcial=" + valor_parcial
				+ ", produto=" + produto + ", venda=" + venda + "]";
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
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
