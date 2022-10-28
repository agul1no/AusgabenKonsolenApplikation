package com.agulicious.model;

import com.agulicious.logic.FileHandler;

/**
 * Diese Klasse stellt eine Ausgaben der realen Welt dar. Sie enthält den Namen, den Betrag und das Datum der Ausgabe.
 */

public class Expense {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = ">noValueSetYet<";
    private static final int DEFAULT_INT_VALUE = -1;
    private static final long DEFAULT_LONG_VALUE = -1L;
    private final int INDEX_NAME = 0;
    private final int INDEX_AMOUNT = 1;
    private final int INDEX_DATE = 2;
    //endregion

    //region Attribute
    private String expenseName;
    private int expenseAmount;
    private long expenseDate;
    //endregion

    //region Konstruktor

    /**
     * Standardkonstruktor, der Eigenschaften mit den Standardwerten initialisiert.
     */
    public Expense() {
        expenseName = DEFAULT_STRING_VALUE;
        expenseAmount = DEFAULT_INT_VALUE;
        expenseDate = DEFAULT_LONG_VALUE;
    }

    /**
     * Konstruktor für den Fall, wenn das Datum fehlt.
     *
     * @param expenseName: {@link String} : Name der Ausgabe
     * @param expenseAmount : {@link Integer} : Betrag der Ausgabe
     */
    public Expense(String expenseName, int expenseAmount) {
        this();
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
    }

    /**
     * Konstruktor für den Fall, wenn der Betrag fehlt.
     *
     * @param expenseName : {@link String} : Name der Ausgabe
     * @param expenseDate : {@link Long} : Datum der Ausgabe als Long Variable gespeichert
     */
    public Expense(String expenseName, long expenseDate) {
        this();
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
    }

    /**
     * Konstruktor für den Fall, wenn der Name der Ausgabe fehlt.
     *
     * @param expenseAmount : {@link Integer} : Betrag der Ausgabe
     * @param expenseDate : {@link Long} : Datum der Ausgabe als Long Variable gespeichert
     */
    public Expense(int expenseAmount, long expenseDate) {
        this();
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
    }

    /**
     * Konstruktor, der im Normalfall verwendet wird, wenn eine Ausgabe alle Parameter bekommt.
     *
     * @param expenseName : {@link String} : Name der Ausgabe
     * @param expenseAmount : {@link Integer} : Betrag der Ausgabe
     * @param expenseDate : {@link Long} : Datum der Ausgabe als Long Variable gespeichert
     */
    public Expense(String expenseName, int expenseAmount, long expenseDate) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
    }

    /**
     * Überladener Konstruktor, welcher ine CSV-Zeile entgegennimmt
     * und über eine passende Methode alle Attribute initialisiert.
     *
     * @param csvLine : {@link String} : Csv-Zeile mit Attributen
     */
    public Expense(String csvLine) {
        setAttributesFromCsvLine(csvLine);
    }


    //endregion

    //region Methoden
    /**
     * Liefert ale Attribute als Csv-String zurück.
     * D.h. ein String mit Attributwerten getrennt durch ein bestimmtes Trennzeichen
     *
     * @return {@link String} : Csv-Zeile
     */
    public String getAttributesAsCvsLine() {
        return expenseName + FileHandler.DELIMITER + expenseAmount + FileHandler.DELIMITER + expenseDate + "\n";
    }

    /**
     * Befüllt alle Attribute mittels eines Csv-Strings
     *
     * @param csvLine : {@link String} : Csv-String mit Attributwerten
     */
    private void setAttributesFromCsvLine(String csvLine) {
        String[] allAttributes = csvLine.split(FileHandler.DELIMITER);

        expenseName = allAttributes[INDEX_NAME];
        expenseAmount = Integer.parseInt(allAttributes[INDEX_AMOUNT]);
        expenseDate = Long.parseLong(allAttributes[INDEX_DATE]);
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public long getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(long expenseDate) {
        this.expenseDate = expenseDate;
    }

    //endregion

}
