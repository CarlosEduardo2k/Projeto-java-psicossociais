package dao;

import conexao.Conexao;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * DAO responsável por todas as operações da tabela "funcionario".
 *
 * A ideia do DAO é centralizar toda comunicação com o banco.
 *
 * Tela -> DAO -> Banco de Dados
 *
 * Assim a interface não precisa conhecer SQL.
 */
public class FuncionarioDAO {

    /*
     * Salva um funcionário no banco de dados.
     *
     * Recebe um objeto Funcionario contendo:
     * - nome
     * - cpf
     *
     * Retorna:
     * - id gerado pelo PostgreSQL se der tudo certo
     * - -1 caso aconteça algum erro
     */
    public int salvar(Funcionario funcionario) {

        /*
         * Comando SQL que será enviado para o PostgreSQL.
         *
         * Os ? são parâmetros que serão preenchidos depois.
         *
         * RETURNING id é uma funcionalidade do PostgreSQL
         * que devolve o ID criado logo após o INSERT.
         */
        String sql =
                "INSERT INTO funcionario(nome, cpf) VALUES (?, ?) RETURNING id";

        try (

                /*
                 * Abre uma conexão com o banco.
                 *
                 * conn representa o "canal de comunicação"
                 * entre o Java e o PostgreSQL.
                 */
                Connection conn = Conexao.conectar();

                /*
                 * Prepara o comando SQL.
                 *
                 * O PreparedStatement permite:
                 * - executar SQL
                 * - passar parâmetros de forma segura
                 * - evitar SQL Injection
                 */
                PreparedStatement stmt =
                        conn.prepareStatement(sql)

        ) {

            /*
             * Preenche o primeiro ? da consulta.
             *
             * SQL:
             * INSERT INTO funcionario(nome, cpf)
             * VALUES (?, ?)
             *
             * Primeiro ? = nome
             */
            stmt.setString(
                    1,
                    funcionario.getNome()
            );

            /*
             * Preenche o segundo ? da consulta.
             *
             * Segundo ? = cpf
             */
            stmt.setString(
                    2,
                    funcionario.getCpf()
            );

            /*
             * Executa o SQL no banco.
             *
             * Como usamos RETURNING id,
             * o PostgreSQL devolve um resultado.
             *
             * Esse resultado é armazenado dentro
             * do ResultSet.
             */
            ResultSet rs = stmt.executeQuery();

            /*
             * Move o cursor para a primeira linha.
             *
             * O ResultSet sempre começa "antes"
             * da primeira linha.
             *
             * Se existir alguma linha retornada,
             * rs.next() devolve true.
             */
            if (rs.next()) {

                /*
                 * Lê o valor da coluna "id"
                 * retornada pelo PostgreSQL.
                 *
                 * Exemplo:
                 *
                 * id
                 * ---
                 * 15
                 *
                 * Resultado:
                 * idGerado = 15
                 */
                int idGerado = rs.getInt("id");

                /*
                 * Retorna o ID criado para quem chamou
                 * o método.
                 *
                 * Exemplo:
                 *
                 * int id = dao.salvar(funcionario);
                 *
                 * id receberá o valor retornado aqui.
                 */
                return idGerado;
            }
        } catch (SQLException e) {

            /*
             * Caso aconteça qualquer erro relacionado
             * ao banco de dados:
             *
             * - conexão falhou
             * - tabela não existe
             * - coluna incorreta
             * - SQL inválido
             *
             * o erro será exibido no console.
             */
            e.printStackTrace();

        }
        /*
         * Se algo deu errado e não foi possível
         * obter um ID válido, retornamos -1.
         *
         * Como IDs nunca são negativos,
         * podemos usar -1 como indicador de falha.
         */
        return -1;
    }
}