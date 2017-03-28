package com.intersys.iamas.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.intersys.fabricadeconexao.FabricaDeConexao;

public class GrupoPO {

	private static GrupoPO instancia;

	public static synchronized GrupoPO getInstancia() {
		if (instancia == null) {
			instancia = new GrupoPO();
		}
		return instancia;
	}

	private GrupoTO grupoTO;

	public void salvar() throws Exception, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into cadgru ");
		sql.append("( gp_nome)");
		sql.append("values");
		sql.append(" (?)");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection();) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, this.grupoTO.getGp_nome());
				statement.execute();
			}
		}
	}

	public void atualizar() throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update cadgru");
		sql.append(" set gp_nome = ? ");
		sql.append(" where");
		sql.append(" gp_codgru = ?");
		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, this.grupoTO.getGp_nome());
				statement.setInt(2, this.grupoTO.getCodGrupo());
				statement.execute();
			}

		}
	}

	public List<GrupoTO> preencherTabela() throws Exception, SQLException {
		List<GrupoTO> listaGrupo = new ArrayList<>();
		GrupoTO grupoTO = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadgru");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					grupoTO = this.transferencia(resultSet);
					listaGrupo.add(grupoTO);
				}
			}
		}
		return listaGrupo;
	}

	public List<GrupoTO> buscaId() throws Exception, SQLException {
		GrupoTO grupoTO = null;
		List<GrupoTO> listaId = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadgru where gp_codgru = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setInt(1, this.grupoTO.getCodGrupo());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						grupoTO = this.grupoTO;
						grupoTO = this.transferencia(resultSet);
						listaId.add(grupoTO);
					}
				}
			}
		}
		return listaId;
	}

	public List<GrupoTO> buscaNome() throws Exception, SQLException {
		GrupoTO grupoTO = null;
		List<GrupoTO> listaNome = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadgru where gp_nome like ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, "%" + this.grupoTO.getGp_nome() + "%");
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						grupoTO = this.grupoTO;
						grupoTO = this.transferencia(resultSet);
						listaNome.add(grupoTO);
					}
				}
			}
		}
		return listaNome;
	}

	public List<GrupoTO> verificaNome() throws Exception, SQLException {
		GrupoTO grupoTO = null;
		List<GrupoTO> listaNome = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadgru where gp_nome = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, this.grupoTO.getGp_nome());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						grupoTO = this.grupoTO;
						grupoTO = this.transferencia(resultSet);
						listaNome.add(grupoTO);
					}
				}
			}
		}
		return listaNome;
	}

	private GrupoTO transferencia(ResultSet resultSet) throws SQLException {
		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setCodGrupo(resultSet.getInt("gp_codgru"));
		grupoTO.setGp_nome(resultSet.getString("gp_nome"));
		return grupoTO;

	}

	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}
	
	
	

}
