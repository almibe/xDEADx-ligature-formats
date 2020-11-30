/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats

import cats.effect.IO
import dev.ligature.NamedNode
import fs2.Stream

import scala.io.Source

object Common {
  val stringNamedNode: NamedNode = NamedNode("http://www.w3.org/2001/XMLSchema#string")
  val spiderMan: NamedNode = NamedNode("http://example.org/#spiderman")
  val greenGoblin: NamedNode = NamedNode("http://example.org/#green-goblin")
  val blackCat: NamedNode = NamedNode("http://example.org/#black-cat")
  val enemyOf: NamedNode = NamedNode("http://www.perceive.net/schemas/relationship/enemyOf")
  val thatSeventiesShow: NamedNode = NamedNode("http://example.org/show/218")
  val helium: NamedNode = NamedNode("http://en.wikipedia.org/wiki/Helium")
  val label: NamedNode = NamedNode("http://www.w3.org/2000/01/rdf-schema#label")
  val localName: NamedNode = NamedNode("http://example.org/show/localName")

  def readText(resourcePath: String): Stream[IO, String] = {
      Stream.fromIterator[IO](Source.fromResource(resourcePath).getLines())
  }
}
