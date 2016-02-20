package com.sysgears.example.rest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import net.liftweb.json.Serialization
import com.sysgears.example.rest.data.CustomerTestData._
import scala.util.Random

/**
 * Load test for the rest service.
 */
class CustomerSimulation extends io.gatling.core.scenario.Simulation with SimulationConfig {

  /**
   * http configuration.
   */
  val httpProtocol = http.baseURL(baseURL)

  /**
   * Set default formats for json parser.
   */
  implicit val formats = net.liftweb.json.DefaultFormats

  /**
   * Returns a random customer in JSON format.
   *
   * @return customer in JSON format
   */
  def randCustomer = {
    StringBody(Serialization.write(
      customers(Random.nextInt(customers.size))))
  }

  /**
   * Generates a random search query with random params.
   *
   * @return generated query
   */
  def randSearchQuery = {
    def ?+(s: String) = {
      if (Random.nextBoolean()) s + "&" else ""
    }
    val customer = customers(Random.nextInt(customers.size))

    "?" + ?+(s"firstName=${customer.firstName}") +
      ?+(s"lastName=${customer.lastName}") +
      ?+(s"birthday=${dateFormat.format(customer.birthday.get)}")
  }

  /**
   * Scenario for simulation.
   */
  val scn = scenario("Simulation for the customer service").repeat(repeatCount) {
    exec(
      http(session => "Post a customer")
        .post(customerLink)
        .body(randCustomer)
        .check(status is 201)
        .check(jsonPath("$.id").saveAs("id"))
    )
      .exec(
        http(session => "Search customers")
          .get(customerLink + randSearchQuery)
          .check(status is 200)
      )
      .exec(
        http(session => "Get customers")
          .get(customerLink)
          .check(status is 200)
      )
      .exec(
        http(session => "Get customer by id")
          .get("/customer/${id}")
          .check(status is 200)
      )
      .exec(
        http(session => "Put customer by id")
          .put("/customer/${id}")
          .body(randCustomer)
          .check(status is 200)
      )
      .exec(
        http(session => "Delete customer")
          .delete(customerLink + "/${id}")
          .check(status is 200)
      )
  }

  /**
   * Sets the scenario.
   */
  setUp(scn.inject(atOnceUsers(threads)))
    .protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.is(percentSuccess)) // Check the test result.
}