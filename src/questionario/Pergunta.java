package questionario;

public class Pergunta {

    private final int id;
    private final String texto;
    private final Categoria categoria;

    public Pergunta(int id, String texto, Categoria categoria ){
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
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
}
