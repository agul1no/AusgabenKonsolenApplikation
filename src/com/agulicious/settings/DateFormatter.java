package com.agulicious.settings;

import java.text.SimpleDateFormat;

/**
 *Diese Klasse speichert das Format, in welchem das Datum nach dem auslesen aus der CSV-Datei ausgebegeben wird
 * und stellt die Methode für die Umwandlung zur Verfügung.
 */
public class DateFormatter {

    //region Konstanten
    private static String DAY_MONTH_YEAR_FORMAT = "dd.MM.yyyy";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktor
    private DateFormatter() {}
    //endregion

    //region Methoden
    public static String transformDateMillisToDateString(long dateMillis) {
        SimpleDateFormat formatter = new SimpleDateFormat(DAY_MONTH_YEAR_FORMAT);
        return formatter.format(dateMillis);
    }
    //endregion

}
