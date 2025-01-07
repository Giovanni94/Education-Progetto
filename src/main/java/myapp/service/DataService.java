package myapp.service;

import myapp.model.Utente;
import myapp.model.Corso;
import myapp.model.Prenotazione;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    private List<Corso> corsi;
    private List<Utente> utenti;
    private List<Prenotazione> prenotazioni;

    public DataService() {
        corsi = new ArrayList<>();
        utenti = new ArrayList<>();
        prenotazioni = new ArrayList<>();
    }

    // Carica gli utenti dal file CSV
    public List<Utente> caricaUtenti(String filePath) throws IOException {
        List<Utente> utenti = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        // Salta la prima riga (intestazione)
        lines = lines.subList(1, lines.size());

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length < 6) {
                System.out.println("Riga malformata (Utente): " + line);
                continue;
            }

            try {
                int id = Integer.parseInt(data[0]);
                String nome = data[1].trim();
                String cognome = data[2].trim();
                LocalDate dataDiNascita = LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String indirizzo = data[4].trim();
                String documentoId = data[5].trim();

                utenti.add(new Utente(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
            } catch (NumberFormatException e) {
                System.out.println("Errore nel parsing dell'ID dell'utente: " + line);
            }
        }

        return utenti;
    }

    // Carica i corsi dal file CSV
    public List<Corso> caricaCorsi(String filePath) throws IOException {
        List<Corso> corsi = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        lines = lines.subList(1, lines.size());

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length < 7) {
                System.out.println("Riga malformata (Corso): " + line);
                continue;
            }

            try {
                int id = Integer.parseInt(data[0].trim());
                String nome = data[1].trim();
                String descrizione = data[2].trim();
                LocalDate dataInizio = LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                int durata = Integer.parseInt(data[4].trim());
                String luogo = data[5].trim();
                boolean disponibile = Boolean.parseBoolean(data[6].trim());

                corsi.add(new Corso(id, nome, descrizione, dataInizio, durata, luogo, disponibile));
            } catch (NumberFormatException e) {
                System.out.println("Errore nel parsing del Corso: " + line);
            }
        }

        return corsi;
    }

    // Carica le prenotazioni dal file CSV
    public List<Prenotazione> caricaPrenotazioni(String filePath) throws IOException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        lines = lines.subList(1, lines.size());

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length < 5) {
                System.out.println("Riga malformata (Prenotazione): " + line);
                continue;
            }

            try {
                int id = Integer.parseInt(data[0].trim());
                int idCorso = Integer.parseInt(data[1].trim());
                int idUtente = Integer.parseInt(data[2].trim());
                LocalDate dataInizio = LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dataFine = LocalDate.parse(data[4].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                prenotazioni.add(new Prenotazione(id, idCorso, idUtente, dataInizio, dataFine));
            } catch (NumberFormatException e) {
                System.out.println("Errore nel parsing della Prenotazione: " + line);
            }
        }

        return prenotazioni;
    }

    // Prenota un corso per un utente
    public void prenotaCorso(int idCorso, int idUtente) {
        Corso corso = findCorsoById(idCorso);
        Utente utente = findUtenteById(idUtente);

        if (corso != null && utente != null && corso.isDisponibile()) {
            Prenotazione prenotazione = new Prenotazione(prenotazioni.size() + 1, idCorso, idUtente, LocalDate.now(), LocalDate.now().plusDays(corso.getDurataOre()));
            prenotazioni.add(prenotazione);
            System.out.println("Prenotazione effettuata con successo.");
        } else {
            System.out.println("Corso non disponibile o utente non trovato.");
        }
    }

    // Disdire una prenotazione
    public void disdirePrenotazione(int idPrenotazione) {
        Prenotazione prenotazione = findPrenotazioneById(idPrenotazione);
        if (prenotazione != null) {
            prenotazioni.remove(prenotazione);
            System.out.println("Prenotazione annullata.");
        } else {
            System.out.println("Prenotazione non trovata.");
        }
    }

    // Aggiungi un nuovo utente
    public void aggiungiUtente(Utente utente) {
        utenti.add(utente);
        System.out.println("Utente aggiunto: " + utente.getNome() + " " + utente.getCognome());
    }

    // Esporta i corsi disponibili in un file CSV
    public void esportaCorsiDisponibili(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID;Nome;Descrizione;Data Inizio;Durata;Luogo;Disponibile\n");
            for (Corso corso : corsi) {
                if (corso.isDisponibile()) {
                    writer.write(corso.getId() + ";" + corso.getNome() + ";" + corso.getDescrizione() + ";" +
                            corso.getData() + ";" + corso.getDurataOre() + ";" + corso.getLuogo() + ";" + corso.isDisponibile() + "\n");
                }
            }
            System.out.println("Corsi esportati in " + filePath);
        } catch (IOException e) {
            System.out.println("Errore durante l'esportazione dei corsi: " + e.getMessage());
        }
    }

    // Trova un corso per ID
    private Corso findCorsoById(int id) {
        for (Corso corso : corsi) {
            if (corso.getId() == id) {
                return corso;
            }
        }
        return null;
    }

    // Trova un utente per ID
    private Utente findUtenteById(int id) {
        for (Utente utente : utenti) {
            if (utente.getId() == id) {
                return utente;
            }
        }
        return null;
    }

    // Trova una prenotazione per ID
    private Prenotazione findPrenotazioneById(int id) {
        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getId() == id) {
                return prenotazione;
            }
        }
        return null;
    }
}
