package Steps;

import Runner.Runner;
import io.cucumber.java.en.When;

public class CategorySteps {

    @When("I click the manage categories button")
    public void i_click_the_manage_categories_button(){Runner.categoryPOM.clickManageCategoriesButton();}

    @When("I click the create category modal")
    public void i_click_the_create_category_modal(){Runner.categoryPOM.clickAddCategoryModal();}

    @When("I input {string} in the create category name input")
    public void i_input_category_name_in_the_create_category_name_input(String categoryName){
        Runner.categoryPOM.setNewCategoryName(categoryName);}

    @When("I click the create category button")
    public void i_click_the_create_category_button(){Runner.categoryPOM.clickAddCategoryButton();}


}