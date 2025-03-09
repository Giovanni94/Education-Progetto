package main;

import myapp.controller.PrenotazioneController;
import myapp.model.Corso;
import myapp.model.Prenotazione;
import myapp.service.DataService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import myapp.model.Utente;

public class Main {
    public static void main(String[] args) {
        // Creazione delle istanze di controller e dataService
        DataService dataService = new DataService();
        PrenotazioneController controller = new PrenotazioneController(dataService);

        Scanner scanner = new Scanner(System.in);
        List<Prenotazione> prenotazioni = null;
        List<Corso> corsi = null;
        List<Utente> utenti = null;

        // Carica i dati iniziali (se i percorsi dei file sono corretti)
        String utentiFilePath = "utenti.txt";
        String corsiFilePath = "corsi.txt";
        String prenotazioniFilePath = "prenotazioni.txt";
        controller.caricaDatiEVisualizzaCorsi(utentiFilePath, corsiFilePath, prenotazioniFilePath);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Visualizza Corsi");
            System.out.println("2. Prenota Corso");
            System.out.println("3. Disdire Prenotazione");
            System.out.println("4. Esporta Corsi");
            System.out.println("0. Esci");
            System.out.print("Seleziona un'opzione: ");
            
            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    // Visualizza corsi
                    controller.caricaDatiEVisualizzaCorsi(utentiFilePath, corsiFilePath, prenotazioniFilePath);
                    break;

                case 2:
                    // Prenota un corso
                    System.out.print("Inserisci l'ID del corso da prenotare: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci l'ID del corso da prenotare: ");
                    int idCorso = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci l'ID dell'utente: ");
                    int idUtente = Integer.parseInt(scanner.nextLine());
                    System.out.println("Corso prenotato con successo!");
                     // Chiedi all'utente la data di inizio del corso
                    System.out.print("Inserisci la data di inizio (formato: dd/MM/yyyy): ");
                    String dataInizio = scanner.nextLine();

                    // Chiedi all'utente la data di fine del corso
                    System.out.print("Inserisci la data di fine (formato: dd/MM/yyyy): ");
                    String dataFine = scanner.nextLine();

                    controller.prenotaCorso(id, idCorso, idUtente, dataInizio, dataFine);
                    System.out.println("Corso prenotato con successo!");
                    break;

                case 3:
                    // Disdire una prenotazione
                    if (prenotazioni != null && !prenotazioni.isEmpty()) {
                        controller.disdirePrenotazione(prenotazioni, scanner);
                    } else {
                        System.out.println("Non ci sono prenotazioni da disdire.");
                    }
                    break;

                case 4:
                    // Esporta corsi
                    controller.esportaCorsiDisponibili(corsiFilePath);
                    break;

                case 0:
                    // Esci dal programma
                    System.out.println("Uscita in corso...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }
    }
}
