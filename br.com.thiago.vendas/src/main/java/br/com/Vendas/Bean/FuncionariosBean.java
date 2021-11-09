package br.com.Vendas.Bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.Constantes;
import br.com.Vendas.util.JSFUtil;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "MBFuncionarios")
@ViewScoped
public class FuncionariosBean {

	@Getter
	@Setter
	private Funcionario funcionario;

	@Getter
	@Setter
	private ArrayList<Funcionario> itens;

	@Getter
	@Setter
	private ArrayList<Funcionario> itensFiltrados;

	@Getter
	@Setter
	private String acao;

	@Getter
	@Setter
	private Long codigo;

	// @PostConstruct
	public void prepararPesquisa() {
		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			itens = (ArrayList<Funcionario>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FuncionariosDAO fdao = new FuncionariosDAO();
				funcionario = fdao.buscarPorCodigo(codigo);
			} else {
				funcionario = new Funcionario();
			}
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void novo() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.salvar(funcionario);
			funcionario = new Funcionario();
			JSFUtil.adicionarMensagemSucesso(Constantes.FUNCIONARIO_SALVO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.excluir(funcionario);
			JSFUtil.adicionarMensagemSucesso(Constantes.FUNCIONARIO_EXCLUIDO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(Constantes.FUNCIONARIO_ASSOCIADO);
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.editar(funcionario);
			JSFUtil.adicionarMensagemSucesso(Constantes.FUNCIONARIO_EDITADO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
}
