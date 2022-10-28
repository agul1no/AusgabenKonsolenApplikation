package com.agulicious.ui;

import com.agulicious.logic.FileHandler;
import com.agulicious.model.Expense;
import com.agulicious.settings.AppCommands;
import com.agulicious.settings.AppTexts;
import com.agulicious.settings.DateFormatter;
import de.rhistel.logic.ConsoleReader;

import java.util.List;

/**
 * Implementiert die Interaktion zwischen UI (User Interface / Benutzeroberfläche) und dem Benutzer.
 */
public class UiController {

    //region Konstanten
    //endregion

    //region Attribute
    private List<Expense> expenseList;
    //endregion

    //region Konstruktor
    public UiController() {
        //this.expenseList = TestData.getTestExpenses();
        this.expenseList = FileHandler.getInstance().readNotesFromCsvFile();
    }
    //endregion

    //region Methoden
    public void startUi() {
        printAppName();
        handelUserInteraction();
    }

    private void printAppName() {
        System.out.println(AppTexts.APP_NAME);
    }

    /**
     * Gibt das Hauptmenü aus, erwartet eine Nutzereingabe, wertet diese aus
     * und leitet weiter Logik ein.
     * Die Interaktion mit dem Hauptmenü ist fortlaufend über das rekursive Aufrufen der handleUserInteraction Methode
     */
    private void handelUserInteraction() {
        int userChoice;
        printMainMenu();
        userChoice = ConsoleReader.in.readPositivInt();
        switch (userChoice) {
            case AppCommands.SHOW_EXPENSE_OPTION:
                if (!expenseList.isEmpty()) showExpenses();
                else System.out.println(AppTexts.NO_EXPENSES_EXISTING);
                break;
            case AppCommands.ADD_EXPENSE_OPTION:
                addExpense();
                break;
            case AppCommands.DELETE_EXPENSE_OPTION:
                if (!expenseList.isEmpty()) deleteExpense();
                else System.out.println(AppTexts.NO_EXPENSES_EXISTING);
                break;
            case AppCommands.EDIT_EXPENSE_OPTION:
                if (!expenseList.isEmpty()) editExpense();
                else System.out.println(AppTexts.NO_EXPENSES_EXISTING);
                break;
            case AppCommands.QUIT_OPTION:
                System.out.println(AppTexts.GOODBYE_MESSAGE);
                System.exit(AppCommands.QUIT_OPTION);
            default:
                System.out.println(AppTexts.WRONG_INPUT_MESSAGE);
        }
        handelUserInteraction();
    }

    private void printMainMenu() {
        System.out.println(AppTexts.MAIN_MENU);
    }

