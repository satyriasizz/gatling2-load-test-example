package com.sysgears.example.rest.data

import spray.http.{HttpCharsets, HttpEntity}
import net.liftweb.json.{DateFormat, Formats, Serialization}
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Customer entity.
 *
 * @param id        unique id
 * @param firstName first name
 * @param lastName  last name
 * @param birthday  date of birth
 */
case class Customer(id: Option[Long], firstName: String, lastName: String, birthday: Option[java.util.Date])

object CustomerConversions {

  implicit val liftJsonFormats = new Formats {
    val dateFormat = new DateFormat {
      val sdf = new SimpleDateFormat("yyyy-MM-dd")

      def parse(s: String): Option[Date] = try {
        Some(sdf.parse(s))
      } catch {
        case e: Exception => None
      }

      def format(d: Date): String = sdf.format(d)
    }
  }

  implicit def HttpEntityToCustomer(httpEntity: HttpEntity) = Serialization.read[Customer](httpEntity.asString(HttpCharsets.`UTF-8`))
}