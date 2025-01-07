package myapp.util;

import myapp.model.Prenotazione;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneFileLoader {

    public static List<Prenotazione> caricaPrenotazioni(String filePath) throws IOException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            // Salta l'intestazione del CSV
            if (line.startsWith("ID")) continue;

            String[] data = line.split(";");
            int id = Integer.parseInt(data[0]);
            int corsoId = Integer.parseInt(data[1]);
            int utenteId = Integer.parseInt(data[2]);
            LocalDate dataInizio = LocalDate.parse(data[3]);
            LocalDate dataFine = LocalDate.parse(data[4]);

            // Aggiungi la prenotazione alla lista
            prenotazioni.add(new Prenotazione(id, corsoId, utenteId, dataInizio, dataFine));
        }

        return prenotazioni;
    }
}
