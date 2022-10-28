package com.agulicious.settings;

/**
 * Diese Klasse stellt Nutzerauswahlmöglichkeiten als öffentliche Konstanten
 * zur Verfügung. Von dieser Klasse kann kein Objekt erzeugt werden.
 */
public class AppCommands {

    //region Konstanten
    public static final int QUIT_OPTION = 0;
    public static final int SHOW_EXPENSE_OPTION = 1;
    public static final int ADD_EXPENSE_OPTION = 2;
    public static final int DELETE_EXPENSE_OPTION = 3;
    public static final int EDIT_EXPENSE_OPTION = 4;


    public static final int MINIMAL_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 16;
    //endregion

    //region Attribute
    //endregion

    //region Konstruktor
    /**
     * Privater Konstruktor um Objekterzeugung von außen zu verhindern.
     */
    private AppCommands() {
    }
    //endregion

    //region Methoden
    //endregion

}
