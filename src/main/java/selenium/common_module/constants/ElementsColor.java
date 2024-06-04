package selenium.common_module.constants;

public enum ElementsColor {
    BLUE_DEGREE("#007bff");

    private String color;

    ElementsColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
