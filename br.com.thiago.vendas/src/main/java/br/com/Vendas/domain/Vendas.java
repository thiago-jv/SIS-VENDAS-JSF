package br.com.Vendas.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_vendas")
@NamedQueries({ @NamedQuery(name = "Venda.listar", query = "SELECT venda FROM Vendas venda"),
		@NamedQuery(name = "Venda.buscarPorCodigo", query = "SELECT venda FROM Vendas venda WHERE venda.codigo = :codigo") })
public class Vendas {

	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "ven_codigo")
	private Long codigo;

	@Getter
	@Setter
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ven_horario", nullable = false)
	private Date horario;

	@Getter
	@Setter
	@Column(name = "ven_valor_total", nullable = false, scale = 2, precision = 7)
	private BigDecimal valor_total;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_funcionarios_fun_codigo", referencedColumnName = "fun_codigo", nullable = false)
	private Funcionario funcionario;

	public Vendas() {
	}

	public Vendas(Long codigo, Date horario, BigDecimal valor_total, Funcionario funcionario) {
		this.codigo = codigo;
		this.horario = horario;
		this.valor_total = valor_total;
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Vendas [codigo=" + codigo + ", horario=" + horario + ", valor_total=" + valor_total + ", funcionario="
				+ funcionario + "]";
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
		Vendas other = (Vendas) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
