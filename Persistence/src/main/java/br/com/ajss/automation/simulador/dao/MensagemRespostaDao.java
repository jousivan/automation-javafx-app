package br.com.ajss.automation.simulador.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import br.com.ajss.automation.dao.SimuladorImplDao;
import br.com.ajss.automation.simulador.pojo.TbMensagemResposta;

public class MensagemRespostaDao extends SimuladorImplDao<String, TbMensagemResposta> {
	private Integer nsuMensagemAnterior;
	private Integer nsuMensagemEspecifica;
	
	public MensagemRespostaDao(Class<TbMensagemResposta> classeDaEntidade) {
		super(classeDaEntidade);
	}

	public void alteraMensagemSirot(Integer mitResp, Integer codProcResp, Boolean ativa, String comentario) {
		if(ativa == true) {
			//Desativa Mensagem Anterior
			DesativaMsgAnterior(mitResp, codProcResp, ativa);
			
			//Ativa Mensagem Especifica
			alteraStatusMensagemEspecifica(mitResp, codProcResp, ativa, comentario);
			
		}else {
			//Desativa Mensagem Especifica
			desativaMensagemEspecifica();
			
			//Ativa Mensagem Anterior
			ativaMsgAnterior();
		}
	}
	
	public void DesativaMsgAnterior(Integer mitResp, Integer codProcResp, Boolean ativa) {
		getEntityManager().getTransaction().begin();
		
		StringBuilder query = new StringBuilder();
		query.append("Select nsuMensagem From TbMensagemResposta T");
		query.append(" where mitResposta =:mitResp");
		query.append(" and codProcessamentoResposta =:codProcResp");
		query.append(" and ativo =:ativa");
		Query tQuery = criaQuery(query.toString());
		tQuery.setParameter("mitResp", mitResp);
		tQuery.setParameter("codProcResp", codProcResp);
		tQuery.setParameter("ativa", ativa);
        try{
        	setNsuMensagemAnterior((Integer) tQuery.getSingleResult());
        }catch(NoResultException e){
        	setNsuMensagemAnterior(null);
        }
		
		if(getNsuMensagemAnterior() != null) {
			//getEntityManager().getTransaction().begin();
			StringBuilder query2 = new StringBuilder();
			query2.append("Update TbMensagemResposta Set ativo = :ativa");
			query2.append(" where nsuMensagem =:nsuMensagem");
			Query tQuery2 = criaQuery(query2.toString());
			tQuery2.setParameter("nsuMensagem", nsuMensagemAnterior);
			tQuery2.setParameter("ativa", (!ativa));
			tQuery2.executeUpdate();
			
		}
		getEntityManager().getTransaction().commit();
	}

	public void ativaMsgAnterior() {
		
		if (getNsuMensagemAnterior() != null) {
			getEntityManager().getTransaction().begin();
			StringBuilder query2 = new StringBuilder();
			query2.append("Update TbMensagemResposta Set ativo = :ativa");
			query2.append(" where nsuMensagem =:nsuMensagem");
			Query tQuery2 = criaQuery(query2.toString());
			tQuery2.setParameter("nsuMensagem", getNsuMensagemAnterior());
			tQuery2.setParameter("ativa", true);
			tQuery2.executeUpdate();
			getEntityManager().getTransaction().commit();
		}
	}
	
	public void alteraStatusMensagemEspecifica(Integer mitResp, Integer codProcResp, Boolean ativa, String comentario) {
		getEntityManager().getTransaction().begin();
		StringBuilder queryConsulta = new StringBuilder();
		queryConsulta.append("Select nsuMensagem From TbMensagemResposta T");
		queryConsulta.append(" where mitResposta =:mitResp");
		queryConsulta.append(" and codProcessamentoResposta =:codProcResp");
		queryConsulta.append(" and comentario =:comentario");
		Query tQuery = criaQuery(queryConsulta.toString());
		tQuery.setParameter("mitResp", mitResp);
		tQuery.setParameter("codProcResp", codProcResp);
		tQuery.setParameter("comentario", comentario);
		try {
			setNsuMensagemEspecifica((Integer) tQuery.getSingleResult());
		} catch (Exception e) {
			setNsuMensagemEspecifica(null);
		}
		
		if(getNsuMensagemEspecifica() != null) {
			StringBuilder query = new StringBuilder();
			query.append("Update TbMensagemResposta Set ativo = :ativa");
			query.append(" where nsuMensagem =:nsuMensagem");
			Query tQuery2 = criaQuery(query.toString());
			tQuery2.setParameter("nsuMensagem", getNsuMensagemEspecifica());
			tQuery2.setParameter("ativa", ativa);
			tQuery2.executeUpdate();
			
		}
		getEntityManager().getTransaction().commit();
		
	}
	
	public void desativaMensagemEspecifica() {
		getEntityManager().getTransaction().begin();
		if(getNsuMensagemEspecifica() != null) {
			StringBuilder query = new StringBuilder();
			query.append("Update TbMensagemResposta Set ativo = :ativa");
			query.append(" where nsuMensagem =:nsuMensagem");
			Query tQuery2 = criaQuery(query.toString());
			tQuery2.setParameter("nsuMensagem", getNsuMensagemEspecifica());
			tQuery2.setParameter("ativa", false);
			tQuery2.executeUpdate();
			
		}
		getEntityManager().getTransaction().commit();
	}

	public Integer getNsuMensagemAnterior() {
		return nsuMensagemAnterior;
	}

	public void setNsuMensagemAnterior(Integer nsuMensagemAnterior) {
		this.nsuMensagemAnterior = nsuMensagemAnterior;
	}

	public Integer getNsuMensagemEspecifica() {
		return nsuMensagemEspecifica;
	}

	public void setNsuMensagemEspecifica(Integer nsuMensagemEspecifica) {
		this.nsuMensagemEspecifica = nsuMensagemEspecifica;
	}
	
}
