package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestExecutionListener implements IInvokedMethodListener {

    private void delayByLoop(int milliseconds) {
        try {
            Thread.sleep(milliseconds);  // Recommended over busy-loop
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            System.out.println("Delaying before: " + method.getTestMethod().getMethodName());
            delayByLoop(3000); // Delay before every test step
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            delayByLoop(3000); // Delay after every test step
            System.out.println("Delayed after: " + method.getTestMethod().getMethodName());
        }
    }
}