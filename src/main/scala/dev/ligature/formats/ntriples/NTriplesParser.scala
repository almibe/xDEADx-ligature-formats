/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature.{ Statement, BlankNode, Literal, Subject, Object }
import dev.ligature.iris.IRI

object NTriples {
  def read(in: String): Iterator[Statement] = read(in.iterator)

  def read(in: Iterator[Char]): Iterator[Statement] = {
    val tokens = NTriplesLexer.read(in)
    val res = scala.collection.mutable.ListBuffer[Statement]()
    while (tokens.hasNext) {
      res += statement(tokens)
    }
    res.iterator
  }

  def statement(tokens: Iterator[NTriplesToken]): Statement = {
    val s = subject(tokens)
    val p = iri(tokens)
    val o = `object`(tokens)
    Statement(s, p, o)
  }

  def subject(tokens: Iterator[NTriplesToken]): Subject = ???
  def `object`(tokens: Iterator[NTriplesToken]): Object = ???
  def iri(tokens: Iterator[NTriplesToken]): IRI = ???
}
