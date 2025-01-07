package myapp.controller;

import myapp.model.Utente;
import myapp.model.Prenotazione;
import myapp.model.Corso;
import myapp.service.DataService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class PrenotazioneController {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DataService dataService;

    public PrenotazioneController(DataService dataService) {
        this.dataService = dataService;
    }

    public void caricaDatiEVisualizzaPrenotazioni(String utentiFilePath, String corsiFilePath, String prenotazioniFilePath) {
        try {
            List<Utente> utenti = dataService.caricaUtenti(utentiFilePath);
            List<Corso> corsi = dataService.caricaCorsi(corsiFilePath);
            List<Prenotazione> prenotazioni = dataService.caricaPrenotazioni(prenotazioniFilePath);

            // Visualizza i dati o gestisci la logica di prenotazione
            System.out.println("Utenti:");
            utenti.forEach(utente -> System.out.println(utente));

            System.out.println("\nCorsi:");
            corsi.forEach(corso -> System.out.println(corso));

            System.out.println("\nPrenotazioni:");
            prenotazioni.forEach(prenotazione -> System.out.println(prenotazione));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disdirePrenotazione(List<Prenotazione> prenotazioni, Scanner scanner) {
    // Mostra le prenotazioni esistenti
    if (prenotazioni.isEmpty()) {
        System.out.println("Non ci sono prenotazioni da disdire.");
        return;
    }

    System.out.println("Prenotazioni esistenti:");
    prenotazioni.forEach(prenotazione -> System.out.println(prenotazione));

    // Chiede all'utente l'ID della prenotazione da disdire
    System.out.print("Inserisci l'ID della prenotazione che desideri disdire: ");
    int prenotazioneId = scanner.nextInt();

    // Cerca la prenotazione con l'ID dato
    Prenotazione prenotazione = prenotazioni.stream()
            .filter(p -> p.getId() == prenotazioneId)
            .findFirst()
            .orElse(null);

    // Se la prenotazione non esiste, stampa un messaggio di errore
    if (prenotazione == null) {
        System.out.println("Prenotazione con ID " + prenotazioneId + " non trovata.");
        return;
    }

    // Rimuove la prenotazione dalla lista
    prenotazioni.remove(prenotazione);
    System.out.println("Prenotazione con ID " + prenotazioneId + " disdetta con successo!");
}


}