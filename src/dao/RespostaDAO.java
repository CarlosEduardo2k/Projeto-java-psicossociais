package dao;

import conexao.Conexao;
import model.Resposta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RespostaDAO {

    public void salvar(Resposta resposta){

        String sql = "INSERT INTO resposta(" + "funcionario_id," + "pergunta_id," + "resposta" + ") VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, resposta.getFuncionarioId());

            ps.setInt(2, resposta.getPerguntaId());

            ps.setInt(3, resposta.getResposta());

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
