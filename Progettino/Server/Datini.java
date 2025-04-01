package Progettino.Server;

public class Datini {
    private String comune;
    private String provincia;
    private String regione;
    private String nome;
    private String anno;
    private String dataOra;
    private String identificatore;
    private String longitudine;
    private String latitudine;

    public Datini() {
        this.comune = "";
        this.provincia = "";
        this.regione = "";
        this.nome = "";
        this.anno = "";
        this.dataOra = "";
        this.identificatore = "";
        this.longitudine = "";
        this.latitudine = "";
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getDataOra() {
        return dataOra;
    }

    public void setDataOra(String dataOra) {
        this.dataOra = dataOra;
    }

    public String getIdentificatore() {
        return identificatore;
    }

    public void setIdentificatore(String identificatore) {
        this.identificatore = identificatore;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }
}
