package com.sysgears.example.rest.data

import java.text.SimpleDateFormat
import com.sysgears.example.rest.SimulationConfig
import scala.util.Random

object CustomerTestData extends SimulationConfig {

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

  //test data
  val firstNames = getRequiredStringList("data.first_names")
  val lastNames = getRequiredStringList("data.last_names")
  val birthdays = getRequiredStringList("data.birthdays")

  def generateCustomer: Customer = {
    Customer(None,
      firstNames.get(Random.nextInt(firstNames.size())),
      lastNames.get(Random.nextInt(lastNames.size())),
      Some(dateFormat.parse(birthdays.get(Random.nextInt(birthdays.size()))))
    )
  }

  val customers = List.fill(threads)(generateCustomer)

  val nonExistentCustomerId = customers.size + 1
}
