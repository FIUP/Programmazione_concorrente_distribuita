package library;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
  AuthorUnitTest.class, 
  BookUnitTest.class,
  ShelfUnitTest.class,
  LibraryStatisticsTest.class}
)

public class AllTestSuite {}