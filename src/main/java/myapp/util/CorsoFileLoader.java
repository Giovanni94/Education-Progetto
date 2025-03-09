package myapp.util;

import myapp.model.Corso;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CorsoFileLoader {

    public static List<Corso> caricaCorsi(String filePath) {
        List<Corso> corsi = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                // Salta l'intestazione del CSV
                if (line.startsWith("ID")) continue;

                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                String nome = data[1];
                String descrizione = data[2];
                String dataCorso = data[3];
                int durataOre = Integer.parseInt(data[4]);
                String luogo = data[5];
                boolean disponibile = Boolean.parseBoolean(data[6]);

                // Aggiungi il corso alla lista
                corsi.add(new Corso(id, nome, descrizione, dataCorso, durataOre, luogo, disponibile));
            }
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento del file: " + e.getMessage());
            e.printStackTrace();  // Opzionale: per il debug, stampa lo stack trace
        }
        return corsi;
    }
}
