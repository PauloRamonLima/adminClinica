package br.com.adm.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.JPAResourceBean;

public class LeitoInternacaoDAO extends GenericDAO<LeitoInternacao> {

	public LeitoInternacaoDAO() {
		super(LeitoInternacao.class);
	}

	public List<LeitoInternacao> buscarLeitosInternacaoPorLeito(Leito leito) {

		EntityManager em = JPAResourceBean.getEntityManager();
		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.leito = :leito";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();

	}

	public Long buscarMaiorLeitosInternacaoPorLeito(Long leito) {
		EntityManager em = JPAResourceBean.getEntityManager();
		String queryJPQL = "SELECT max(l.numero) FROM LeitoInternacao l WHERE l.leito.id = :leito";
		return em.createQuery(queryJPQL, Long.class).setParameter("leito", leito).getSingleResult();
	}

	public LeitoInternacao buscarLeitoDeInternacaoPorPaciente(Paciente paciente) {

		EntityManager em = JPAResourceBean.getEntityManager();
		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.paciente = :paciente";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("paciente", paciente).getSingleResult();

	}

	public List<LeitoInternacao> buscarLeitoDeInternacaOcupados(Leito leito) {
		EntityManager em = JPAResourceBean.getEntityManager();
		String queryJPQL = "SELECT l FROM LeitoInternacao l  WHERE l.leito = :leito and l.paciente != null";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();
	}

	public List<LeitoInternacao> buscarLeitosDeInternacaoDesocupados(Leito leito) {

		EntityManager em = JPAResourceBean.getEntityManager();
		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.leito = :leito and l.paciente = null";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();

	}

}
