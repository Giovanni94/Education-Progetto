package myapp.model;

import java.time.LocalDate;

public class Corso {
    private int id;
    private String nome;
    private String descrizione;
    private LocalDate data;
    private int durataOre;
    private String luogo;
    private boolean disponibile;

    // Costruttore
    public Corso(int id, String nome, String descrizione, LocalDate data, int durataOre, String luogo, boolean disponibile) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.durataOre = durataOre;
        this.luogo = luogo;
        this.disponibile = disponibile;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(int durataOre) {
        this.durataOre = durataOre;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    // Metodo toString per una visualizzazione più semplice
    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", durataOre=" + durataOre +
                ", luogo='" + luogo + '\'' +
                ", disponibile=" + disponibile +
                '}';
    }
    
}

