package myapp.util;

import myapp.model.Utente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtenteFileLoader {

    public static List<Utente> caricaUtenti(String filePath) throws IOException {
        List<Utente> utenti = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            // Salta l'intestazione del CSV
            if (line.startsWith("ID")) continue;

            String[] data = line.split(";");
            int id = Integer.parseInt(data[0]);
            String nome = data[1];
            String cognome = data[2];
            LocalDate dataDiNascita = LocalDate.parse(data[3]);
            String indirizzo = data[4];
            String documentoId = data[5];

            // Aggiungi l'utente alla lista
            utenti.add(new Utente(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
        }

        return utenti;
    }
}
