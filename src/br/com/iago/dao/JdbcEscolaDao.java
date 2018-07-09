package br.com.iago.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.iago.entity.Escola;
import br.com.iago.mapper.EscolaMapper;

public class JdbcEscolaDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Escola> buscarEscola() throws Exception {
		List<Escola> escolas = new ArrayList<>();
		try {
			escolas = this.jdbcTemplate.query("SELECT * FROM ESCOLA LIMIT 1;", new EscolaMapper());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return escolas;
	}
	
	public Boolean atualizarEscola(Escola escola) {
		try {
			this.jdbcTemplate.update(
					"UPDATE ESCOLA SET NOME = ?, FUNDACAO = ?, ENDERECO = ? WHERE ID = ?;", 
					escola.getNome(),
					escola.getFundacao(),
					escola.getEndereco(),
					1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}