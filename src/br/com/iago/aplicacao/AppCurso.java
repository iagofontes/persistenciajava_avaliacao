package br.com.iago.aplicacao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.iago.entity.Aluno;
import br.com.iago.entity.Curso;
import br.com.iago.entity.Escola;
import br.com.iago.helper.AlunoHelper;
import br.com.iago.helper.CursoHelper;

public class AppCurso {

	public static void adicionarCurso() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		CursoHelper cursoHelper = new CursoHelper(em);
		
		Escola escola = new Escola();
		escola.setId(1);
		Curso curso = new Curso();
		curso.setDescricao(JOptionPane.showInputDialog("Informe o nome do curso:"));
		curso.setEscola(escola);
		
		JOptionPane.showMessageDialog(null, cursoHelper.salvar(curso));
		//System.out.println(cursoHelper.salvar(curso));
	}
	
	public static void listarCursos() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		CursoHelper cursoHelper = new CursoHelper(em);
		
		String cursos = "Cursos: \n";
		
		for(Curso curso : cursoHelper.listarCursos()) {
			cursos += curso.getDescricao()+" \n";
		}
		JOptionPane.showMessageDialog(null, cursos);
	}
}
