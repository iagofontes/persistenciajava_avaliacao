package br.com.iago.aplicacao;

import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) {
		selectOption();
	}
	
	private static void selectOption() {
		String message = ""
				+ "1 - Listar alunos \n"
				+ "2 - Listar cursos \n"
				+ "3 - Listar cursos por aluno \n"
				+ "4 - Listar matrículas \n"
				+ "5 - Adicionar aluno \n"
				+ "6 - Adicionar curso \n"
				+ "7 - Atribuit nota \n"
				+ "8 - Matricular aluno \n"
				+ "9 - Atualizar Instituição";
		
		while(true) {
			String opt = JOptionPane.showInputDialog(null, message, "Escola", JOptionPane.QUESTION_MESSAGE);
			//System.out.println(opt);
			if(opt == null)
				System.exit(0);
			callFunction(Integer.parseInt(opt));
				
		}
	}
	
	private static void callFunction(Integer opcao) {
		switch(opcao) {
			case 1:
				AppAluno.listarAlunos();
				break;
			case 2:
				AppCurso.listarCursos();
				break;
			case 3: 
				AppMatricula.apresentarCursosPorAluno();
				break;
			case 4:
				AppMatricula.listarMatriculas();
				break;
			case 5:
				AppAluno.incluirAluno();
				break;
			case 6: 
				AppCurso.adicionarCurso();
				break;
			case 7: 
				AppMatricula.atribuirNota();
				break;
			case 8:
				AppMatricula.matricularAluno();
				break;
			case 9:
				AppEscola.atualizarEscola();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção não disponível.");
				break;
		}
	}
}
