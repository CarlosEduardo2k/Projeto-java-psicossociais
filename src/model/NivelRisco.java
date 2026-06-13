package model;

import java.awt.*;

public enum NivelRisco {

    ALTO("Alto", new Color(197, 28, 64)),
    MODERADO("Moderado", new Color(255, 140, 0)),
    BAIXO("Baixo", new Color(46, 139, 87));

    private final String descricao;
    private final Color cor;

    NivelRisco(String descricao, Color cor) {
        this.descricao = descricao;
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Color getCor() {
        return cor;
    }

    /**
     * Método antigo.
     * Mantido para telas que já usam nota pronta.
     */
    public static NivelRisco definirPelaNota(double nota) {

        if (nota >= 30.0) {
            return ALTO;
        }

        if (nota >= 15.0) {
            return MODERADO;
        }

        return BAIXO;
    }

    /**
     * Método novo.
     * Calcula o risco baseado na pontuação
     * e quantidade de perguntas da categoria.
     */
    public static NivelRisco definirPelaPontuacao(
            int pontuacao,
            int quantidadePerguntas
    ) {

        int minimo = quantidadePerguntas;
        int maximo = quantidadePerguntas * 5;

        double percentual =
                ((double) (pontuacao - minimo)
                        / (maximo - minimo))
                        * 100;

        if (percentual >= 67) {
            return ALTO;
        }

        if (percentual >= 34) {
            return MODERADO;
        }

        return BAIXO;
    }
}