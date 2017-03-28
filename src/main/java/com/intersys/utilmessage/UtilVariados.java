package com.intersys.utilmessage;

public class UtilVariados {

	public static boolean eNumerico(String strnumero) {
		try {
			Integer.parseInt(strnumero);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
