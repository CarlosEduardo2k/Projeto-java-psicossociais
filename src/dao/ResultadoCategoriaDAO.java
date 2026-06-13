package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import model.ResultadoCategoria;
import model.NivelRisco;

public class ResultadoCategoriaDAO {

    // Salva o resultado das categorias no banco
    public void salvar(ResultadoCategoria resultadoCategoria){

        // Comando SQL de inserção
        String sql =
                "INSERT INTO resultado_categoria(" +
                        "funcionario_id," +
                        "ot,ct,rt,rp,la,de,dp," +
                        "ot_risco,ct_risco,rt_risco,rp_risco,la_risco,de_risco,dp_risco" +
                        ")" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (
                // Abre conexão com o banco
                Connection conn = Conexao.conectar();

                // Prepara o comando SQL
                PreparedStatement ps = conn.prepareStatement(sql);

        ){

            // Substitui os ? pelos valores do objeto
            ps.setInt(1, resultadoCategoria.getFuncionarioId());
            ps.setInt(2, resultadoCategoria.getOt());
            ps.setInt(3, resultadoCategoria.getCt());
            ps.setInt(4, resultadoCategoria.getRt());
            ps.setInt(5, resultadoCategoria.getRp());
            ps.setInt(6, resultadoCategoria.getLa());
            ps.setInt(7, resultadoCategoria.getDe());
            ps.setInt(8, resultadoCategoria.getDp());

            ps.setString(9, resultadoCategoria.getOtRisco().name());
            ps.setString(10, resultadoCategoria.getCtRisco().name());
            ps.setString(11, resultadoCategoria.getRtRisco().name());
            ps.setString(12, resultadoCategoria.getRpRisco().name());
            ps.setString(13, resultadoCategoria.getLaRisco().name());
            ps.setString(14, resultadoCategoria.getDeRisco().name());
            ps.setString(15, resultadoCategoria.getDpRisco().name());

            // Executa o INSERT
            ps.executeUpdate();

            System.out.println("Categoria salva com sucesso!");

        } catch (SQLException e) {

            // Exibe erro caso ocorra algum problema
            throw new RuntimeException(e);
        }
    }

    public ResultadoCategoria buscarPorFuncionarioId(int funcionarioId) {
        // Ajuste o nome da tabela e das colunas se no seu banco estiver diferente
        String sql = "SELECT * FROM resultado_categoria WHERE funcionario_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Instancia o objeto usando as notas gravadas no banco
                    // Certifique-se de usar os nomes exatos das suas colunas do banco aqui (ex: "soma_ot" ou "ot")
                    ResultadoCategoria resultado = new ResultadoCategoria();

                    resultado.setFuncionarioId(
                            rs.getInt("funcionario_id")
                    );

                    resultado.setOt(
                            rs.getInt("ot")
                    );

                    resultado.setCt(
                            rs.getInt("ct")
                    );

                    resultado.setRt(
                            rs.getInt("rt")
                    );

                    resultado.setRp(
                            rs.getInt("rp")
                    );

                    resultado.setLa(
                            rs.getInt("la")
                    );

                    resultado.setDe(
                            rs.getInt("de")
                    );

                    resultado.setDp(
                            rs.getInt("dp")
                    );

                    resultado.setOtRisco(
                            NivelRisco.valueOf(
                                    rs.getString("ot_risco")
                            )
                    );

                    resultado.setCtRisco(
                            NivelRisco.valueOf(
                                    rs.getString("ct_risco")
                            )
                    );

                    resultado.setRtRisco(
                            NivelRisco.valueOf(
                                    rs.getString("rt_risco")
                            )
                    );

                    resultado.setRpRisco(
                            NivelRisco.valueOf(
                                    rs.getString("rp_risco")
                            )
                    );

                    resultado.setLaRisco(
                            NivelRisco.valueOf(
                                    rs.getString("la_risco")
                            )
                    );

                    resultado.setDeRisco(
                            NivelRisco.valueOf(
                                    rs.getString("de_risco")
                            )
                    );

                    resultado.setDpRisco(
                            NivelRisco.valueOf(
                                    rs.getString("dp_risco")
                            )
                    );

                    return resultado;
                }
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao buscar notas do funcionário: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar nada
    }

}