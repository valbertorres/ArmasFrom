package com.intersys.iamas.sistema;

import java.sql.SQLException;

import javax.swing.JTable;

public class SubGruCtrl {

	private static SubGruCtrl instancia;

	public static synchronized SubGruCtrl getInstancia() {
		if (instancia == null) {
			instancia = new SubGruCtrl();
		}
		return instancia;
	}

	private JTable tabelaSubGru;
	private SubGruView subGruView;

	public void inserir() throws Exception {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setSubGruView(subGruView);
		subGruBO.inserir();
	}

	public void atualizar() throws Exception {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setSubGruView(subGruView);
		subGruBO.atualizar();
	}

	public void preecherTabela() throws ClassNotFoundException, SQLException {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setTabelaSubGru(tabelaSubGru);
		subGruBO.preencherTabela();
	}

	public void buscaId() throws Exception {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setSubGruView(subGruView);
		subGruBO.buscaId();
	}

	public void buscaNome() throws ClassNotFoundException, SQLException {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setSubGruView(subGruView);
		subGruBO.buscaNomeSubgru();
	}

	public void verificaSubgru() throws Exception {
		SubGruBO subGruBO = SubGruBO.getInstancia();
		subGruBO.setSubGruView(subGruView);
		subGruBO.verificaNome();
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
