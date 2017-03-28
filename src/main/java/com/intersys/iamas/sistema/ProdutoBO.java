package com.intersys.iamas.sistema;

import java.io.Serializable;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.intersys.persistencia.PersistenciaException;
import com.intersys.utilmessage.UtilVariados;

public class ProdutoBO implements Serializable {
	private static ProdutoBO instancia;

	public static synchronized ProdutoBO getInstancia() {
		if (instancia == null) {
			instancia = new ProdutoBO();
		}
		return instancia;
	}

	private ProdutoView produtoView;
	private JTable jTableLista;

	private void validar() throws Exception {
		String codpro = this.produtoView.getTfCodProduto().getText();
		String descricao = this.produtoView.getTfDescricao().getText();
		String codgru = this.produtoView.getTfCodGrupo().getText();
		String codsbgru = this.produtoView.getTfCodSubGrupo().getText();
		String especie = this.produtoView.getTfEspecie().getText().toUpperCase();
		String estoque = this.produtoView.getTfEstoque().getText();

		if (codpro.isEmpty()) {
			throw new Exception("Campo código de produto não pode ser null!");
		}

		if (descricao.isEmpty()) {
			throw new Exception("Campo descrição não pode se null!");
		}

		if (codgru.isEmpty()) {
			throw new Exception("Campo código de grupo não pode ser null!");
		}

		if (codsbgru.isEmpty()) {
			throw new Exception("Campo código de subgrupo não pode ser null!");
		}

		if (especie.isEmpty()) {
			throw new Exception("Campo unidade não pode ser null!");
		}

		if (estoque.isEmpty()) {
			throw new Exception("Campo estoque não pode ser null!");
		}

		ProdutoTO produtoTO = new ProdutoTO();
		produtoTO.setCodProduto(codpro);
		produtoTO.setDescricao(descricao.toUpperCase());
		produtoTO.setCodGrupo(new Integer(codgru));
		produtoTO.setCodSrubGrupo(new Integer(codsbgru));
		produtoTO.setEspecie(especie);
		produtoTO.setEstoque(new Integer(estoque));
		ProdutoPO produtoPO = ProdutoPO.getInstancia();
		produtoPO.setProdutoTO(produtoTO);
		produtoPO.inserir();

	}

	public void salvar() throws Exception {
		this.validar();
	}

	private void validarUpdate() throws Exception {
		int codSelecionado = this.jTableLista.getSelectedRow();
		String codpro = this.jTableLista.getValueAt(codSelecionado, 0).toString();
		String descricao = this.produtoView.getTfDescricao().getText().toUpperCase();
		String codgru = this.produtoView.getTfCodGrupo().getText();
		String codsbgru = this.produtoView.getTfCodSubGrupo().getText();
		String especie = this.produtoView.getTfEspecie().getText().toUpperCase();
		String estoque = this.produtoView.getTfEstoque().getText();
		if (descricao.isEmpty()) {
			throw new Exception("Campo descrição não pode se null!");
		}

		if (codgru.isEmpty()) {
			throw new Exception("Campo código de grupo não pode ser null!");
		}

		if (codsbgru.isEmpty()) {
			throw new Exception("Campo código de subgrupo não pode ser null!");
		}

		if (especie.isEmpty()) {
			throw new Exception("Campo unidade não pode ser null!");
		}

		if (estoque.isEmpty()) {
			throw new Exception("Campo estoque não pode ser null!");
		}

		ProdutoTO produtoTO = new ProdutoTO();
		produtoTO.setCodProduto(codpro);
		produtoTO.setDescricao(descricao);
		produtoTO.setCodGrupo(new Integer(codgru));
		produtoTO.setCodSrubGrupo(new Integer(codsbgru));
		produtoTO.setEspecie(especie);
		produtoTO.setEstoque(new Integer(estoque));
		ProdutoPO produtoPO = ProdutoPO.getInstancia();
		produtoPO.setProdutoTO(produtoTO);
		produtoPO.atualizar();

	}

	public void atualizar() throws Exception {
		this.validarUpdate();
	}

