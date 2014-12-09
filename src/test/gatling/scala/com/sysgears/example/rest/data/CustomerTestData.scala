package com.sysgears.example.rest.data

import java.text.SimpleDateFormat

object CustomerTestData {

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

  //test data
  val birthday0 = Some(dateFormat.parse("1991-01-14"))
  val firstName0 = "Andrey"
  val lastName0 = "Litvinenko"
  val birthday1 = Some(dateFormat.parse("1987-02-12"))
  val firstName1 = "Corwin"
  val lastName1 = "Holmes"
  val customers = List(
    Customer(None, firstName0, lastName0, birthday0),
    Customer(None, firstName1, lastName1, birthday1))
  val nonExistentCustomerId = customers.size + 1
}
