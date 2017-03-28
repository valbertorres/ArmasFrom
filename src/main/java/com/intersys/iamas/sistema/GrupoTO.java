package com.intersys.iamas.sistema;

public class GrupoTO {
	private int codGrupo;
	private String gp_nome;

	public GrupoTO() {
		this.iniciaVariavel();
	}

	public void iniciaVariavel() {
		codGrupo = 0;
		gp_nome = "";
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getGp_nome() {
		return gp_nome;
	}

	public void setGp_nome(String gp_nome) {
		this.gp_nome = gp_nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codGrupo;
		result = prime * result + ((gp_nome == null) ? 0 : gp_nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoTO other = (GrupoTO) obj;
		if (codGrupo != other.codGrupo)
			return false;
		if (gp_nome == null) {
			if (other.gp_nome != null)
				return false;
		} else if (!gp_nome.equals(other.gp_nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoTO [codGrupo=" + codGrupo + ", gp_nome=" + gp_nome + "]";
	}

	
}
