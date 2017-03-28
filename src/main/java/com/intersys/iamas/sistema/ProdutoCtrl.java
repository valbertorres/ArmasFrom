package com.intersys.iamas.sistema;

import com.intersys.persistencia.PersistenciaException;

public class ProdutoCtrl {

	private static ProdutoCtrl instancia;

	public static synchronized ProdutoCtrl getInstancia() {
		if (instancia == null) {
			instancia = new ProdutoCtrl();
		}
		return instancia;
	}

	private ProdutoView produtoView;

	public void salvar() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.salvar();
	}

	public void atualizar() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.atualizar();
	}

	public void preencherTabela() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.preencherTabela();
	}

	public void validaCodPro() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.validaCodPro();
	}

	public void existeCodGru() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.validaCodGrupo();
	}

	public void existeCodSubGru() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.verifCodSubGru();
	}

	public void preencherCampos() throws PersistenciaException {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.preencherCampos();
	}

	public void verifcampoPro() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.valdCampoPro();
	}

	public void verifcampoGru() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.valdCampoGru();
	}

	public void verifiCampoSubgru() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.valdCampoSubGru();
	}

	public void verifCampoEstoque() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.validCampoEtoque();
	}

	public void buscaID() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.buscaID();
	}

	public void buscaDescricao() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.buscaDescricao();
	}

	public void validaDescricao() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.validaDescricao();
	}

	public void validaEspecie() throws Exception {
		ProdutoBO produtoBO = ProdutoBO.getInstancia();
		produtoBO.setProdutoView(produtoView);
		produtoBO.ValidaEspecie();
	}

	public ProdutoView getProdutoView() {
		return produtoView;
	}

	public void setProdutoView(ProdutoView produtoView) {
		this.produtoView = produtoView;
	}

}
