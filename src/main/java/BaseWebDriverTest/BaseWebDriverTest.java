package BaseWebDriverTest;


	import com.applause.auto.framework.pageframework.synchronization.AjaxHelper;
	import com.applause.auto.framework.pageframework.util.drivers.WebDriverWrapper;
	import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
	import com.applause.auto.framework.test.listeners.TestListener;
	import org.apache.log4j.Logger;
	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Listeners;

	@Listeners({TestListener.class})
	public class BaseWebDriverTest {
	    protected static WebDriver driver = null;
	    protected static AjaxHelper syncHelper = new AjaxHelper(WebDriverWrapper.getWebDriver());
	    protected static EnvironmentUtil env = EnvironmentUtil.getInstance();
	    protected static Logger logger = Logger.getLogger(BaseWebDriverTest.class);

	    public BaseWebDriverTest() {
	    }

	    @AfterClass(
	        alwaysRun = true
	    )
	    public static void suiteTearDown() {
	        if(!env.getIsRemoteTestRun()) {
	            WebDriverWrapper.closeBrowser(driver);
	        }

	    }

	    @AfterMethod(
	        alwaysRun = true
	    )
	    public void testTearDown() {
	        logger.warn("Test Complete");
	    }

	    static {
	        logger.info("calling get WebDriver()");
	        driver = WebDriverWrapper.getWebDriver();
	    }
	}

