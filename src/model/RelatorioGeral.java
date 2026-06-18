package model;

public class RelatorioGeral {
    private int totalRespondentes;

    // Médias gerais de cada categoria
    private double mediaOt;
    private double mediaCt;
    private double mediaRt;
    private double mediaRp;
    private double mediaLa;
    private double mediaDe;
    private double mediaDp;

    // Construtor Padrão
    public RelatorioGeral() {}

    // Getters e Setters
    public int getTotalRespondentes() { return totalRespondentes; }
    public void setTotalRespondentes(int totalRespondentes) { this.totalRespondentes = totalRespondentes; }

    public double getMediaOt() { return mediaOt; }
    public void setMediaOt(double mediaOt) { this.mediaOt = mediaOt; }

    public double getMediaCt() { return mediaCt; }
    public void setMediaCt(double mediaCt) { this.mediaCt = mediaCt; }

    public double getMediaRt() { return mediaRt; }
    public void setMediaRt(double mediaRt) { this.mediaRt = mediaRt; }

    public double getMediaRp() { return mediaRp; }
    public void setMediaRp(double mediaRp) { this.mediaRp = mediaRp; }

    public double getMediaLa() { return mediaLa; }
    public void setMediaLa(double mediaLa) { this.mediaLa = mediaLa; }

    public double getMediaDe() { return mediaDe; }
    public void setMediaDe(double mediaDe) { this.mediaDe = mediaDe; }

    public double getMediaDp() { return mediaDp; }
    public void setMediaDp(double mediaDp) { this.mediaDp = mediaDp; }
}