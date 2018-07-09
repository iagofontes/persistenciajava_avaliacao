package br.com.iago.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.iago.entity.Escola;

public class EscolaMapper implements RowMapper<Escola> {
	@Override
	public Escola mapRow(ResultSet rs, int rowNum) throws SQLException {
		Escola escola = new Escola();
		escola.setId(rs.getInt("ID"));
		escola.setNome(rs.getString("NOME"));
		escola.setFundacao(rs.getDate("FUNDACAO"));
		escola.setEndereco(rs.getString("ENDERECO"));
		return escola;
	}
}
