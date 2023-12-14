package ism.ase.ro.tests.suites;

import ism.ase.ro.tests.cases.TestCase1;
import ism.ase.ro.tests.cases.TestCase2;
import ism.ase.ro.tests.cases.TestCase3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCase1.class, TestCase2.class, TestCase3.class})
public class TestSuiteAll {
}
