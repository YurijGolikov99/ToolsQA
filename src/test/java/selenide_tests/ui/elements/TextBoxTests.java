package selenide_tests.ui.elements;

import org.junit.jupiter.api.Test;
import selenide.ui_module.pages.elements.TextBoxPage;
import selenide.ui_module.steps.elements.TextBoxSteps;
import selenide_tests.BaseTest;

public class TextBoxTests extends BaseTest {

    private static TextBoxPage textBoxPage = new TextBoxPage();
    private static TextBoxSteps textBoxSteps = new TextBoxSteps();
    private final String TEXT_BOX_URL = "https://demoqa.com/text-box";

    @Test
    public void fillAllFields(){
        textBoxPage.openPage(TEXT_BOX_URL);
        textBoxSteps.fillFullNameField();
        textBoxSteps.fillEmailField();
        textBoxSteps.fillCurrentAddressField();
        textBoxSteps.dragCurrentAddressField();
        textBoxSteps.fillPermanentAddressField();
    }
}
