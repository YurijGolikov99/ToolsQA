package selenide.common_module.constants;

public enum ElementsHexColor {
    BLUE_DEGREE("#007bff");

    private String color;

    ElementsHexColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
