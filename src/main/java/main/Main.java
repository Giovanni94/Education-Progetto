package main;

import myapp.service.DataService;
import myapp.controller.PrenotazioneController;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();
        PrenotazioneController controller = new PrenotazioneController(dataService);

        // Qui definisci i percorsi dei tuoi file CSV
        String utentiFilePath = "src/main/resources/data/utenti.csv";
        String corsiFilePath = "src/main/resources/data/corsi.csv";
        String prenotazioniFilePath = "src/main/resources/data/prenotazioni.csv";

        // Passi i percorsi al controller per caricare i dati e visualizzare le prenotazioni
        controller.caricaDatiEVisualizzaPrenotazioni(utentiFilePath, corsiFilePath, prenotazioniFilePath);
        
    }
}
