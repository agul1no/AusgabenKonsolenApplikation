package com.agulicious.logic;

import com.agulicious.model.Expense;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Datei-Handling durch FileHandler</h2>
 *
 * <h3>Funktionalität</h3>
 * Diese Klasse speichert die Daten des Programms in einer selbst generierten CSV-Datei.</br>
 * <h3>Speicherort</h3>
 * Die Datei wird innerhalb des Projekts gespeichert. Pfad der Datei ist: src/com/agulicious/resources/expenses.csv
 */

public class FileHandler {

    //region Konstanten
    public static final String CSV_FILE_PATH = "src/com/agulicious/resources/expenses.csv";
    public static final String DELIMITER = ";";
    //endregion

    //region Attribute
    /** FileHandler soll ein singleton sein. 1.- Privates Objekt*/
    private static FileHandler instance;
    //endregion

    //region Konstruktor
    /** FileHandler soll ein singleton sein. 2.- Privater Konstruktor*/
    private FileHandler() {
        System.out.println("FileHandler erzeugt");
    }
    //endregion

    //region Methoden
    /** FileHandler soll ein singleton sein. 3.- public Methode, um zu kontrollieren, ob der FileHandler bereits initialisiert wurde
     * synchronize macht der FileHandler thread-sicher. Nachfolgende Aufrufe werden blockiert, bis der erste Aufruf des getInstance Methode
     * fertig ist.*/
    public static synchronized FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    /**
     * Liest die Datei Zeile für Zeile aus, generiert aus jeder Zeile ein Ausgaben-Objekt
     * und fügt es einer Liste hinzu. Diese Liste wird zurückgegeben.
     *
     * @return {@link List<Expense>} : Liste von Ausgaben
     */

    public List<Expense> readNotesFromCsvFile() {
        List<Expense> expenseList = new ArrayList<>();

        File csvFile = new File(CSV_FILE_PATH);

        FileInputStream fis = null;

        InputStreamReader isr = null;

        BufferedReader in = null;

        try {
            if(!csvFile.exists()) {
               csvFile.createNewFile();
            }
            fis = new FileInputStream(csvFile);
            isr = new InputStreamReader(fis);
            in = new BufferedReader(isr);

            boolean endOfFile = false;

            while (!endOfFile) {
                String csvLine = in.readLine();

                if (csvLine == null) {
                    endOfFile = true;
                } else {
                    Expense currentExpense = new Expense(csvLine);
                    expenseList.add(currentExpense);
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return expenseList;
    }

    /**
     * Speichert Ausgaben einer übergebenen Liste als CSV-Strings
     * in einer CSV-Datei.
     *
     * @param noteList : {@link List<Expense>} : Liste mit zu speichernden Ausgaben
     */
    public void saveExpensesToCsvFile(List<Expense> noteList) {
        //Anliegen meines Datei Objektes
        File csvFile = new File(CSV_FILE_PATH);
        //Brücke zur Datei definieren
        FileOutputStream fileOutputStream = null;

        //Schreibt in Bytes und definiert einen bestimmten Zeichensatz
        OutputStreamWriter outputStreamWriter = null;

        //Schreibt Zeichenketten und nutzt einen Zwischenspeicher dafür
        BufferedWriter out = null;

        try {
            fileOutputStream = new FileOutputStream(csvFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            out = new BufferedWriter(outputStreamWriter);

            for (Expense expense : noteList) {
                out.write(expense.getAttributesAsCvsLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException");
            e.printStackTrace();
        }catch (IOException e) {
            System.err.println("IOException");
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //endregion

}
