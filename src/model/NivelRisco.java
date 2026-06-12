package model;

import java.awt.*;

public enum NivelRisco {
    ALTO("Alto", new Color(197,28,64)),
    MODERADO("Moderado", new Color(255,140,0)),
    BAIXO("Baixo", new Color(46,139,87));

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

    public static NivelRisco definirPelaNota(double nota){
        if (nota >=30.0) return ALTO;
        else if (nota >=15.0) return MODERADO;
        return BAIXO;
    }

}
