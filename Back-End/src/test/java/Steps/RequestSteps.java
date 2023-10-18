package Steps;

import Runner.Runner;
import io.cucumber.java.en.When;

public class RequestSteps {

    @When("I click the manage requests button")
    public void i_click_the_manage_requests_button() {Runner.requestPOM.clickManageRequestsButton();}

    @When("I click the create request modal")
    public void i_click_the_create_request_modal() {Runner.requestPOM.clickAddRequestModal();}

    @When("I select {string} from the create request category input")
    public void i_select_category_from_the_create_request_category_input(String categoryId) {Runner.requestPOM.setAddRequestCategoryDropdown(categoryId);}

    @When("I input {string} in the create request comment input")
    public void i_input_comment_in_the_create_request_comment_input(String comment) {Runner.requestPOM.setAddRequestComment(comment);}

    @When("I input {double} in the create request amount input")
    public void i_input_amount_in_the_create_request_amount_input(double amount) {Runner.requestPOM.setAddRequestAmount(amount);}

    @When("I click the create request button")
    public void i_click_the_create_request_button() {Runner.requestPOM.clickAddRequestButton();}
    
}
