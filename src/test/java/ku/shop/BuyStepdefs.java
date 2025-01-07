package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }



    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws NotEnoughProductException{
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @When("I buy too many {string} with quantity {int}")
    public void i_buy_too_many_quantity(String name, int quantity) throws NotEnoughProductException{
        Product prod = catalog.getProduct(name);
        assertThrows(NotEnoughProductException  .class, () -> order.addItem(prod, quantity));
    }

    @When("stock have {int} {string}")
    public void stock_have(int quantity, String name) {
        Product prod = catalog.getProduct(name);
        int stock = prod.getStock();
    }



    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("stock will have {int} {string}")
    public void stock_will_have(int quantity, String name) {
        Product prod = catalog.getProduct(name);
        assertEquals(quantity, prod.getStock());
    }
}

