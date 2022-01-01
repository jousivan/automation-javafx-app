
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
@Table(name = "tb_mensagem_resposta")
public class TbMensagemResposta implements java.io.Serializable, Entidade {

	/** The Serial Version UID */
	private static final long serialVersionUID = -5285865211056632209L;

	@Id
	@Column(name = "nsu", unique = true, nullable = false)
	private Integer nsuMensagem;
	@Column(name = "mit_resposta", nullable = false)
	private Integer mitResposta;
	@Column(name = "cod_processamento_resposta", nullable = false)
	private Integer codProcessamentoResposta;
	@Column(name = "mensagem", nullable = false)
	private String mensagem;
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
	@Column(name = "bit39", nullable = false)
	private String bit39;
	@Column(name = "mit_envio", nullable = false)
	private String mitEnvio;
	@Column(name = "cod_processamento_envio", nullable = false)
	private String codProcessamentoEnvio;
	@Column(name = "sequencia_rajada", nullable = false)
	private String sequenciaRajada;
	@Column(name = "nsu_pai", nullable = false)
	private String nsuPai;
	@Column(name = "comentario", nullable = false)
	private String comentario;
	
	public Integer getNsuMensagem() {
		return nsuMensagem;
	}
	public void setNsuMensagem(Integer nsuMensagem) {
		this.nsuMensagem = nsuMensagem;
	}
	public Integer getMitResposta() {
		return mitResposta;
	}
	public void setMitResposta(Integer mitResposta) {
		this.mitResposta = mitResposta;
	}
	public Integer getCodProcessamentoResposta() {
		return codProcessamentoResposta;
	}
	public void setCodProcessamentoResposta(Integer codProcessamentoResposta) {
		this.codProcessamentoResposta = codProcessamentoResposta;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getBit39() {
		return bit39;
	}
	public void setBit39(String bit39) {
		this.bit39 = bit39;
	}
	public String getMitEnvio() {
		return mitEnvio;
	}
	public void setMitEnvio(String mitEnvio) {
		this.mitEnvio = mitEnvio;
	}
	public String getCodProcessamentoEnvio() {
		return codProcessamentoEnvio;
	}
	public void setCodProcessamentoEnvio(String codProcessamentoEnvio) {
		this.codProcessamentoEnvio = codProcessamentoEnvio;
	}
	public String getSequenciaRajada() {
		return sequenciaRajada;
	}
	public void setSequenciaRajada(String sequenciaRajada) {
		this.sequenciaRajada = sequenciaRajada;
	}
	public String getNsuPai() {
		return nsuPai;
	}
	public void setNsuPai(String nsuPai) {
		this.nsuPai = nsuPai;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
