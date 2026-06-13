package model;

public class Resposta {

    private int funcionarioId;
    private int perguntaId;
    private int resposta;

    public Resposta(
            int funcionarioId,
            int perguntaId,
            int resposta
    ){
        this.funcionarioId = funcionarioId;
        this.perguntaId = perguntaId;
        this.resposta = resposta;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public int getPerguntaId() {
        return perguntaId;
    }

    public int getResposta() {
        return resposta;
    }
}