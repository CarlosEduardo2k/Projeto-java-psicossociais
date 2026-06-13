package controller;

import dao.FuncionarioDAO;
import dao.RespostaQuestaoDAO;
import dao.ResultadoCategoriaDAO;
import dao.RespostaDAO;
import model.Funcionario;
import model.ResultadoCategoria;
import questionario.BancoPerguntas;
import questionario.Pergunta;

import java.util.List;
import java.util.Map;

public class RespostasIndividuaisController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private ResultadoCategoriaDAO resultadoCategoriaDAO = new ResultadoCategoriaDAO(); // Mantido!
    private RespostaQuestaoDAO respostaDAO = new RespostaQuestaoDAO();                              // Adicionado!
    private BancoPerguntas bancoPerguntas = new BancoPerguntas();                     // Adicionado!

    // MANTIDO: Usado pelas duas abas para listar quem está cadastrado
    public List<Funcionario> listaFuncionarios(){
        return funcionarioDAO.listarTodos();
    }

    // MANTIDO: A sua 1ª aba (Resultados por Categoria) continua chamando esse método normalmente!
    public ResultadoCategoria obterNotasFuncionario(int funcionarioId){
        return resultadoCategoriaDAO.buscarPorFuncionarioId(funcionarioId);
    }

    //  NOVOS MÉTODOS EXCLUSIVOS PARA A 2ª ABA (Respostas Individuais)


    //  Busca o mapa de respostas (1 a 5) direto da tabela 'resposta'
    public Map<Integer, Integer> obterRespostasDoFuncionario(int funcionarioId) {
        return respostaDAO.buscarRespostasPorFuncionarioId(funcionarioId);
    }

    //  Retorna a lista com as 25 perguntas prontas
    public List<Pergunta> obterListaDePerguntas() {
        return bancoPerguntas.carregarPerguntas();
    }
}