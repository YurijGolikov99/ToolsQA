package selenide.api_module.utils;

import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.core.ThrowingRunnable;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.Callable;

//методы для реализации механизма ожидания с использованием библиотеки Awaitility
@Slf4j
@Component
public class WaitingUtils {

    //проверка наличия определенного состояния
    public void waitUntil(long timeout, long pollInterval, String failMessage, Callable<Boolean> untilAction) {
        try {
            Awaitility.await()
                    .atMost(Duration.ofSeconds(timeout))
                    .pollInterval(Duration.ofSeconds(pollInterval))
                    .until(untilAction);
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(failMessage);
        }
    }

    //проверка выполнения определенного условия
    public void waitUntilAssert(long timeout, long pollInterval, String failMessage, ThrowingRunnable assertion) {
        try {
            Awaitility.await()
                    .atMost(Duration.ofSeconds(timeout))
                    .pollInterval(Duration.ofSeconds(pollInterval))
                    .untilAsserted(assertion);
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(failMessage);
        }
    }

}