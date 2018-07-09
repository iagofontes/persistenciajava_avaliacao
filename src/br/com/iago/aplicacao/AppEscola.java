package br.com.iago.aplicacao;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.iago.dao.JdbcEscolaDao;
import br.com.iago.entity.Escola;

public class AppEscola {

	public static void atualizarEscola() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("beanJdbc.xml");
			JdbcEscolaDao dao = (JdbcEscolaDao) context.getBean("jdbcEscolaDao");
			Escola escola = new Escola();
			escola.setNome(JOptionPane.showInputDialog("Informe o novo nome da instituição: "));
			SimpleDateFormat dfmt = new SimpleDateFormat("dd/mm/yyyy");
			escola.setFundacao(dfmt.parse(JOptionPane.showInputDialog("Data de fundação: (Ex: 01/01/2000)")));
			escola.setEndereco(JOptionPane.showInputDialog("Endereço da instituição: "));
			if(dao.atualizarEscola(escola))
				JOptionPane.showMessageDialog(null, "Escola atualizada com sucesso");
			else
				JOptionPane.showMessageDialog(null, "Problemas ao atualizar escola.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
