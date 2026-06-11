package model;

// Representa o resultado final do questionário
// para um determinado funcionário.
public class ResultadoCategoria {

    // ID do funcionário que respondeu o questionário
    private int funcionarioId;

    // Pontuação total de cada categoria
    private int ot;
    private int ct;
    private int rt;
    private int rp;
    private int la;
    private int de;
    private int dp;

    // Construtor responsável por criar um objeto
    // já com todas as pontuações preenchidas.
    public ResultadoCategoria(
            int funcionarioId,
            int ot,
            int ct,
            int rt,
            int rp,
            int la,
            int de,
            int dp
    ){
        this.funcionarioId = funcionarioId;
        this.ot = ot;
        this.ct = ct;
        this.rt = rt;
        this.rp = rp;
        this.la = la;
        this.de = de;
        this.dp = dp;
    }

    // Getters utilizados para acessar os dados do objeto

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public int getOt() {
        return ot;
    }

    public int getCt() {
        return ct;
    }

    public int getRt() {
        return rt;
    }

    public int getRp() {
        return rp;
    }

    public int getLa() {
        return la;
    }

    public int getDe() {
        return de;
    }

    public int getDp() {
        return dp;
    }
}