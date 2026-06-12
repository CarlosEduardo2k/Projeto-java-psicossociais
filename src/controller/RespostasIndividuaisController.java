package controller;

import dao.FuncionarioDAO;
import dao.ResultadoCategoriaDAO;
import model.Funcionario;
import model.ResultadoCategoria;

import java.util.List;

public class RespostasIndividuaisController {
    private FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
    private ResultadoCategoriaDAO resultadoCategoriaDAO = new ResultadoCategoriaDAO();

    public List<Funcionario> listaFuncionarios(){
        return funcionarioDAO.listarTodos();
    }

    public ResultadoCategoria obterNotasFuncionario(int funcionarioId){
        return resultadoCategoriaDAO.buscarPorFuncionarioId(funcionarioId);
    }
}
