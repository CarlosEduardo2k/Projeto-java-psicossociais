package controller;

import dao.RelatorioGeralDAO;
import model.RelatorioGeral;

public class RelatorioGeralController {

    private final RelatorioGeralDAO relatorioDAO;

    public RelatorioGeralController() {
        this.relatorioDAO = new RelatorioGeralDAO();
    }

    public RelatorioGeral carregarIndicadoresEmpresa() {
        return relatorioDAO.obterDadosRelatorioGeral();
    }
}