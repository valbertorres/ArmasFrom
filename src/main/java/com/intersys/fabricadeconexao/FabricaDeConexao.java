package com.intersys.fabricadeconexao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao implements Serializable {

	private static String url = "jdbc:mysql://localhost:3306/sge_armas";
	private static String drive = "com.mysql.jdbc.Driver";
	private static String senha = "dezenove";
	private static String usuario = "root";

	private static FabricaDeConexao instancia;

	public static FabricaDeConexao getInstancia() {
		if (instancia == null) {
			instancia = new FabricaDeConexao();
			return instancia;
		}
		return instancia;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(drive);
		Connection connection = (Connection) DriverManager.getConnection(url, usuario, senha);
		return connection;
	}
	public static void main(String[] args) {
		try {
			Connection connection = FabricaDeConexao.getInstancia().getConnection();
			System.out.println("conectou");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
