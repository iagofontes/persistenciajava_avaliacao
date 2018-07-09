package br.com.iago.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.iago.entity.Aluno;
import br.com.iago.helper.AlunoHelper;

public class AppAluno {
	
	public static void incluirAluno() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		AlunoHelper alunoHelper = new AlunoHelper(em);
		
		Aluno aluno = new Aluno();
		aluno.setNome(JOptionPane.showInputDialog("Informe o nome do aluno:"));
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			aluno.setNascimento(format.parse(JOptionPane.showInputDialog("Data de nascimento(dd/mm/yyyy):")));	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Impossível converter data, tente novamente.");
		}
		JOptionPane.showMessageDialog(null, alunoHelper.salvar(aluno));
	}
	
	public static void listarAlunos() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		
		AlunoHelper alunoHelper = new AlunoHelper(em);
		
		String msg = "Alunos: \n";
		for(Aluno aluno : alunoHelper.listarAlunos()) {
			msg += aluno.getNome()+" \n";
		}
		JOptionPane.showMessageDialog(null, msg);
	}

}
