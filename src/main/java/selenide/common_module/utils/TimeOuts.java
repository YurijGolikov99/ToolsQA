package selenide.common_module.utils;

public enum TimeOuts {
    PAGE_LOAD_TIMEOUT(30),
    ELEMENT_TIMEOUT(5),
    MINIMAL_ELEMENT_TIMEOUT(1),
    POLLING(100);

    private final int timeOut;

    TimeOuts(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeOut() {
        return timeOut;
    }

}
