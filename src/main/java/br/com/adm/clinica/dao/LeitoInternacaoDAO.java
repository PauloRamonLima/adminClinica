package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;

public class LeitoInternacaoDAO extends GenericDAO<LeitoInternacao> implements Serializable{

	private static final long serialVersionUID = 993161257726214854L;
	
	@Inject
	private EntityManager em;
	
	public LeitoInternacaoDAO() {
		super(LeitoInternacao.class);
	}

	public List<LeitoInternacao> buscarLeitosInternacaoPorLeito(Leito leito) {

		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.leito = :leito";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();

	}

	public Long buscarMaiorLeitosInternacaoPorLeito(Long leito) {
		String queryJPQL = "SELECT max(l.numero) FROM LeitoInternacao l WHERE l.leito.id = :leito";
		return em.createQuery(queryJPQL, Long.class).setParameter("leito", leito).getSingleResult();
	}

	public LeitoInternacao buscarLeitoDeInternacaoPorPaciente(Paciente paciente) {

		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.paciente = :paciente";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("paciente", paciente).getSingleResult();

	}

	public List<LeitoInternacao> buscarLeitoDeInternacaOcupados(Leito leito) {
		String queryJPQL = "SELECT l FROM LeitoInternacao l  WHERE l.leito = :leito and l.paciente != null";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();
	}
	
	public LeitoInternacao buscarLeitoDeInternacaPorLeitoENumeracao(String leito, Long numero) {
		String queryJPQL = "SELECT l FROM LeitoInternacao l  WHERE l.leito.descricao = :leito and l.numero = :numero";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).setParameter("numero", numero).getSingleResult();
	}

	public List<LeitoInternacao> buscarLeitosDeInternacaoDesocupados(Leito leito) {

		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.leito = :leito and l.paciente is null";
		return em.createQuery(queryJPQL, LeitoInternacao.class).setParameter("leito", leito).getResultList();

	}
	
	public List<LeitoInternacao> buscarPacientesInternados() {

		String queryJPQL = "SELECT l FROM LeitoInternacao l WHERE l.paciente != null";
		return em.createQuery(queryJPQL, LeitoInternacao.class).getResultList();

	}

}
