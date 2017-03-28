package com.intersys.iamas.sistema;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.intersys.utilmessage.UtilVariados;

public class GrupoBO {

	private static GrupoBO instancia;

	public static synchronized GrupoBO getInstancia() {
		if (instancia == null) {
			instancia = new GrupoBO();
		}
		return instancia;
	}

	private GrupoView grupoView;
	private JTable tabelaGrupo;

	private void validar() throws Exception {
		String nomeGru = this.grupoView.getTfNomeDoGrupo().getText().toUpperCase();
		if (nomeGru.isEmpty()) {
			throw new Exception("Infome o nome do grupo!");
		}

		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setGp_nome(nomeGru);

		GrupoPO grupoPO = GrupoPO.getInstancia();
		grupoPO.setGrupoTO(grupoTO);
		grupoPO.salvar();
	}

	public void inserir() throws Exception {
		this.validar();
	}

	public void atualizar() throws Exception {
		String codGru = this.grupoView.getLbCodigoDoGrupo().getText();
		String nomeGru = this.grupoView.getTfNomeDoGrupo().getText().toUpperCase();
		if (nomeGru.isEmpty()) {
			throw new Exception("Infome o nome do grupo!");
		}

		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setCodGrupo(new Integer(codGru));
		grupoTO.setGp_nome(nomeGru);

		GrupoPO grupoPO = GrupoPO.getInstancia();
		grupoPO.setGrupoTO(grupoTO);
		grupoPO.atualizar();

	}

	public void preencherTabela() throws SQLException, Exception {
		GrupoPO grupoPO = GrupoPO.getInstancia();
		List<GrupoTO> lista = grupoPO.preencherTabela();
		DefaultTableModel model = (DefaultTableModel) this.tabelaGrupo.getModel();
		model.setNumRows(0);
		for (GrupoTO grupoTO : lista) {
			model.addRow(new Object[] { grupoTO.getCodGrupo(), grupoTO.getGp_nome() });
		}
	}

	public void buscaDescricao() throws SQLException, Exception {
		String descricao = this.grupoView.getTfPesquisa().getText();
		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setGp_nome(descricao);

		GrupoPO grupoPO = GrupoPO.getInstancia();
		grupoPO.setGrupoTO(grupoTO);

		List<GrupoTO> lista = grupoPO.buscaNome();
		DefaultTableModel model = (DefaultTableModel) this.tabelaGrupo.getModel();
		model.setNumRows(0);
		for (GrupoTO grupoTO2 : lista) {
			model.addRow(new Object[] { grupoTO2.getCodGrupo(), grupoTO2.getGp_nome() });
		}

	}

	public void buscaId() throws SQLException, Exception {
		String codGru = this.grupoView.getTfPesquisa().getText();
		if (!codGru.isEmpty() && !UtilVariados.eNumerico(codGru)) {
			throw new Exception("Para pesquisar com código somente número!");
		}
		
		if (!codGru.isEmpty() && UtilVariados.eNumerico(codGru)) {
			GrupoTO grupoTO = new GrupoTO();
			grupoTO.setCodGrupo(new Integer(codGru));

			GrupoPO grupoPO = GrupoPO.getInstancia();
			grupoPO.setGrupoTO(grupoTO);

			List<GrupoTO> lista = grupoPO.buscaId();
			DefaultTableModel model = (DefaultTableModel) this.tabelaGrupo.getModel();
			model.setNumRows(0);
			for (GrupoTO grupoTO2 : lista) {
				model.addRow(new Object[] { grupoTO2.getCodGrupo(), grupoTO2.getGp_nome() });
			}

		}
	}

	public void verificaGru() throws SQLException, Exception {
		String nomeGru = this.grupoView.getTfNomeDoGrupo().getText();
		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setGp_nome(nomeGru);

		GrupoPO grupoPO = GrupoPO.getInstancia();
		grupoPO.setGrupoTO(grupoTO);
		List<GrupoTO> lista = grupoPO.verificaNome();
		if (!lista.isEmpty()) {
			throw new Exception("Esse grupo já existe!");
		}
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
