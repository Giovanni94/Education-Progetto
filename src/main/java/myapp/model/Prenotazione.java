package myapp.model;

import java.time.LocalDate;

public class Prenotazione {
    private int id;
    private int idCorso;
    private int idUtente;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    // Costruttore
    public Prenotazione(int id, int idCorso, int idUtente, LocalDate dataInizio, LocalDate dataFine) {
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

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
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
