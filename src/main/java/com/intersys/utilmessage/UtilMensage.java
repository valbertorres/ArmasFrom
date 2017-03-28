package com.intersys.utilmessage;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class UtilMensage implements Serializable {

	public static int message(String strMessage) {
		Object[] opcao = { "ok!" };
		return JOptionPane.showOptionDialog(null, strMessage, "Erro", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
	}

	public static void message(Exception exception) {
		message(exception.getMessage());
	}

}
