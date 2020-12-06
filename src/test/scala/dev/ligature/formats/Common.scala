/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats

import cats.effect.IO
import dev.ligature.iris.IRI
import monix.reactive.Observable

import scala.io.Source

object Common {
  val stringIRI: IRI = IRI("http://www.w3.org/2001/XMLSchema#string").getOrElse(???)
  val spiderMan: IRI = IRI("http://example.org/#spiderman").getOrElse(???)
  val greenGoblin: IRI = IRI("http://example.org/#green-goblin").getOrElse(???)
  val blackCat: IRI = IRI("http://example.org/#black-cat").getOrElse(???)
  val enemyOf: IRI = IRI("http://www.perceive.net/schemas/relationship/enemyOf").getOrElse(???)
  val thatSeventiesShow: IRI = IRI("http://example.org/show/218").getOrElse(???)
  val helium: IRI = IRI("http://en.wikipedia.org/wiki/Helium").getOrElse(???)
  val label: IRI = IRI("http://www.w3.org/2000/01/rdf-schema#label").getOrElse(???)
  val localName: IRI = IRI("http://example.org/show/localName").getOrElse(???)

  def readText(resourcePath: String): Iterator[Char] = {
      Source.fromResource(resourcePath).iterator
  }
}
