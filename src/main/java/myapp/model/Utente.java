package myapp.model;


public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String indirizzo;
    private String documentoId;

    // Costruttore
    public Utente(int id, String nome, String cognome, String dataDiNascita, String indirizzo, String documentoId) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
        this.documentoId = documentoId;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    // toString() per una rappresentazione testuale
    @Override
    public String toString() {
        return "Utente{id=" + id + ", nome='" + nome + "', cognome='" + cognome + "', dataDiNascita=" + dataDiNascita +
               ", indirizzo='" + indirizzo + "', documentoId='" + documentoId + "'}";
    }
}

