package ism.ase.ro.tests.cases;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import ism.ase.ro.classes.Product;
import ism.ase.ro.tests.categories.ImportantTest;
import ism.ase.ro.tests.categories.PerformanceTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class TestCase3 {

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
    public void testRightGetPercentOfBadWeeks() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        int limit = 50;
        int expectedPercent = 100;

        assertEquals(expectedPercent, product.getPercentOfBadWeeks(limit));
    }

    @Test
    public void testRightGetWeeksIndexWithMaxSales() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        ArrayList<Integer> expectedMaxValues = new ArrayList<>();
        expectedMaxValues.add(0);

        assertEquals(expectedMaxValues, product.getWeeksIndexWithMaxSales());
    }

    @Test
    public void testCrossCheckGetPercentOfBadWeeks() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        int limit = 100;

        ArrayList<Integer> expectedItems = (ArrayList<Integer>) product.getWeeklySoldItems().stream()
                .filter(item -> item < limit).collect(Collectors.toList());
        int expectedPercentage = 100 * expectedItems.size() / product.getWeeklySoldItems().size();
        int actualPercentage = product.getPercentOfBadWeeks(limit);

        assertEquals(expectedPercentage, actualPercentage);

    }

    @Test
    public void testCrossCheckGetWeeksIndexWithMaxSales() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(FIRST_WEEK);
        INITIAL_SOLD_ITEMS.add(SECOND_WEEK);
        INITIAL_SOLD_ITEMS.add(THIRD_WEEK);
        product.setSales(INITIAL_SOLD_ITEMS);

        int maximumItem = product.getWeeklySoldItems().stream().max(Integer::compare).get();
        ArrayList<Integer> filteredItems = (ArrayList<Integer>) product.getWeeklySoldItems().stream()
                .filter(item -> item >= maximumItem).collect(Collectors.toList());

        ArrayList<Integer> expectedItems = new ArrayList<>();

        for (int i = 0; i < filteredItems.size(); i++) {
            expectedItems.add(i);
        }

        assertEquals(expectedItems, product.getWeeksIndexWithMaxSales());

    }

    @Test
    public void testInverseRelationGetPercentOfBadWeeks() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(1);
        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(3);
        product.setSales(INITIAL_SOLD_ITEMS);

        int limit = 10;
        int expectedPercent = 0;

        assertNotEquals(expectedPercent, product.getPercentOfBadWeeks(limit));
    }

    @Test
    public void testInverseRelationGetWeeksIndexWithMaxSales() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(6);
        INITIAL_SOLD_ITEMS.add(5);
        INITIAL_SOLD_ITEMS.add(154);
        product.setSales(INITIAL_SOLD_ITEMS);

        ArrayList<Integer> expectedMaxValues = new ArrayList<>();
        expectedMaxValues.add(0);

        assertNotEquals(expectedMaxValues, product.getWeeksIndexWithMaxSales());
    }

    @Test
    public void testAscOrderGetPercentOfBadWeeks() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        for (int item = 1; item <= 46; item += 5) {
            soldItems.add(item);
        }
        product.setSales(soldItems);
        int limit = 50;
        int expectedPercent = 100;

        assertEquals(expectedPercent, product.getPercentOfBadWeeks(limit));
    }

    @Category({ImportantTest.class, PerformanceTest.class})
    @Test
    public void testAscOrderGetWeeksIndexWithMaxSales() {
        ArrayList<Integer> soldItems = new ArrayList<>();
        for (int item = 1; item <= 46; item += 5) {
            soldItems.add(item);
        }
        product.setSales(soldItems);

        ArrayList<Integer> expectedMaxValues = new ArrayList<>();
        expectedMaxValues.add(soldItems.size() - 1);

        assertEquals(expectedMaxValues, product.getWeeksIndexWithMaxSales());

    }

}
