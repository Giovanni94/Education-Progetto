package myapp.controller;

import myapp.model.Utente;
import myapp.model.Prenotazione;
import myapp.model.Corso;
import myapp.service.DataService;
import myapp.util.CorsoFileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PrenotazioneController {
    
    private DataService dataService;

    public PrenotazioneController(DataService dataService) {
        this.dataService = dataService;
    }

    // Carica i dati e visualizza i corsi disponibili
    public void caricaDatiEVisualizzaCorsi(String utentiFilePath, String corsiFilePath, String prenotazioniFilePath) {
        try {
            List<Utente> utenti = dataService.caricaUtenti(utentiFilePath);
            List<Corso> corsi = CorsoFileLoader.caricaCorsi(corsiFilePath);
            List<Prenotazione> prenotazioni = dataService.caricaPrenotazioni(prenotazioniFilePath);

            // Visualizza i corsi
            System.out.println("\nCorsi presenti nel sistema:");
            corsi.forEach(corso -> System.out.println(corso));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Prenota un corso
    public void prenotaCorso(int id, int idCorso, int idUtente, String dataInizio, String dataFine) {
        dataService.prenotaCorso(id, idCorso, idUtente, dataInizio, dataFine);
    }

    // Disdire una prenotazione
    public void disdirePrenotazione(List<Prenotazione> prenotazioni, Scanner scanner) {
        if (prenotazioni.isEmpty()) {
            System.out.println("Non ci sono prenotazioni da disdire.");
            return;
        }

        System.out.println("Prenotazioni esistenti:");
        prenotazioni.forEach(prenotazione -> System.out.println(prenotazione));

        System.out.print("Inserisci l'ID della prenotazione da disdire: ");
        int prenotazioneId = scanner.nextInt();

        Prenotazione prenotazione = prenotazioni.stream()
                .filter(p -> p.getId() == prenotazioneId)
                .findFirst()
                .orElse(null);

        if (prenotazione == null) {
            System.out.println("Prenotazione con ID " + prenotazioneId + " non trovata.");
            return;
        }

        prenotazioni.remove(prenotazione);
        System.out.println("Prenotazione con ID " + prenotazioneId + " disdetta con successo!");
    }

    // Esporta i corsi disponibili
    public void esportaCorsiDisponibili(String filePath) {
        List<Corso> corsi = CorsoFileLoader.caricaCorsi(filePath);
        corsi.forEach(corso -> System.out.println(corso));
    }
}
