package com.intersys.iamas.sistema;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.intersys.utilmessage.UtilVariados;

public class SubGruBO {

	private static SubGruBO instancia;

	public static synchronized SubGruBO getInstancia() {
		if (instancia == null) {
			instancia = new SubGruBO();
		}
		return instancia;
	}

	private JTable tabelaSubGru;
	private SubGruView subGruView;

	private void validar() throws Exception {
		String nomeSubGru = this.subGruView.getTfNomeDoGrupo().getText().toUpperCase();

		if (nomeSubGru.isEmpty()) {
			throw new Exception("Informe o nome do subgrupo!");
		}

		SubGrupoTO subGrupoTO = new SubGrupoTO();
		subGrupoTO.setSg_nome(nomeSubGru);

		SubGruPO subGruPO = SubGruPO.getInstancia();
		subGruPO.setSubGrupoTO(subGrupoTO);
		subGruPO.inserir();
	}

	public void inserir() throws Exception {
		this.validar();
	}

	private void validaAtualizar() throws Exception {
		String codSubGru = this.subGruView.getLbCodigoDoGrupo().getText();
		String nomeSubGru = this.subGruView.getTfNomeDoGrupo().getText().toUpperCase();
		if (nomeSubGru.isEmpty()) {
			throw new Exception("Informe o nome do subgrupo!");
		}

		SubGrupoTO subGrupoTO = new SubGrupoTO();
		subGrupoTO.setCod_subGrupo(new Integer(codSubGru));
		subGrupoTO.setSg_nome(nomeSubGru);

		SubGruPO subGruPO = SubGruPO.getInstancia();
		subGruPO.setSubGrupoTO(subGrupoTO);
		subGruPO.atualizar();
	}

	public void atualizar() throws Exception {
		this.validaAtualizar();
	}

	public void preencherTabela() throws ClassNotFoundException, SQLException {
		SubGruPO subGruPO = SubGruPO.getInstancia();
		List<SubGrupoTO> listaTabela = subGruPO.preecherTabela();
		DefaultTableModel model = (DefaultTableModel) this.tabelaSubGru.getModel();
		model.setRowCount(0);
		for (SubGrupoTO subGrupoTO : listaTabela) {
			model.addRow(new Object[] { subGrupoTO.getCod_subGrupo(), subGrupoTO.getSg_nome() });
		}

	}

	public void buscaId() throws Exception {
		String codSubgru = this.subGruView.getTfPesquisa().getText();
		if(!codSubgru.isEmpty() && !UtilVariados.eNumerico(codSubgru)){
			throw new Exception("Para pequisar código somente númerico!");
		}
		
		if (!codSubgru.isEmpty() && UtilVariados.eNumerico(codSubgru)) {

			SubGrupoTO subGrupoTO = new SubGrupoTO();
			subGrupoTO.setCod_subGrupo(new Integer(codSubgru));

			SubGruPO subGruPO = SubGruPO.getInstancia();
			subGruPO.setSubGrupoTO(subGrupoTO);
			List<SubGrupoTO> listaTabela = subGruPO.buscaId();
			DefaultTableModel model = (DefaultTableModel) this.tabelaSubGru.getModel();
			model.setRowCount(0);
			for (SubGrupoTO subGrupoTO2 : listaTabela) {
				model.addRow(new Object[] { subGrupoTO2.getCod_subGrupo(), subGrupoTO2.getSg_nome() });
			}

		}
	}

	public void buscaNomeSubgru() throws ClassNotFoundException, SQLException {
		String nomeSubgru = this.subGruView.getTfPesquisa().getText();
		if (!nomeSubgru.isEmpty()) {

			SubGrupoTO subGrupoTO = new SubGrupoTO();
			subGrupoTO.setSg_nome(nomeSubgru);

			SubGruPO subGruPO = SubGruPO.getInstancia();
			subGruPO.setSubGrupoTO(subGrupoTO);
			List<SubGrupoTO> listaTabela = subGruPO.buscaNome();
			DefaultTableModel model = (DefaultTableModel) this.tabelaSubGru.getModel();
			model.setRowCount(0);
			for (SubGrupoTO subGrupoTO2 : listaTabela) {
				model.addRow(new Object[] { subGrupoTO2.getCod_subGrupo(), subGrupoTO2.getSg_nome() });
			}
		}

	}

	public void verificaNome() throws ClassNotFoundException, Exception {
		String nomeSubgru = this.subGruView.getTfNomeDoGrupo().getText();
		if (!nomeSubgru.isEmpty()) {

			SubGrupoTO subGrupoTO = new SubGrupoTO();
			subGrupoTO.setSg_nome(nomeSubgru);

			SubGruPO subGruPO = SubGruPO.getInstancia();
			subGruPO.setSubGrupoTO(subGrupoTO);
			subGrupoTO = subGruPO.verificaSubgru();

			if (subGrupoTO != null) {
				throw new Exception("Esse SubGrupo já existe!");
			}
		}
	}

	public JTable getTabelaSubGru() {
		return tabelaSubGru;
	}

	public void setTabelaSubGru(JTable tabelaSubGru) {
		this.tabelaSubGru = tabelaSubGru;
	}

	public SubGruView getSubGruView() {
		return subGruView;
	}

	public void setSubGruView(SubGruView subGruView) {
		this.subGruView = subGruView;
	}

}
