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

    @When("I click the update category modal for category {string}")
    public void i_click_the_update_category_modal_for_category(String categoryId) {Runner.categoryPOM.clickUpdateCategoryModal(categoryId);}

    @When("I input {string} in the update category name input")
    public void i_input_category_name_in_the_update_category_name_input(String categoryName) {Runner.categoryPOM.setUpdateCategoryName(categoryName);}

    @When("I click the update category button")
    public void i_click_the_update_category_button(){Runner.categoryPOM.clickUpdateCategoryButton();}

    @When("I click the delete category modal for category {string}")
    public void i_click_the_delete_category_modal_for_category(String categoryId) {Runner.categoryPOM.clickDeleteCategoryModal(categoryId);}

    @When("I click the delete category button")
    public void i_click_the_delete_category_button(){Runner.categoryPOM.clickDeleteCategoryButton();}
}