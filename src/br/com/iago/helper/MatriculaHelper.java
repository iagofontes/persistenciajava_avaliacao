package br.com.iago.helper;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.iago.entity.Aluno;
import br.com.iago.entity.Curso;
import br.com.iago.entity.Matricula;
public class MatriculaHelper {
	private EntityManager em;	
	public MatriculaHelper(EntityManager emp) {
		this.em = emp;
	}
	public String salvar(Matricula matricula) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(matricula);
			this.em.getTransaction().commit();
			return "Matrícula realizada com sucesso.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Problemas durante a matrícula do aluno.";
		}
	}
	public List<Matricula> listarMatriculas() {
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m", Matricula.class);
		return query.getResultList();
	}
	public List<Matricula> listarCursosAluno(Aluno aluno) {
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m Where m.aluno.id = :idAluno", Matricula.class);
		query.setParameter("idAluno", aluno.getId());
		return query.getResultList();
	}
	public String atribuirNota(Matricula matricula) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(matricula);
			this.em.getTransaction().commit();
			return "Nota atribuída com sucesso.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Problemas durante atribuição de nota.";
		}
	}
}
