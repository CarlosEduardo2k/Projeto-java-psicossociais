package dao;

import conexao.Conexao;
import model.RelatorioGeral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioGeralDAO {

    public RelatorioGeral obterDadosRelatorioGeral() {
        RelatorioGeral relatorio = new RelatorioGeral();

        // Query que calcula a média de todas as linhas da tabela de uma só vez
        String sql = "SELECT COUNT(DISTINCT funcionario_id) as total, " +
                "AVG(ot) as ot, AVG(ct) as ct, AVG(rt) as rt, " +
                "AVG(rp) as rp, AVG(la) as la, AVG(de) as de, AVG(dp) as dp " +
                "FROM resultado_categoria";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                relatorio.setTotalRespondentes(rs.getInt("total"));
                relatorio.setMediaOt(rs.getDouble("ot"));
                relatorio.setMediaCt(rs.getDouble("ct"));
                relatorio.setMediaRt(rs.getDouble("rt"));
                relatorio.setMediaRp(rs.getDouble("rp"));
                relatorio.setMediaLa(rs.getDouble("la"));
                relatorio.setMediaDe(rs.getDouble("de"));
                relatorio.setMediaDp(rs.getDouble("dp"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular relatório geral: " + e.getMessage());
        }

        return relatorio;
    }
}