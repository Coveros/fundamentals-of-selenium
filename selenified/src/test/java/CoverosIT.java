import com.coveros.selenified.Locator;
import com.coveros.selenified.Selenified;
import com.coveros.selenified.application.App;
import com.coveros.selenified.element.Element;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CoverosIT extends Selenified {

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://www.coveros.com");
    }

    @Test
    public void seleniumSearch() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // identify our elements
        Element searchBox = app.newElement(Locator.ID, "s");
        // perform the search
        searchBox.type("selenified");
        searchBox.submit();
        // wait for some search results to come back
        app.newElement(Locator.CLASSNAME, "header-blog").waitForState().displayed();
        // ensure the title shows what we expect
        app.azzert().titleEquals("You searched for selenified - Coveros");
        // close out the test
        finish();
    }

    @Test
    public void selenifiedDownload() {
        App app = this.apps.get();
        app.newElement(Locator.XPATH, "//*[@id='header']/div[2]").click();
        app.newElement(Locator.LINKTEXT, "Selenified").click();
        app.newElement(Locator.NAME, "FirstName").type("Max");
        app.newElement(Locator.NAME, "LastName").type("Saperstone");
        app.newElement(Locator.NAME, "email").type("max.saperstone@coveros.com");
        app.newElement(Locator.NAME, "Company").type("Coveros");
        app.newElement(Locator.XPATH, "//form/p/input").click();
        Element element = app.newElement(Locator.XPATH, "//form/div[2]");
        element.waitForState().displayed();
        element.assertEquals().text("Thank you for your interest in Selenified. Your download should have started.");
	finish();
    }
}
