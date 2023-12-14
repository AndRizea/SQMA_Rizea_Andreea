package ism.ase.ro.tests.cases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import ism.ase.ro.classes.Product;
import ism.ase.ro.exceptions.InvalidNameException;
import ism.ase.ro.exceptions.InvalidPriceException;
import ism.ase.ro.exceptions.InvalidSoldItemsException;
import ism.ase.ro.exceptions.NullArrayException;
import ism.ase.ro.tests.categories.ImportantTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestCase1 {

    public static Product product;
    private static String INITIAL_NAME = "Water";
    private static final float INITIAL_PRICE = Product.MIN_PRICE + 5;
    private static ArrayList<Integer> INITIAL_SOLD_ITEMS = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTwoParamsConstructor() {
        product = new Product(INITIAL_NAME, INITIAL_PRICE);

        assertEquals(INITIAL_NAME, product.getName());
        assertEquals(INITIAL_PRICE, product.getPrice(), 0.01);

    }

    @Category(ImportantTest.class)
    @Test
    public void testThreeParamsConstructor() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(4);
        INITIAL_SOLD_ITEMS.add(8);

        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);

        assertEquals(INITIAL_NAME, product.getName());
        assertEquals(INITIAL_PRICE, product.getPrice(), 0.01);
        assertArrayEquals(INITIAL_SOLD_ITEMS.toArray(), product.getWeeklySoldItems().toArray());
    }

    @Test(expected = InvalidNameException.class)
    public void testSetNameTwoParamsConstructor() {
        String shortName = "Eau";
        product = new Product(shortName, INITIAL_PRICE);

    }

    @Test(expected = InvalidPriceException.class)
    public void testSetPriceTwoParamsConstructor() {
        float newPrice = 0f;
        product = new Product(INITIAL_NAME, newPrice);

    }

    @Category(ImportantTest.class)
    @Test(expected = InvalidPriceException.class)
    public void testSetPriceThreeParamsConstructor() {

        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(4);
        INITIAL_SOLD_ITEMS.add(8);

        float newPrice = 100000.454f;
        product = new Product(INITIAL_NAME, newPrice, INITIAL_SOLD_ITEMS);
    }

    @Category(ImportantTest.class)
    @Test(expected = InvalidSoldItemsException.class)
    public void testSetSoldItemsThreeParamsConstructor() {

        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(0);
        INITIAL_SOLD_ITEMS.add(2000);
        INITIAL_SOLD_ITEMS.add(856);

        float newPrice = 1000f;
        product = new Product(INITIAL_NAME, newPrice, INITIAL_SOLD_ITEMS);
    }

    @Category(ImportantTest.class)
    @Test
    public void testReferenceArrayThreeParamsConstructor() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(4);
        INITIAL_SOLD_ITEMS.add(8);

        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);

        INITIAL_SOLD_ITEMS.set(0, 7);

        assertNotEquals(INITIAL_SOLD_ITEMS.toArray(), product.getWeeklySoldItems().toArray());
        assertNotEquals(INITIAL_SOLD_ITEMS.toArray()[0], product.getWeeklySoldItems().toArray()[0]);

    }

    @Category(ImportantTest.class)
    @Test(expected = NullArrayException.class)
    public void testExistenceArrayThreeParamsConstructor() {
        INITIAL_SOLD_ITEMS = null;
        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);
    }


    @Test
    public void testReferenceSetSales() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(4);
        INITIAL_SOLD_ITEMS.add(8);

        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);

        INITIAL_SOLD_ITEMS.set(0, 7);

        product.setSales(INITIAL_SOLD_ITEMS);

        INITIAL_SOLD_ITEMS.set(0, 15);

        assertNotEquals(INITIAL_SOLD_ITEMS.toArray(), product.getWeeklySoldItems().toArray());
        assertNotEquals(INITIAL_SOLD_ITEMS.toArray()[0], product.getWeeklySoldItems().toArray()[0]);

    }

    @Test(expected = NullArrayException.class)
    public void testExistenceSetSales() {
        INITIAL_SOLD_ITEMS = new ArrayList<>();

        INITIAL_SOLD_ITEMS.add(2);
        INITIAL_SOLD_ITEMS.add(4);
        INITIAL_SOLD_ITEMS.add(8);

        product = new Product(INITIAL_NAME, INITIAL_PRICE, INITIAL_SOLD_ITEMS);

        product.setSales(null);
    }


}
