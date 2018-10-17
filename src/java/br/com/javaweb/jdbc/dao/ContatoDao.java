package br.com.javaweb.jdbc.dao;

import br.com.javaweb.jdbc.ConnectionFactory;
import br.com.javaweb.jdbc.modelo.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDao {

    private final Connection connection;

    public ContatoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Contato contato) {
        String sql = "insert into contatos "
                + "(nome,email,endereco,dataNascimento) "
                + "values (?,?,?,?)";
        try {
            //	prepared statement para	inserção
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            //	seta os valores
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date( contato.getDataNascimento().getTimeInMillis()) );
            //	executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
