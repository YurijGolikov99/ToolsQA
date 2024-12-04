package selenide.common_module.utils;

import lombok.SneakyThrows;

//Методы, связанные с выполнением тестов и очисткой после выполнения
public class TestExecutionUtils {

    public void testWithCleaner(Runnable test, Runnable cleanTask) {
        try {
            test.run();
        } finally {
            if (cleanTask != null) {
                cleanTask.run();
            }
        }
    }

    @SneakyThrows
    public void testWithCleanerWithExceptions(Runnable test, Runnable cleanTask) {
        Throwable exceptions = null;
        try {
            test.run();
        } catch (Throwable e) {
            exceptions = e;
        } finally {
            try {
                cleanTask.run();
            } catch (Throwable e) {
                if (exceptions == null) {
                    exceptions = e;
                }
                exceptions.addSuppressed(e);
            } finally {
                if (exceptions != null) {
                    throw exceptions;
                }
            }
        }
    }
}
