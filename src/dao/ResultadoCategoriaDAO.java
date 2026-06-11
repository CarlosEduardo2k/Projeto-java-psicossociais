package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import model.ResultadoCategoria;

public class ResultadoCategoriaDAO {

    // Salva o resultado das categorias no banco
    public void salvar(ResultadoCategoria resultadoCategoria){

        // Comando SQL de inserção
        String sql = "INSERT INTO resultado_categoria(funcionario_id,ot,ct,rt,rp,la,de,dp)" +
                "VALUES (?,?,?,?,?,?,?,?)";

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
                    return new ResultadoCategoria(
                            rs.getInt("funcionario_id"),
                            rs.getInt("soma_ot"),
                            rs.getInt("soma_ct"),
                            rs.getInt("soma_rt"),
                            rs.getInt("soma_rp"),
                            rs.getInt("soma_la"),
                            rs.getInt("soma_de"),
                            rs.getInt("soma_dp")
                    );
                }
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao buscar notas do funcionário: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar nada
    }

}