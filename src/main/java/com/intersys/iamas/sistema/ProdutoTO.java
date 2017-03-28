package com.intersys.iamas.sistema;

public class ProdutoTO {

	private String codProduto;
	private String Descricao;
	private int codGrupo;
	private int codSrubGrupo;
	private String Especie;
	private float estoque;
	private GrupoTO grupoTO;
	private SubGrupoTO subGrupoTO;

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public int getCodSrubGrupo() {
		return codSrubGrupo;
	}

	public void setCodSrubGrupo(int codSrubGrupo) {
		this.codSrubGrupo = codSrubGrupo;
	}

	public String getEspecie() {
		return Especie;
	}

	public void setEspecie(String especie) {
		Especie = especie;
	}

	public double getEstoque() {
		return estoque;
	}

	public void setEstoque(float estoque) {
		this.estoque = estoque;
	}

	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}

	public SubGrupoTO getSubGrupoTO() {
		return subGrupoTO;
	}

	public void setSubGrupoTO(SubGrupoTO subGrupoTO) {
		this.subGrupoTO = subGrupoTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descricao == null) ? 0 : Descricao.hashCode());
		result = prime * result + ((Especie == null) ? 0 : Especie.hashCode());
		result = prime * result + codGrupo;
		result = prime * result + ((codProduto == null) ? 0 : codProduto.hashCode());
		result = prime * result + codSrubGrupo;
		result = prime * result + Float.floatToIntBits(estoque);
		result = prime * result + ((grupoTO == null) ? 0 : grupoTO.hashCode());
		result = prime * result + ((subGrupoTO == null) ? 0 : subGrupoTO.hashCode());
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
		ProdutoTO other = (ProdutoTO) obj;
		if (Descricao == null) {
			if (other.Descricao != null)
				return false;
		} else if (!Descricao.equals(other.Descricao))
			return false;
		if (Especie == null) {
			if (other.Especie != null)
				return false;
		} else if (!Especie.equals(other.Especie))
			return false;
		if (codGrupo != other.codGrupo)
			return false;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		if (codSrubGrupo != other.codSrubGrupo)
			return false;
		if (Float.floatToIntBits(estoque) != Float.floatToIntBits(other.estoque))
			return false;
		if (grupoTO == null) {
			if (other.grupoTO != null)
				return false;
		} else if (!grupoTO.equals(other.grupoTO))
			return false;
		if (subGrupoTO == null) {
			if (other.subGrupoTO != null)
				return false;
		} else if (!subGrupoTO.equals(other.subGrupoTO))
			return false;
		return true;
	}

}
