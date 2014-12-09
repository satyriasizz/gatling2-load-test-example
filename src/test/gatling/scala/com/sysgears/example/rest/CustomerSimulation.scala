package com.sysgears.example.rest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import net.liftweb.json.Serialization
import com.sysgears.example.rest.data.CustomerTestData._
import scala.util.Random
import com.sysgears.example.rest.data.CustomerConversions._
import com.sysgears.example.rest.SimulationConfig._

/**
 * Load test for the rest service.
 */
class CustomerSimulation extends io.gatling.core.scenario.Simulation {

  /**
   * http configuration.
   */
  val httpProtocol = http.baseURL(baseURL)

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
      if (Random.nextBoolean()) s else ""
    }

    val customer = customers(Random.nextInt(customers.size))
    val builder = new StringBuilder("?")
    builder.append(?+(s"firstName=${customer.firstName}"))
    builder.append(?+(s"lastName=${customer.lastName}"))
    builder.append(?+(s"birthday=${dateFormat.format(customer.birthday.get)}"))

    builder.toString()
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
    .assertions(global.successfulRequests.percent.is(100)) // Check the test result.
}