package br.com.Vendas.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.util.Constantes;
import br.com.Vendas.util.JSFUtil;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	
	@Getter
	@Setter
	private Produto produto;

	@Getter
	@Setter
	private ArrayList<Produto> itens;
	
	@Getter
	@Setter
	private ArrayList<Produto> itensFiltrados;
	
	@Getter
	@Setter
	private String acao;
	
	@Getter
	@Setter
	private Long codigo;
	
	@Getter
	@Setter
	private List<Fornecedor> listaFornecedor;

	//@PostConstruct
	public void prepararPesquisa() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			itens = (ArrayList<Produto>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutosDAO fdao = new ProdutosDAO();
				produto = fdao.buscarPorCodigo(codigo);
			} else {
				produto = new Produto();
			}
			FornecedoresDAO dao = new FornecedoresDAO();
			listaFornecedor = dao.listar();
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void novo() {
		produto = new Produto();
	}

	public void salvar() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.salvar(produto);
			produto = new Produto();
			JSFUtil.adicionarMensagemSucesso(Constantes.PRODUTO_SALVO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.excluir(produto);
			JSFUtil.adicionarMensagemSucesso(Constantes.PRODUTO_EXCLUIDO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.editar(produto);
			JSFUtil.adicionarMensagemSucesso(Constantes.PRODUTO_EDITADO_COM_SUCESSO);
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
}
