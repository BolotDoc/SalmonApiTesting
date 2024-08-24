package ApiTest;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class AllureReportExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        log.info("Starting Test Method '{}'", getTestMethodName(extensionContext));
        Allure.step("Starting Test Method "+getTestMethodName(extensionContext));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        log.info("Finished Test Method '{}'", getTestMethodName(extensionContext));
        Allure.step("Finished Test Method "+getTestMethodName(extensionContext));
    }

    private static String getTestMethodName(ExtensionContext extensionContext) {
        return extensionContext.getTestMethod()
                .map(method -> method.getName())
                .orElse("unknown");
    }
}
