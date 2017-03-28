package com.intersys.iamas.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intersys.fabricadeconexao.FabricaDeConexao;

public class SubGruPO {

	private static SubGruPO instancia;

	public static synchronized SubGruPO getInstancia() {
		if (instancia == null) {
			instancia = new SubGruPO();
		}
		return instancia;
	}

	private SubGrupoTO subGrupoTO;

	public void inserir() throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into cadsg");
		sql.append("(sg_codemp, sg_nome)");
		sql.append("values");
		sql.append("(?,?)");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setInt(1, 0);
				statement.setString(2, this.subGrupoTO.getSg_nome());
				statement.execute();
			}
		}
	}

	public void atualizar() throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("update cadsg set ");
		sql.append("sg_nome=?");
		sql.append("where sg_codsg = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, this.subGrupoTO.getSg_nome());
				statement.setInt(2, this.subGrupoTO.getCod_subGrupo());
				statement.execute();
			}
		}
	}

	public List<SubGrupoTO> buscaId() throws ClassNotFoundException, SQLException {
		SubGrupoTO subGrupoTO = null;
		List<SubGrupoTO> listaId = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select *from cadsg where sg_codsg =?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setInt(1, this.subGrupoTO.getCod_subGrupo());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						subGrupoTO = this.transferenciaresult(resultSet);
						listaId.add(subGrupoTO);
					}
				}
			}
		}
		return listaId;
	}

	public List<SubGrupoTO> buscaNome() throws ClassNotFoundException, SQLException {
		SubGrupoTO subGrupoTO = null;
		List<SubGrupoTO> listaNome = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadsg where sg_nome like ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, "%" + this.subGrupoTO.getSg_nome() + "%");
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						subGrupoTO = this.transferenciaresult(resultSet);
						listaNome.add(subGrupoTO);
					}
				}
			}
		}
		return listaNome;
	}

	public SubGrupoTO verificaSubgru() throws SQLException, ClassNotFoundException{
		SubGrupoTO subGrupoTO = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadsg where sg_nome  = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setString(1, this.subGrupoTO.getSg_nome());
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						subGrupoTO = this.transferenciaresult(resultSet);
						subGrupoTO.setSg_nome(null);
						return subGrupoTO;
					}
				}
			}
			return null;
		}

	}

	public List<SubGrupoTO> preecherTabela() throws ClassNotFoundException, SQLException {
		SubGrupoTO subGrupoTO = null;
		List<SubGrupoTO> listaTabela = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cadsg ");

		try (Connection connection = FabricaDeConexao.getInstancia().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						subGrupoTO = this.transferenciaresult(resultSet);
						listaTabela.add(subGrupoTO);
					}
				}
			}
		}
		return listaTabela;
	}

	private SubGrupoTO transferenciaresult(ResultSet resultSet) throws SQLException {
		SubGrupoTO subGrupoTO = new SubGrupoTO();
		subGrupoTO.setCod_subGrupo(resultSet.getInt("sg_codsg"));
		subGrupoTO.setSg_nome(resultSet.getString("sg_nome"));
		return subGrupoTO;
	}

	public SubGrupoTO getSubGrupoTO() {
		return subGrupoTO;
	}

	public void setSubGrupoTO(SubGrupoTO subGrupoTO) {
		this.subGrupoTO = subGrupoTO;
	}

}
