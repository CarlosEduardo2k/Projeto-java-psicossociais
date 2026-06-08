package questionario;

public class Pergunta {

    private final int id;
    private final String texto;
    private final Categoria categoria;
    private boolean invertida;

    public Pergunta(int id, String texto, Categoria categoria, boolean invertida ){
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
        this.invertida = invertida;
    }

    public int getId(){
        return id;
    }
    public String getTexto(){
        return texto;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public boolean isInvertida(){
        return invertida;
    }
}