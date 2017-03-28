package com.intersys.iamas.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intersys.fabricadeconexao.FabricaDeConexao;
import com.intersys.persistencia.PersistenciaException;

public class ProdutoPO {
	private static ProdutoPO instancia;

	public static synchronized ProdutoPO getInstancia() {
		if (instancia == null) {
			instancia = new ProdutoPO();
		}
		return instancia;
	}

	private ProdutoTO produtoTO;

	public void inserir() throws PersistenciaException {
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = "INSERT INTO CADPRO(PDCODPRO ,PDDESCRICAO, PDCODGRU , PDCODSG, PDUND, PDESTOQUE) VALUES  (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, this.produtoTO.getCodProduto());
			statement.setString(2, this.produtoTO.getDescricao());
			statement.setInt(3, (new Integer(this.produtoTO.getCodGrupo())));
			statement.setInt(4, (new Integer(this.produtoTO.getCodSrubGrupo())));
			statement.setString(5, this.produtoTO.getEspecie());
			statement.setDouble(6, (new Double(this.produtoTO.getEstoque())));

			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	public void atualizar() throws PersistenciaException {
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = "UPDATE CADPRO SET	PDDESCRICAO=?, PDCODGRU=? , PDCODSG=?, PDUND=?, PDESTOQUE=? WHERE  PDCODPRO=?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, this.produtoTO.getDescricao());
			statement.setInt(2, this.produtoTO.getCodGrupo());
			statement.setInt(3, this.produtoTO.getCodSrubGrupo());
			statement.setString(4, this.produtoTO.getEspecie());
			statement.setDouble(5, this.produtoTO.getEstoque());
			statement.setString(6, this.produtoTO.getCodProduto());
			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	public void deletar() throws PersistenciaException {
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = "DELETE FROM CADPRO WHERE PDCODPRO = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, this.produtoTO.getCodProduto());

			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}

	}

	public List<ProdutoTO> listartodos() throws PersistenciaException {
		List<ProdutoTO> listartodos = new ArrayList<ProdutoTO>();
		ProdutoTO produtoTO = null;
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = " SELECT pdcodpro, pddescricao, pdestoque, gp_nome, SG_NOME, pdcodgru, pdcodsg, pdund, SG_CODSG,gp_codgru "
					+ "FROM cadpro INNER JOIN cadgru  ON (PDCODGRU = gp_codgru)"
					+ "INNER JOIN CADSG on (PDCODSG = SG_CODSG);";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				produtoTO = this.transferenciaDeResultSet(resultSet);
				listartodos.add(produtoTO);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listartodos;
	}

	public List<ProdutoTO> listartodosCampos() throws PersistenciaException {
		ProdutoTO produtoTO = null;
		List<ProdutoTO> listartodos = new ArrayList<ProdutoTO>();
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = " SELECT pdcodpro, pddescricao, pdestoque, gp_nome, SG_NOME, pdcodgru, pdcodsg, pdund, SG_CODSG,gp_codgru "
					+ "FROM cadpro a INNER JOIN cadgru  ON (PDCODGRU = gp_codgru)"
					+ "INNER JOIN CADSG on (PDCODSG = SG_CODSG) where a.pdcodpro = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, new Integer(this.produtoTO.getCodProduto()));
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				produtoTO = transferenciaDeResultSet(resultSet);
				listartodos.add(produtoTO);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listartodos;
	}

	public List<ProdutoTO> buscarporID() throws PersistenciaException {
		ProdutoTO produtoTO = null;
		List<ProdutoTO> listaID = new ArrayList<ProdutoTO>();
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = " SELECT pdcodpro, pddescricao, pdestoque, gp_nome, SG_NOME, pdcodgru, pdcodsg, pdund, SG_CODSG,gp_codgru "
					+ "FROM cadpro INNER JOIN cadgru  ON (PDCODGRU = gp_codgru)"
					+ "INNER JOIN CADSG on (PDCODSG = SG_CODSG) where pdcodpro = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, new Integer(this.produtoTO.getCodProduto()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				produtoTO = this.transferenciaDeResultSet(resultSet);
				listaID.add(produtoTO);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaID;
	}

	public List<ProdutoTO> buscarporDescri() {
		ProdutoTO produtoTO = null;
		List<ProdutoTO> listaDecricao = new ArrayList<ProdutoTO>();
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = " SELECT pdcodpro, pddescricao, pdestoque, gp_nome, SG_NOME, pdcodgru, pdcodsg, pdund, SG_CODSG,gp_codgru "
					+ "FROM cadpro INNER JOIN cadgru  ON (PDCODGRU = gp_codgru)"
					+ "INNER JOIN CADSG on (PDCODSG = SG_CODSG) where pddescricao like ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + this.produtoTO.getDescricao() + "%");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				produtoTO = this.transferenciaDeResultSet(resultSet);
				listaDecricao.add(produtoTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDecricao;
	}

	public ProdutoTO vaerifCodPro() throws Exception {
		ProdutoTO produtoTO = null;
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = " select PDCODPRO from cadpro where PDCODPRO  = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, new Integer(this.produtoTO.getCodProduto()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				produtoTO = this.produtoTO;
				produtoTO.setCodProduto(resultSet.getString("pdcodpro"));
				return produtoTO;
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProdutoTO verifCodGrupo() throws PersistenciaException {
		ProdutoTO produtoTO = null;
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = "select gp_nome from cadgru where GP_CODGRU = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, new Integer(this.produtoTO.getCodGrupo()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				produtoTO = this.produtoTO;
				GrupoTO grupoTO = new GrupoTO();
				grupoTO.setGp_nome(resultSet.getString("gp_nome"));
				produtoTO.setGrupoTO(grupoTO);
				return produtoTO;
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new PersistenciaException(e.getMessage());
		}
		return null;
	}

	public ProdutoTO verifCodSubGru() throws PersistenciaException {
		ProdutoTO produtoTO = null;
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			String sql = "select sg_nome from cadsg where SG_CODSG = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, new Integer(this.produtoTO.getCodSrubGrupo()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SubGrupoTO subGrupoTO = new SubGrupoTO();
				produtoTO = this.produtoTO;
				subGrupoTO.setSg_nome(resultSet.getString("sg_nome"));
				produtoTO.setSubGrupoTO(subGrupoTO);
				return produtoTO;
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new PersistenciaException(e.getMessage());
		}
		return null;
	}

	private ProdutoTO transferenciaDeResultSet(ResultSet resultSet) throws Exception {
		ProdutoTO produtoTO = new ProdutoTO();

		produtoTO.setCodProduto(resultSet.getString("pdcodpro"));
		produtoTO.setDescricao(resultSet.getString("pddescricao"));
		produtoTO.setEstoque(resultSet.getFloat("pdestoque"));
		produtoTO.setEspecie(resultSet.getString("pdund"));

		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setCodGrupo(resultSet.getInt("gp_codgru"));
		grupoTO.setGp_nome(resultSet.getString("gp_nome"));
		produtoTO.setGrupoTO(grupoTO);

		SubGrupoTO subGrupoTO = new SubGrupoTO();
		subGrupoTO.setCod_subGrupo(resultSet.getInt("SG_CODSG"));
		subGrupoTO.setSg_nome(resultSet.getString("SG_NOME"));
		produtoTO.setSubGrupoTO(subGrupoTO);

		return produtoTO;
	}

	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}

	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}

}
