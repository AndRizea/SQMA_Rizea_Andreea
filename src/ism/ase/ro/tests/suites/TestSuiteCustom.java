package ism.ase.ro.tests.suites;

import ism.ase.ro.tests.cases.TestCase1;
import ism.ase.ro.tests.cases.TestCase2;
import ism.ase.ro.tests.cases.TestCase3;
import ism.ase.ro.tests.categories.ImportantTest;
import ism.ase.ro.tests.categories.PerformanceTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@IncludeCategory({ImportantTest.class, PerformanceTest.class})
@Suite.SuiteClasses({ TestCase1.class, TestCase2.class, TestCase3.class })
public class TestSuiteCustom {
}
