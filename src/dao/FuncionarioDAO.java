package dao;

import conexao.Conexao;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    public int salvar(Funcionario funcionario) {

        String sql =
                "INSERT INTO funcionario(nome, cpf) VALUES (?, ?) RETURNING id";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int idGerado = rs.getInt("id");

                return idGerado;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return -1;
    }
}