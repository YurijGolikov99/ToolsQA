package selenide.common_module.utils;

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
}
