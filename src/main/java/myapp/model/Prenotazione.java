package myapp.model;


public class Prenotazione {
    private int id;
    private int idCorso;
    private int idUtente;
    private String dataInizio;
    private String dataFine;

    // Costruttore
    public Prenotazione(int id, int idCorso, int idUtente, String dataInizio, String dataFine) {
        this.id = id;
        this.idCorso = idCorso;
        this.idUtente = idUtente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", idCorso=" + idCorso +
                ", idUtente=" + idUtente +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
