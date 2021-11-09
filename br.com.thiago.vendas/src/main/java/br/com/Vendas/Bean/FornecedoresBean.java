package br.com.Vendas.Bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.Constantes;
import br.com.Vendas.util.JSFUtil;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	@Setter
	private Fornecedor fornecedores;

	@Getter
	@Setter
	private ArrayList<Fornecedor> itens;
	
	@Getter
	@Setter
	private ArrayList<Fornecedor> itensFiltrados;

	@Getter
	@Setter
	private String acao;
	
	@Getter
	@Setter
	private Long codigo;

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = (ArrayList<Fornecedor>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FornecedoresDAO fdao = new FornecedoresDAO();
				fornecedores = fdao.buscarPorCodigo(codigo);
			} else {
				fornecedores = new Fornecedor();
			}
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void novo() {
		fornecedores = new Fornecedor();
	}

	public void salvar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			fornecedores = new Fornecedor();
			// itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso(Constantes.FORNECEDOR_SALVO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
			JSFUtil.adicionarMensagemSucesso(Constantes.FORNECEDOR_EXCLUIDO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(Constantes.FORNECEDOR_ASSOCIADO);
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			JSFUtil.adicionarMensagemSucesso(Constantes.FORNECEDOR_EDITADO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public Fornecedor getFornecedores() {
		if(fornecedores == null) {
			fornecedores = new Fornecedor();
		}
		return fornecedores;
	}
	
}