    /**
     * Zeigt alle gespeicherten Ausgaben auf der Konsole an
     */
    private void showExpenses() {
        sortExpensesListByDateAndAmount();
        System.out.println(AppTexts.YOUR_CURRENT_LIST_MESSAGE);
        System.out.printf("%5s %24s %20s %26s\n", "Index", "Titel", "Betrag", "Datum");
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.printf("%3d %26s %20s %26s\n",
                    i,
                    expenseList.get(i).getExpenseName(),
                    expenseList.get(i).getExpenseAmount() + " €",
                    DateFormatter.transformDateMillisToDateString(expenseList.get(i).getExpenseDate())
            );
        }
        System.out.println(AppTexts.SEPARATOR);
        System.out.println("Ausgabenanzahl Total: " + expenseList.size() + "\tTotal ausgegeben: " + calculateTotalAmount() + " €");

    }

    /**
     * sortiert die Liste der Ausgaben primär nach dem Datum, sekundär nach dem Betrag
     */
    private void sortExpensesListByDateAndAmount() {
        expenseList.sort((firstExpense, secondExpense) -> {
            Long firstExpenseDate = firstExpense.getExpenseDate();
            Long secondExpenseDate = secondExpense.getExpenseDate();

            long dateCompare = firstExpenseDate.compareTo(secondExpenseDate);

            if (dateCompare != 0) {
                return (int) -dateCompare;
            }
            Integer firstExpenseAmount = firstExpense.getExpenseAmount();
            Integer secondExpenseAmount = secondExpense.getExpenseAmount();

            int amountCompare = firstExpenseAmount.compareTo(secondExpenseAmount);

            return amountCompare;
        });
    }

    /**
     * Rechnet wie viel insgesamt ausgegeben wurde
     * @return : {@link Integer} Summe der Ausgaben
     */
    private int calculateTotalAmount() {
        int total = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            total += expenseList.get(i).getExpenseAmount();
        }
        return total;
    }

    /**
     * Legt eine neue Ausgabe anhand von Nutzereingaben an
     * und fügt sie der Liste hinzu.
     */
    private void addExpense() {
        String expenseName = askUserIntroduceExpenseName();
        int expenseAmount = askUserIntroduceExpenseAmount();
        long expenseDate = System.currentTimeMillis();

        expenseList.add(new Expense(expenseName, expenseAmount, expenseDate));
        sortExpensesListByDateAndAmount();
        FileHandler.getInstance().saveExpensesToCsvFile(expenseList);
        System.out.println(AppTexts.EXPENSE_ADDED_SUCCESSFULLY_MESSAGE);
    }

    /**
     * Fragt den User nach dem Namen der Ausgabe und gibt diesen als String zurück
     * @return : {@link String} Name der Ausgabe
     */
    private String askUserIntroduceExpenseName() {
        System.out.println(AppTexts.ENTER_EXPENSE_NAME_MESSAGE);
        String noteName = ConsoleReader.in.readMandatoryString();
        while (noteName.length() < AppCommands.MINIMAL_NAME_LENGTH || noteName.length() > AppCommands.MAX_NAME_LENGTH) {
            System.out.println(AppTexts.ENTER_EXPENSE_NAME_MESSAGE);
            noteName = ConsoleReader.in.readMandatoryString();
        }
        return noteName;
    }

    /**
     * Fragt den User nach dem Betrag der Ausgabe und gibt diesen als Integer zurück
     * @return : {@link Integer} Name der Ausgabe
     */
    private int askUserIntroduceExpenseAmount() {
        System.out.println(AppTexts.ENTER_EXPENSE_AMOUNT_MESSAGE);
        int expenseAmount = ConsoleReader.in.readPositivInt();
        while (expenseAmount > 99999) {
            System.out.println(AppTexts.ENTER_EXPENSE_AMOUNT_MESSAGE);
            expenseAmount = ConsoleReader.in.readPositivInt();
        }
        return expenseAmount;
    }

    /**
     * Löscht eine Ausgabe aus der Liste anhand einer Nutzereingabe für
     * den Index.
     */
    private void deleteExpense() {
        showExpenses();
        System.out.println(AppTexts.DELETE_EXPENSE_MESSAGE);
        int deleteUserChoice = ConsoleReader.in.readPositivInt();
        deleteUserChoice = checkDeleteUserChoice(deleteUserChoice);

        expenseList.remove(deleteUserChoice);
        sortExpensesListByDateAndAmount();
        FileHandler.getInstance().saveExpensesToCsvFile(expenseList);
        System.out.println(AppTexts.EXPENSE_DELETED_SUCCESSFULLY_MESSAGE);
    }

    /**
     * Lässt den Nutzer eine Ausgabe zum Bearbeiten auswählen.
     * Danach werden neue Daten eingelesen und die Notiz abgeändert.
     */
    private void editExpense() {
        showExpenses();
        System.out.println(AppTexts.EDIT_EXPENSE_MESSAGE);
        int editUserChoice = ConsoleReader.in.readPositivInt();
        editUserChoice = checkEditUserChoice(editUserChoice);
        Expense currentExpense = expenseList.get(editUserChoice);

        System.out.println("Die Ausgabe wurde " + currentExpense.getExpenseName() + " benannt. Wie möchten Sie es ändern?");
        String expenseName = askUserIntroduceExpenseName();
        System.out.println("Der Betrag war " + currentExpense.getExpenseAmount() + " €. Wie möchten Sie es ändern?");
        int expenseAmount = askUserIntroduceExpenseAmount();

        expenseList.set(editUserChoice, new Expense(expenseName, expenseAmount, currentExpense.getExpenseDate()));
        sortExpensesListByDateAndAmount();
        FileHandler.getInstance().saveExpensesToCsvFile(expenseList);
        System.out.println(AppTexts.EXPENSE_EDITED_SUCCESSFULLY_MESSAGE);
    }

    /**
     * Kontrolliert die Nutzereingabe für das Löschen einer Ausgabe und gibt die Auswahl als Integer zurück
     * @param deleteUserChoice : {@link Integer} Auswahl des User vor Auswertung
     * @return : {@link Integer} Auswahl des Users nach der Auswertung
     */
    private int checkDeleteUserChoice(int deleteUserChoice) {
        while (deleteUserChoice >= expenseList.size()) {
            System.out.println(AppTexts.WRONG_INPUT_MESSAGE);
            System.out.println(AppTexts.DELETE_EXPENSE_MESSAGE);
            deleteUserChoice = ConsoleReader.in.readPositivInt();
        }
        return deleteUserChoice;
    }

    /**
     * Kontrolliert die Nutzereingabe für das Bearbeiten einer Ausgabe und gibt die Auswahl als Integer zurück
     * @param editUserChoice : {@link Integer} Auswahl des User vor Auswertung
     * @return : {@link Integer} Auswahl des Users nach der Auswertung
     */
    private int checkEditUserChoice(int editUserChoice) {
        while (editUserChoice >= expenseList.size()) {
            System.out.println(AppTexts.WRONG_INPUT_MESSAGE);
            System.out.println(AppTexts.EDIT_EXPENSE_MESSAGE);
            editUserChoice = ConsoleReader.in.readPositivInt();
        }
        return editUserChoice;
    }
    //endregion

}
