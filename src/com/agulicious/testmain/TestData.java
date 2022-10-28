package com.agulicious.testmain;

import com.agulicious.model.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Stellt statische Methoden zur Generierung von Testdaten zur Verfügung
 */
public class TestData {

    //region Konstanten
    private static final int FIRST_INDEX = 0;
    private static final int TEST_EXPENSE_AMOUNT = 20;
    private static final int MIN_VALUE_EXPENSE_RANGE_AMOUNT = 1;
    private static final int MAX_VALUE_EXPENSE_RANGE_AMOUNT = 200;
    private static final long MIN_VALUE_EXPENSE_RANGE_DATE = 1661443339945L;
    private static final long MAX_VALUE_EXPENSE_RANGE_DATE = 1666773339940L;
    //endregion

    //region Attribute
    private static Random random = new Random();
    //endregion

    //region Konstruktor
    private TestData() {
    }
    //endregion

    //region Methoden
    public static List<Expense> getTestExpenses() {
        List<Expense> testExpenses = new ArrayList<>();


        for (int i = FIRST_INDEX; i < TEST_EXPENSE_AMOUNT; i++) {
            Expense currentExpense = new Expense("Dummy Titel " + i,
                    random.nextInt(((MAX_VALUE_EXPENSE_RANGE_AMOUNT - MIN_VALUE_EXPENSE_RANGE_AMOUNT) + 1) + MIN_VALUE_EXPENSE_RANGE_AMOUNT),
                    ThreadLocalRandom.current().nextLong(MIN_VALUE_EXPENSE_RANGE_DATE, MAX_VALUE_EXPENSE_RANGE_DATE)
            );
            testExpenses.add(currentExpense);
        }
        return testExpenses;
    }
    //endregion

}
