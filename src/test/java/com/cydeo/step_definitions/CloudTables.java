package com.cydeo.step_definitions;

import com.cydeo.pages.CloudTablePage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CloudTables {

    CloudTablePage cloudTablePage = new CloudTablePage();
    public String firstName;
    public String lastName;

    @Given("User is on cloudTables homepage")
    public void user_is_on_cloud_tables_homepage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("cloudtables.url"));
    }
    @When("User clicks on New button")
    public void user_clicks_on_new_button() {
        cloudTablePage.newPerson.click();
    }
    @When("User enters {string} to firstname field")
    public void user_enters_to_firstname_field(String string) {
        cloudTablePage.firstName.sendKeys(string);
        firstName=string;

    }
    @When("User enters {string} to lastname field")
    public void user_enters_to_lastname_field(String string) {
         cloudTablePage.lastName.sendKeys(string);
         lastName=string;
    }
    @When("User enters {string} to position field")
    public void user_enters_to_position_field(String string) {
        cloudTablePage.positionBox.sendKeys(string);
    }
    @When("User enters {string} to salary field")
    public void user_enters_to_salary_field(String string) {
        cloudTablePage.salary.sendKeys(string);
    }
    @When("User clicks on create button")
    public void user_clicks_on_create_button() {
     cloudTablePage.createButton.click();

    }
    @Then("User should be able to find person at the search box")
    public void user_should_be_able_to_find_person_at_the_search_box() {


    cloudTablePage.searchBox.sendKeys(firstName+" "+lastName);
    String expectedTitle=firstName+" "+lastName;

    Assert.assertTrue(cloudTablePage.person.getText().equals(expectedTitle));


    }

    @And("User deletes the person created")
    public void userDeletesThePersonCreated() {
        cloudTablePage.person.click();
        cloudTablePage.deletePerson.click();
        cloudTablePage.confirmDelete.click();
    }

    @Then("User should be able to find person at the search box {string}")
    public void userShouldBeAbleToFindPersonAtTheSearchBox(String arg0) {
        try{
            if(Driver.getDriver().findElement(By.xpath("//td[@class='sorting_1']")).isDisplayed())
                System.out.println("Element is present and displayed");
            else
                System.out.println("Element is present but not displayed");
        }catch (NoSuchElementException e) {
            System.out.println("Element is not present, hence not displayed as well");
        }


    }
}