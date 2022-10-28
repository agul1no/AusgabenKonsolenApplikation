package com.agulicious.main;

import com.agulicious.ui.UiController;

/**
 * <h2>Startklasse des Hauptprogramms</h2>
 */

public class Main {

    /**
     * Startmethode des Programms
     * @param args : {@link String[]} : Startparameter
     */
    public static void main(String[] args) {
        UiController uiController = new UiController();
        uiController.startUi();
    }
}
