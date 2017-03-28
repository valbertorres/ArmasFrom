package com.intersys.iamas.sistema;

import java.sql.SQLException;

import javax.swing.JTable;

public class GrupoCtrl {

	private static GrupoCtrl instancia;

	public static synchronized GrupoCtrl getInstancia() {
		if (instancia == null) {
			instancia = new GrupoCtrl();
		}
		return instancia;
	}

	private GrupoView grupoView;
	private JTable tabelaGrupo;

	public void inseri() throws Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setGrupoView(grupoView);
		grupoBO.inserir();
	}

	public void atualizar() throws Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setGrupoView(grupoView);
		grupoBO.atualizar();
	}

	public void buscaId() throws SQLException, Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setGrupoView(grupoView);
		grupoBO.buscaId();
	}

	public void buscaDescricao() throws SQLException, Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setGrupoView(grupoView);
		grupoBO.buscaDescricao();
	}

	public void preencherTabela() throws SQLException, Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setTabelaGrupo(tabelaGrupo);
		grupoBO.preencherTabela();
	}

	public void veiricaGru() throws SQLException, Exception {
		GrupoBO grupoBO = GrupoBO.getInstancia();
grupoBO.setGrupoView(grupoView);
		grupoBO.verificaGru();
	}


	public GrupoView getGrupoView() {
		return grupoView;
	}

	public void setGrupoView(GrupoView grupoView) {
		this.grupoView = grupoView;
	}

	public JTable getTabelaGrupo() {
		return tabelaGrupo;
	}

	public void setTabelaGrupo(JTable tabelaGrupo) {
		this.tabelaGrupo = tabelaGrupo;
	}

}
