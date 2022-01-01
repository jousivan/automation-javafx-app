
package br.com.ajss.automation.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import la.foton.componente.dao.Dao;

public abstract class AbstractDao<CHAVE_PRIMARIA extends Serializable, ENTIDADE>
		implements Dao<CHAVE_PRIMARIA, ENTIDADE> {

	private static final String SQL_DELETE_FROM = "DELETE FROM ";

	public abstract Class<ENTIDADE> getClasseDaEntidade();

	public void salva(ENTIDADE entidade) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entidade);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
	}

	public ENTIDADE atualiza(ENTIDADE entidade) {
		getEntityManager().getTransaction().begin();
		ENTIDADE newEntidade = getEntityManager().merge(entidade);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		return newEntidade;
	}

	public void remove(ENTIDADE entidade) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(entidade);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
	}

	public void removePorId(CHAVE_PRIMARIA chavePrimaria) {
		getEntityManager().getTransaction().begin();
		ENTIDADE entidade = getEntityManager().find(getClasseDaEntidade(), chavePrimaria);
		getEntityManager().remove(entidade);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
	}

	public int removeTodos() {
		getEntityManager().getTransaction().begin();
		int ret = getEntityManager().createQuery(SQL_DELETE_FROM + getClasseDaEntidade().getName() + " e")
				.executeUpdate();
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		return ret;
	}

	public int removeTodosNativo() {
		getEntityManager().getTransaction().begin();
		Table tabela = getClasseDaEntidade().getAnnotation(Table.class);
		int ret = getEntityManager().createNativeQuery(SQL_DELETE_FROM + tabela.name()).executeUpdate();
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		return ret;
	}

	public List<ENTIDADE> buscaTodos() {
		Query query = getEntityManager().createQuery("SELECT e FROM " + getClasseDaEntidade().getName() + " as e");
		@SuppressWarnings("unchecked")
		List<ENTIDADE> lista = Collections.checkedList(query.getResultList(), getClasseDaEntidade().getClass());
		return lista == null ? new ArrayList<ENTIDADE>(0) : lista;
	}

	public ENTIDADE buscaPorId(CHAVE_PRIMARIA chavePrimaria) {
		return getEntityManager().find(getClasseDaEntidade(), chavePrimaria);
	}

	public ENTIDADE buscaPorId(CHAVE_PRIMARIA chavePrimaria, boolean lock) {
		if (lock) {
			return getEntityManager().find(getClasseDaEntidade(), chavePrimaria, LockModeType.PESSIMISTIC_WRITE);
		}
		return getEntityManager().find(getClasseDaEntidade(), chavePrimaria);
	}

	public void refresh(ENTIDADE entidade) {
		getEntityManager().getTransaction().begin();
		getEntityManager().refresh(entidade);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
	}

	public void flush() {
		getEntityManager().flush();
	}

	public Query criaQuery(String query) {
		return getEntityManager().createQuery(query);
	}

	public Query criaQueryNativa(String query) {
		return getEntityManager().createNativeQuery(query);
	}

	public TypedQuery<ENTIDADE> criaTypedQuery(String query) {
		return getEntityManager().createQuery(query, getClasseDaEntidade());
	}

}
