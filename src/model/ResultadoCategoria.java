package model;

// Representa o resultado final do questionário para um determinado funcionário.
public class ResultadoCategoria {

    // ID do funcionário que respondeu o questionário
    private int funcionarioId;

    // Pontuação total de cada categoria (seus campos atuais)
    private int ot;
    private int ct;
    private int rt;
    private int rp;
    private int la;
    private int de;
    private int dp;

   // Campos para guardar o texto do risco que vem direto do seu banco (BAIXO, MEDIO, ALTO)
    private String otRisco;
    private String ctRisco;
    private String rtRisco;
    private String rpRisco;
    private String laRisco;
    private String deRisco;
    private String dpRisco;

    // 1. NOVO: Construtor vazio (O DAO precisa dele para instanciar o objeto antes de preencher)
    public ResultadoCategoria() {
    }

    // 2. Seu Construtor Atual (Mantido para não quebrar outras partes do sistema)
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

    // --- GETTERS E SETTERS ---

    public int getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(int funcionarioId) { this.funcionarioId = funcionarioId; }

    // Getters e Setters das Notas Inteiras
    public int getOt() { return ot; }
    public void setOt(int ot) { this.ot = ot; }

    public int getCt() { return ct; }
    public void setCt(int ct) { this.ct = ct; }

    public int getRt() { return rt; }
    public void setRt(int rt) { this.rt = rt; }

    public int getRp() { return rp; }
    public void setRp(int rp) { this.rp = rp; }

    public int getLa() { return la; }
    public void setLa(int la) { this.la = la; }

    public int getDe() { return de; }
    public void setDe(int de) { this.de = de; }

    public int getDp() { return dp; }
    public void setDp(int dp) { this.dp = dp; }

    // Getters e Setters dos Riscos (Vindos das colunas de texto do banco)
    public String getOtRisco() { return otRisco; }
    public void setOtRisco(String otRisco) { this.otRisco = otRisco; }

    public String getCtRisco() { return ctRisco; }
    public void setCtRisco(String ctRisco) { this.ctRisco = ctRisco; }

    public String getRtRisco() { return rtRisco; }
    public void setRtRisco(String rtRisco) { this.rtRisco = rtRisco; }

    public String getRpRisco() { return rpRisco; }
    public void setRpRisco(String rpRisco) { this.rpRisco = rpRisco; }

    public String getLaRisco() { return laRisco; }
    public void setLaRisco(String laRisco) { this.laRisco = laRisco; }

    public String getDeRisco() { return deRisco; }
    public void setDeRisco(String deRisco) { this.deRisco = deRisco; }

    public String getDpRisco() { return dpRisco; }
    public void setDpRisco(String dpRisco) { this.dpRisco = dpRisco; }
}