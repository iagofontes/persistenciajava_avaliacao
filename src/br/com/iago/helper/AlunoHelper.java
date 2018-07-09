package br.com.iago.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.iago.entity.Aluno;

public class AlunoHelper {
	
	private EntityManager em;
	
	public AlunoHelper(EntityManager emp) {
		this.em = emp;
	}
	
	public String salvar(Aluno aluno) {
		try {
			em.getTransaction().begin();
			em.persist(aluno);
			em.getTransaction().commit();
			return "Aluno salvo com sucesso.";	
		} catch (Exception e) {
			e.printStackTrace();
			return "Problemas ao gravar aluno.";
		}
	}
	
	public List<Aluno> listarAlunos() {
		TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
		return query.getResultList();
	}
}
