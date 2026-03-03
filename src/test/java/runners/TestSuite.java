package runners;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Executes all test classes across all packages at once.
 */
@Suite
@SuiteDisplayName("All API Tests Suite")
@SelectPackages({ "Users", "Posts", "Comments", "Todos", "Albums", "Photos" })
public class TestSuite {
    // This class remains empty. It serves only as a holder for the above
    // annotations.
}
