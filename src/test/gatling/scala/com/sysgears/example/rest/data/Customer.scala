package com.sysgears.example.rest.data

import java.util.Date

/**
 * Customer entity.
 *
 * @param id        unique id
 * @param firstName first name
 * @param lastName  last name
 * @param birthday  date of birth
 */
case class Customer(id: Option[Long], firstName: String, lastName: String, birthday: Option[Date])
