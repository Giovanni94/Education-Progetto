package myapp.service;

import myapp.model.Utente;
import myapp.util.CorsoFileLoader;
import myapp.model.Prenotazione;
import myapp.model.Corso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    // Carica la lista degli utenti dal file
    public List<Utente> caricaUtenti(String filePath) throws IOException {
        List<Utente> utenti = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Utente utente = new Utente(
                    Integer.parseInt(data[0]),  // id
                    data[1],  // nome
                    data[2],  // cognome
                    data[3],  // data di nascita (String)
                    data[4],  // indirizzo
                    data[5]   // documentoId
                );
                utenti.add(utente);
            }
        }
        return utenti;
    }

    // Carica la lista dei corsi dal file
    

    // Carica la lista delle prenotazioni dal file
    public List<Prenotazione> caricaPrenotazioni(String filePath) throws IOException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Prenotazione prenotazione = new Prenotazione(
                    Integer.parseInt(data[0]),  // id
                    Integer.parseInt(data[1]),  // idCorso
                    Integer.parseInt(data[2]),  // idUtente
                    data[3],  // dataInizio
                    data[4]   // dataFine
                );
                prenotazioni.add(prenotazione);
            }
        }
        return prenotazioni;
    }

    // Prenota un corso (aggiunge una nuova prenotazione)
    public void prenotaCorso(int id, int idCorso, int idUtente, String dataInizio, String dataFine) {
        Prenotazione prenotazione = new Prenotazione(id, idCorso, idUtente, dataInizio, dataFine);
        // Aggiungi prenotazione alla lista (questa lista dovrebbe essere persistente o passata a questo metodo)
        // Salva la prenotazione nei dati (es. su file, database, etc.)
    }

    // Disdire una prenotazione
    public void disdirePrenotazione(List<Prenotazione> prenotazioni, int prenotazioneId) {
        Prenotazione prenotazione = prenotazioni.stream()
                .filter(p -> p.getId() == prenotazioneId)
                .findFirst()
                .orElse(null);

        if (prenotazione != null) {
            prenotazioni.remove(prenotazione);
            // Salva i cambiamenti nel file o database
        }
    }

    // Esporta i corsi disponibili su un file
    public void esportaCorsiDisponibili(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            List<Corso> corsi = CorsoFileLoader.caricaCorsi(filePath);
            for (Corso corso : corsi) {
                writer.write(corso.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
