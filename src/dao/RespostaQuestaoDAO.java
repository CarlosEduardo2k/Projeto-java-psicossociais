package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import conexao.Conexao;

public class RespostaQuestaoDAO {

    // Busca as respostas de um funcionário específico no banco
    public Map<Integer, Integer> buscarRespostasPorFuncionarioId(int funcionarioId) {
        Map<Integer, Integer> respostas = new HashMap<>();

        // SQL baseado exatamente nos campos da sua tabela 'resposta'
        String sql = "SELECT pergunta_id, resposta FROM resposta WHERE funcionario_id = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, funcionarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Coloca no mapa: chave = pergunta_id, valor = resposta
                    int perguntaId = rs.getInt("pergunta_id");
                    int valorResposta = rs.getInt("resposta");
                    respostas.put(perguntaId, valorResposta);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar respostas individuais: " + e.getMessage());
        }

        return respostas; // Retorna o mapa
    }
}