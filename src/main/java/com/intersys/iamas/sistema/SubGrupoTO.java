package com.intersys.iamas.sistema;

public class SubGrupoTO {

	private int cod_subGrupo;
	private String sg_nome;

	public SubGrupoTO() {
		this.iniciaVariavel();
	}

	public void iniciaVariavel() {
		this.cod_subGrupo = 0;
		this.sg_nome = null;
	}

	public String getSg_nome() {
		return sg_nome;
	}

	public void setSg_nome(String sg_nome) {
		this.sg_nome = sg_nome;
	}

	public int getCod_subGrupo() {
		return cod_subGrupo;
	}

	public void setCod_subGrupo(int cod_subGrupo) {
		this.cod_subGrupo = cod_subGrupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_subGrupo;
		result = prime * result + ((sg_nome == null) ? 0 : sg_nome.hashCode());
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
		SubGrupoTO other = (SubGrupoTO) obj;
		if (cod_subGrupo != other.cod_subGrupo)
			return false;
		if (sg_nome == null) {
			if (other.sg_nome != null)
				return false;
		} else if (!sg_nome.equals(other.sg_nome))
			return false;
		return true;
	}

}
