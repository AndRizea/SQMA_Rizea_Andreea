package ism.ase.ro.tests.cases;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import ism.ase.ro.classes.Product;
import ism.ase.ro.exceptions.InvalidLimitException;
import ism.ase.ro.exceptions.InvalidSoldItemsException;
import ism.ase.ro.tests.categories.ImportantTest;
import ism.ase.ro.tests.categories.PerformanceTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestCase2 {

    public static Product product;
    private static String INITIAL_NAME = "Water";
    private static final float INITIAL_PRICE = Product.MIN_PRICE + 5;
    private static ArrayList<Integer> INITIAL_SOLD_ITEMS = null;
    private static int FIRST_WEEK = 24;
    private static int SECOND_WEEK = 5;
    private static int THIRD_WEEK = 13;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRightAddWeek() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        int newWeek = 25;
        product.addWeek(newWeek);

        ArrayList<Integer> soldItems = product.getWeeklySoldItems();
        int lastValue = soldItems.get(soldItems.size() - 1);

        assertEquals(newWeek, lastValue);
    }

    @Test
    public void testRightGetSoldItems() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        assertEquals(FIRST_WEEK, product.getSoldItems(0));

    }

    @Test
    public void testRightGetNoWeeksAboveLimit() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        int actualNoWeeksAboveLimit = 0;
        int limit = 100;

        assertEquals(actualNoWeeksAboveLimit, product.getNoWeeksAboveLimit(limit));
    }

    @Test(expected = InvalidSoldItemsException.class)
    public void testRangeAddWeek() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 147852;
        product.addWeek(newWeek);

    }

    @Test(expected = InvalidSoldItemsException.class)
    public void testRangeGetSoldItems() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 1245;
        product.getWeeklySoldItems().add(0, newWeek);
        product.getSoldItems(0);
    }

    @Test(expected = InvalidSoldItemsException.class)
    public void testRangeGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 1245;
        product.getWeeklySoldItems().add(newWeek);
        int limit = 100;
        product.getNoWeeksAboveLimit(limit);

    }

    @Test
    public void testLowerBoundaryAddWeek() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 0;
        product.addWeek(newWeek);

        int lastValue = product.getWeeklySoldItems().get(soldItems.size());

        assertEquals(newWeek, lastValue);
    }

    @Test
    public void testUpperBoundaryAddWeek() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 1000;
        product.addWeek(newWeek);

        int lastValue = product.getWeeklySoldItems().get(soldItems.size());

        assertEquals(newWeek, lastValue);

    }

    @Test
    public void testLowerBoudaryGetSoldItems() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 0;
        product.getWeeklySoldItems().add(0, newWeek);
        assertEquals(newWeek, product.getSoldItems(0));
    }

    @Test
    public void testUpperBoudaryGetSoldItems() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 1000;
        product.getWeeklySoldItems().add(0, newWeek);
        assertEquals(newWeek, product.getSoldItems(0));
    }

    @Test
    public void testLowerBoundaryGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 0;
        product.getWeeklySoldItems().add(newWeek);
        int limit = 100;
        int expectedNoWeeks = 0;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));

    }

    @Test
    public void testUpperBoundaryGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        product.setSales(soldItems);
        int newWeek = 1000;
        product.getWeeklySoldItems().add(newWeek);
        int limit = 100;
        int expectedNoWeeks = 1;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));

    }

    @Test
    public void testCardinalityOneGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        product.setSales(soldItems);

        int limit = 100;
        int expectedNoWeeks = 0;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));
    }

    @Test
    public void testCardinalityThreeGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        soldItems.add(THIRD_WEEK);
        product.setSales(soldItems);

        int limit = 100;
        int expectedNoWeeks = 0;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));
    }

    @Test
    public void testAscOrderingGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        for (int item = 1; item <= 45; item += 2) {
            soldItems.add(item);
        }
        product.setSales(soldItems);

        int limit = 100;
        int expectedNoWeeks = 0;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));
    }

    @Test
    public void testDescOrderingGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        for (int item = 45; item >= 1; item -= 2) {
            soldItems.add(item);
        }
        product.setSales(soldItems);

        int limit = 100;
        int expectedNoWeeks = 0;
        assertEquals(expectedNoWeeks, product.getNoWeeksAboveLimit(limit));
    }

    @Category(PerformanceTest.class)
    @Test
    public void testPerformanceGetNoWeeksAboveLimit() {
        int limit = 100;

        Random random = new Random();

        ArrayList<Integer> soldItems = new ArrayList<>(random
                .ints(1000, Product.MIN_SOLD_PRODUCTS, Product.MAX_SOLD_PRODUCTS).boxed().collect(Collectors.toList()));

        product.setSales(soldItems);

        long startTime = System.currentTimeMillis();
        product.getNoWeeksAboveLimit(limit);
        long finishTime = System.currentTimeMillis();

        long duration = finishTime - startTime;

        if (duration < 3000) {
            assertTrue(true);
        } else
            fail("Function getNoWeeksAboveLimit() took too long");

    }

    @Category(ImportantTest.class)
    @Test
    public void testCrossCheckGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        soldItems.add(THIRD_WEEK);
        product.setSales(soldItems);

        int limit = 100;

        ArrayList<Integer> expectedItems = (ArrayList<Integer>) product.getWeeklySoldItems().stream()
                .filter(item -> item >= limit).collect(Collectors.toList());

        int expectedNoWeeks = expectedItems.size();
        int actualNoWeeks = product.getNoWeeksAboveLimit(limit);

        assertEquals(expectedNoWeeks, actualNoWeeks);

    }

    @Test(expected = InvalidLimitException.class)
    public void testExistenceLimitGetNoWeeksAboveLimit() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        soldItems.add(FIRST_WEEK);
        soldItems.add(SECOND_WEEK);
        soldItems.add(THIRD_WEEK);
        product.setSales(soldItems);

        int limit = 0;

        product.getNoWeeksAboveLimit(limit);
    }

}
