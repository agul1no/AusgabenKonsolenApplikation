package com.agulicious.settings;

/**
 * Diese Klasse stellt alle Programmtexte als öffentliche Konstanten zur Verfügung.
 * Von dieser Klasse kann kein Objekt erzeugt werden.
 */
public class AppTexts {

    //region Konstanten
    public static final String APP_NAME = """
            -----------------------------------------------------------------------------
                                            Willkommen
                                       Ausgaben Applikation
            -----------------------------------------------------------------------------""";

    public static final String MAIN_MENU = """
            
            Wählen Sie einer der folgenden Möglichkeiten:
            1.- Ausgaben anzeigen
            2.- Ausgabe eintragen
            3.- Ausgabe löschen
            4.- Ausgabe bearbeiten
            0.- Applikation verlassen
            """;
    public static final String WRONG_INPUT_MESSAGE = "\nFalsche Eingabe. Versuchen Sie es später nochmal";
    public static final String YOUR_CURRENT_LIST_MESSAGE = """
                                                        Ihre aktuelle Liste
                      -------------------------------------------------------------------------------------------------""";
    public static final String SEPARATOR = """
                      -------------------------------------------------------------------------------------------------""";
    public static final String GOODBYE_MESSAGE = "\nAuf Wiedersehen!";
    public static final String ENTER_EXPENSE_NAME_MESSAGE = "Geben Sie bitte einen Namen mit 3 bis 16 Charaktere";
    public static final String ENTER_EXPENSE_AMOUNT_MESSAGE = "Geben Sie bitte einen Betrag ein (max 5-stellig)";

    public static final String EXPENSE_ADDED_SUCCESSFULLY_MESSAGE = "Ausgabe erfolgreich hinzugefügt";
    public static final String EXPENSE_EDITED_SUCCESSFULLY_MESSAGE = "Ausgabe erfolgreich bearbeitet";
    public static final String DELETE_EXPENSE_MESSAGE = "Welche Ausgabe soll gelöscht werden?";
    public static final String EDIT_EXPENSE_MESSAGE = "Welche Ausgabe soll bearbeitet werden?";
    public static final String EXPENSE_DELETED_SUCCESSFULLY_MESSAGE = "Ausgabe erfolgreich gelöscht";
    public static final String NO_EXPENSES_EXISTING = "Keine Ausgaben vorhanden. Tragen Sie zuerst eine Ausgabe ein.";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktor
    private AppTexts() {

    }
    //endregion

    //region Methoden
    //endregion

}
