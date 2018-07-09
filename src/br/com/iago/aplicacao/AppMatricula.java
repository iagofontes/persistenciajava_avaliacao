package br.com.iago.aplicacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.iago.entity.Aluno;
import br.com.iago.entity.Curso;
import br.com.iago.entity.Escola;
//import br.com.iago.entity.Curso;
import br.com.iago.entity.Matricula;
import br.com.iago.helper.AlunoHelper;
import br.com.iago.helper.CursoHelper;
//import br.com.iago.helper.CursoHelper;
import br.com.iago.helper.MatriculaHelper;

public class AppMatricula {

	public static void matricularAluno() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		AlunoHelper alunoHelper = new AlunoHelper(em);
		CursoHelper cursoHelper = new CursoHelper(em);
		MatriculaHelper matriculaHelper = new MatriculaHelper(em);

		List<Curso> cursos = cursoHelper.listarCursos();
		List<Aluno> alunos = alunoHelper.listarAlunos();
		
		Curso curso = (Curso) JOptionPane.showInputDialog(null, "Escolha um curso", "Cursos", JOptionPane.OK_CANCEL_OPTION, null, cursos.toArray(), "");
		Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Alunos", JOptionPane.OK_CANCEL_OPTION, null, alunos.toArray(), "");
		
		Matricula matricula = new Matricula();
		matricula.setAluno(aluno);
		matricula.setCurso(curso);
		matricula.setNota(0.0);
		
		JOptionPane.showMessageDialog(null, matriculaHelper.salvar(matricula));
	}

	public static void listarMatriculas() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		MatriculaHelper matriculaHelper = new MatriculaHelper(em);
		
		String msg = "Matrículas: \n";
		
		for(Matricula matricula : matriculaHelper.listarMatriculas()) {
			msg += "Código Matrícula: "+matricula.getId()+" \n"
					+ "Nome aluno: "+matricula.getAluno().getNome()+" \n"
					+ "Nome curso: "+matricula.getCurso().getDescricao()+"\n"
					+ "=======================\n";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	
	private static List<Matricula> listarCursosPorAluno() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		MatriculaHelper matriculaHelper = new MatriculaHelper(em);
		
		AlunoHelper alunoHelper = new AlunoHelper(em);
		List<Aluno> alunos = alunoHelper.listarAlunos();
		Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno", "Alunos", JOptionPane.OK_CANCEL_OPTION, null, alunos.toArray(), "");
		return matriculaHelper.listarCursosAluno(aluno);
	}
	
	public static void apresentarCursosPorAluno() {
		String cursos = "Cursos: \n";
		for(Matricula matricula: AppMatricula.listarCursosPorAluno()) {
			cursos += matricula.getCurso().getDescricao()+" \n";
		}
		JOptionPane.showMessageDialog(null, cursos);
	}
	
	public static void atribuirNota() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();

		Matricula matricula = (Matricula) JOptionPane.showInputDialog(null, "Selecione a matrícula do aluno", "Matrículas", JOptionPane.OK_CANCEL_OPTION, null, AppMatricula.listarCursosPorAluno().toArray(), "");
		
		//double nota = String.parseDouble();
		if(!checarNotaMatricula(matricula)) {
			String msg = "Informe a nota do aluno: Ex(10.0)";
			while(!checarNotaMatricula(matricula)) {
				matricula.setNota(Double.parseDouble(JOptionPane.showInputDialog(msg)));
				msg = "Nota inválida, insira uma nota maior que 0.0";
			}
			MatriculaHelper matriculaHelper = new MatriculaHelper(em);
			JOptionPane.showMessageDialog(null, matriculaHelper.atribuirNota(matricula));
		} else {
			JOptionPane.showMessageDialog(null, "Nota atribuida anteriormente.");
		}
	}
	
	public static Boolean checarNotaMatricula(Matricula matricula) {
		if(matricula.getNota() > 0.0)
			return true;
		return false;
	}
	
}