	public void preencherTabela() throws Exception {
		ProdutoPO produtoPO = new ProdutoPO();
		try {
			List<ProdutoTO> listaTabela = produtoPO.listartodos();
			DefaultTableModel model = (DefaultTableModel) this.jTableLista.getModel();
			model.setNumRows(0);
			for (ProdutoTO produtoTO : listaTabela) {
				model.addRow(new Object[] { produtoTO.getCodProduto(), produtoTO.getDescricao(),
						produtoTO.getGrupoTO().getGp_nome(), produtoTO.getSubGrupoTO().getSg_nome(),
						produtoTO.getEstoque() });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscaID() throws Exception {
		String codpro = this.produtoView.getTfCombobox().getText();

		if (!codpro.isEmpty() && !UtilVariados.eNumerico(codpro)) {
			throw new Exception("Para pequisar por código somente numérico!");
		}

		if (!codpro.isEmpty() && UtilVariados.eNumerico(codpro)) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setCodProduto(codpro);
			ProdutoPO produtoPO = ProdutoPO.getInstancia();
			produtoPO.setProdutoTO(produtoTO);

			try {
				List<ProdutoTO> listaID = produtoPO.buscarporID();
				DefaultTableModel model = (DefaultTableModel) this.jTableLista.getModel();
				model.setRowCount(0);
				for (ProdutoTO produtoTO2 : listaID) {
					model.addRow(new Object[] { produtoTO2.getCodProduto(), produtoTO2.getDescricao(),
							produtoTO2.getGrupoTO().getGp_nome(), produtoTO2.getSubGrupoTO().getSg_nome(),
							produtoTO2.getEstoque() });
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void buscaDescricao() throws Exception {
		String descricao = this.produtoView.getTfCombobox().getText();

		if (!descricao.isEmpty()) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setDescricao(descricao);
			ProdutoPO produtoPO = ProdutoPO.getInstancia();
			produtoPO.setProdutoTO(produtoTO);

			try {
				List<ProdutoTO> listadescricao = produtoPO.buscarporDescri();
				DefaultTableModel model = (DefaultTableModel) this.jTableLista.getModel();
				model.setRowCount(0);
				for (ProdutoTO produtoTO2 : listadescricao) {
					model.addRow(new Object[] { produtoTO2.getCodProduto(), produtoTO2.getDescricao(),
							produtoTO2.getGrupoTO().getGp_nome(), produtoTO2.getSubGrupoTO().getSg_nome(),
							produtoTO2.getEstoque() });

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void preencherCampos() throws PersistenciaException {
		int codSelecionado = this.jTableLista.getSelectedRow();
		String codpro = this.jTableLista.getValueAt(codSelecionado, 0).toString();
		ProdutoTO produtoTO2 = new ProdutoTO();
		produtoTO2.setCodProduto(codpro);
		ProdutoPO produtoPO = new ProdutoPO();
		produtoPO.setProdutoTO(produtoTO2);
		try {
			List<ProdutoTO> listacampos = produtoPO.listartodosCampos();
			for (ProdutoTO produtoTO : listacampos) {
				int teste = (int) produtoTO.getEstoque();
				this.produtoView.getTfCodProduto().setText(String.valueOf(produtoTO.getCodProduto()));
				this.produtoView.getTfDescricao().setText(produtoTO.getDescricao());
				this.produtoView.getTfCodGrupo().setText(String.valueOf(produtoTO.getGrupoTO().getCodGrupo()));
				this.produtoView.getTfGrupo().setText(produtoTO.getGrupoTO().getGp_nome());
				this.produtoView.getTfCodSubGrupo()
						.setText(String.valueOf(produtoTO.getSubGrupoTO().getCod_subGrupo()));
				this.produtoView.getTfSubGrupo().setText(produtoTO.getSubGrupoTO().getSg_nome());
				this.produtoView.getTfEspecie().setText(produtoTO.getEspecie());
				this.produtoView.getTfEstoque().setText(String.valueOf(teste));

			}
		} catch (Exception e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void validaCodPro() throws Exception {
		String codpro = this.produtoView.getTfCodProduto().getText();

		if (!codpro.isEmpty() && UtilVariados.eNumerico(codpro)) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setCodProduto(codpro);

			ProdutoPO produtoPO = ProdutoPO.getInstancia();
			produtoPO.setProdutoTO(produtoTO);
			produtoTO = produtoPO.vaerifCodPro();
			if (produtoTO != null) {
				throw new Exception("código já existe!");
			}
		}
	}

	public void validaCodGrupo() throws Exception {
		String codgru = this.produtoView.getTfCodGrupo().getText();
		if (!codgru.isEmpty() && UtilVariados.eNumerico(codgru)) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setCodGrupo(new Integer(codgru));
			ProdutoPO produtoPO = ProdutoPO.getInstancia();
			produtoPO.setProdutoTO(produtoTO);
			produtoTO = produtoPO.verifCodGrupo();
			if (produtoTO == null) {
				throw new Exception("códgio de grupo não existe!");
			}
			this.produtoView.getTfGrupo().setText(produtoTO.getGrupoTO().getGp_nome());
		}

	}

	public void verifCodSubGru() throws Exception {
		String codsubgru = this.produtoView.getTfCodSubGrupo().getText();
		if (!codsubgru.isEmpty() && UtilVariados.eNumerico(codsubgru)) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setCodSrubGrupo(new Integer(codsubgru));
			ProdutoPO produtoPO = new ProdutoPO();
			produtoPO.setProdutoTO(produtoTO);
			produtoTO = produtoPO.verifCodSubGru();
			if (produtoTO == null) {
				throw new Exception("código do subgrupo não existe!");
			}
			this.produtoView.getTfSubGrupo().setText(produtoTO.getSubGrupoTO().getSg_nome());
		}

	}

	public void valdCampoPro() throws Exception {
		String codpro = this.produtoView.getTfCodProduto().getText();
		if (!UtilVariados.eNumerico(codpro)) {
			throw new Exception("Campo código de produto somente número!");
		}

		if (codpro.length() >= 10 - 1) {
			throw new Exception("Imforme código do produto abaixo de 999.999.999 !");
		}

	}

	public void valdCampoGru() throws Exception {
		String codgru = this.produtoView.getTfCodGrupo().getText();
		if (!UtilVariados.eNumerico(codgru)) {
			throw new Exception("Código de grupo somente número!");
		}

		if (codgru.length() >= 10 - 1) {
			throw new Exception("Imforme código do grupo abaixo de 999.999.999 !");
		}

	}

	public void valdCampoSubGru() throws Exception {
		String codsbgru = this.produtoView.getTfCodSubGrupo().getText();
		if (!UtilVariados.eNumerico(codsbgru)) {
			throw new Exception("Código de subgrupo somente número!");
		}

		if (codsbgru.length() >= 10 - 1) {
			throw new Exception("Imforme código Subgrupo abaixo de 999.999.999 !");
		}

	}

	public void validaDescricao() throws Exception {
		String descricao = this.produtoView.getTfDescricao().getText();

		if (descricao.length() >= 30 - 1) {
			throw new Exception("Campo Descrição máximo 30 caracter");
		}
	}

	public void validCampoEtoque() throws Exception {
		String estoque = this.produtoView.getTfEstoque().getText();
		if (!UtilVariados.eNumerico(estoque)) {
			throw new Exception("Estoque somente número!");
		}

		if (estoque.length() >= 10 - 1) {
			throw new Exception("Imforme Estoque abaixo de 999.999.999 !");
		}
	}

	public void ValidaEspecie() throws Exception {
		String especie = this.produtoView.getTfEspecie().getText();

		if (especie.length() >= 30 - 1) {
			throw new Exception("Campo especie máximo de 30 caracter!");
		}
	}

	public ProdutoView getProdutoView() {
		return produtoView;
	}

	public void setProdutoView(ProdutoView produtoView) {
		this.produtoView = produtoView;
	}

	public JTable getjTableLista() {
		return jTableLista;
	}

	public void setjTableLista(JTable jTableLista) {
		this.jTableLista = jTableLista;
	}

}
