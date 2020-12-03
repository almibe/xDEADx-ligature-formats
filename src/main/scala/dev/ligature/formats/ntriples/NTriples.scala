/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import cats.effect.IO
import dev.ligature.Statement
import dev.ligature.iris.IRI
import monix.reactive.Observable

object NTriples {
  def parseNTriples(in: Observable[String]): Observable[Statement] = {
    in.map(parseLine)
  }

  def parseLine(in: String): Statement = {
    Statement(IRI("http://example.org/#spiderman").getOrElse(???), 
      IRI("http://www.perceive.net/schemas/relationship/enemyOf").getOrElse(???), 
      IRI("http://example.org/#green-goblin").getOrElse(???))
  }
}
