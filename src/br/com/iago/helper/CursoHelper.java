package br.com.iago.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.iago.entity.Aluno;
import br.com.iago.entity.Curso;

public class CursoHelper {

	private EntityManager em;
	
	public CursoHelper(EntityManager emp) {
		this.em = emp;
	}
	
	public String salvar(Curso curso) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(curso);
			this.em.getTransaction().commit();
			return "Curso adicionado com sucesso.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Problemas ao gravar curso.";
		}
	}
	
	public List<Curso> listarCursos() {
		TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c WHERE c.escola.id = :idEscola", Curso.class);
		query.setParameter("idEscola", 1);
		return query.getResultList();
	}
	
}
