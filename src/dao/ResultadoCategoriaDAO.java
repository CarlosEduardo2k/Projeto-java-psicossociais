package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}