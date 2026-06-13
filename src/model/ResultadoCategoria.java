package model;

// Representa o resultado final do questionário para um determinado funcionário.
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

    // Riscos de cada categoria
    private NivelRisco otRisco;
    private NivelRisco ctRisco;
    private NivelRisco rtRisco;
    private NivelRisco rpRisco;
    private NivelRisco laRisco;
    private NivelRisco deRisco;
    private NivelRisco dpRisco;

    // Construtor vazio
    public ResultadoCategoria() {
    }
    // Construtor completo
    public ResultadoCategoria(
            int funcionarioId,
            int ot,
            int ct,
            int rt,
            int rp,
            int la,
            int de,
            int dp,
            NivelRisco otRisco,
            NivelRisco ctRisco,
            NivelRisco rtRisco,
            NivelRisco rpRisco,
            NivelRisco laRisco,
            NivelRisco deRisco,
            NivelRisco dpRisco
    ) {
        this.funcionarioId = funcionarioId;
        this.ot = ot;
        this.ct = ct;
        this.rt = rt;
        this.rp = rp;
        this.la = la;
        this.de = de;
        this.dp = dp;

        this.otRisco = otRisco;
        this.ctRisco = ctRisco;
        this.rtRisco = rtRisco;
        this.rpRisco = rpRisco;
        this.laRisco = laRisco;
        this.deRisco = deRisco;
        this.dpRisco = dpRisco;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public int getLa() {
        return la;
    }

    public void setLa(int la) {
        this.la = la;
    }

    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public NivelRisco getOtRisco() {
        return otRisco;
    }

    public void setOtRisco(NivelRisco otRisco) {
        this.otRisco = otRisco;
    }

    public NivelRisco getCtRisco() {
        return ctRisco;
    }

    public void setCtRisco(NivelRisco ctRisco) {
        this.ctRisco = ctRisco;
    }

    public NivelRisco getRtRisco() {
        return rtRisco;
    }

    public void setRtRisco(NivelRisco rtRisco) {
        this.rtRisco = rtRisco;
    }

    public NivelRisco getRpRisco() {
        return rpRisco;
    }

    public void setRpRisco(NivelRisco rpRisco) {
        this.rpRisco = rpRisco;
    }

    public NivelRisco getLaRisco() {
        return laRisco;
    }

    public void setLaRisco(NivelRisco laRisco) {
        this.laRisco = laRisco;
    }

    public NivelRisco getDeRisco() {
        return deRisco;
    }

    public void setDeRisco(NivelRisco deRisco) {
        this.deRisco = deRisco;
    }

    public NivelRisco getDpRisco() {
        return dpRisco;
    }

    public void setDpRisco(NivelRisco dpRisco) {
        this.dpRisco = dpRisco;
    }
}