
package br.com.ajss.automation.simulador.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_parametros")
public class TbParamentro implements java.io.Serializable, Entidade {

	/** The Serial Version UID */
	private static final long serialVersionUID = -5285865211056632209L;

	@Id
	@Column(name = "nsu_parametro", unique = true, nullable = false)
	private Integer noParametro;
	@Column(name = "desc_parametro", nullable = false)
	private String descricaoParametro;
	@Column(name = "valor_parametro", nullable = false)
	private String valorParametro;

	public Integer getNoParametro() {
		return noParametro;
	}

	public void setNoParametro(Integer noParametro) {
		this.noParametro = noParametro;
	}

	public String getDescricaoParametro() {
		return descricaoParametro;
	}

	public void setDescricaoParametro(String descricaoParametro) {
		this.descricaoParametro = descricaoParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

}
