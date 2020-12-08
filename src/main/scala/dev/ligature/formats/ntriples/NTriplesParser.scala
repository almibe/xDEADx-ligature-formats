/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import cats.effect.IO
import dev.ligature.{ Statement, BlankNode, Literal, Subject, Object }
import dev.ligature.formats.common.Walker
import dev.ligature.iris.IRI

object NTriples {
  def parseNTriples(in: Iterator[Char]): Iterator[Statement] = {
    val walker = Walker(in)
    if (walker.hasNext) {
      val next = walker.next
      next match {
        case '#' => Iterator.empty
        case '<' => Iterator(statement(walker))
        case _        => throw RuntimeException(s"Error on line: $in")
      }
    } else {
      Iterator.empty
    }
  }

  def statement(walker: Walker): Statement = {
    val subject = iri(walker)
    val predicate = iri(walker)
    val `object` = iri(walker)
    Statement(subject, predicate, `object`)
  }

  def subject(walker: Walker): Subject = ???
  def `object`(walker: Walker): Object = ???
  def iri(walker: Walker): IRI = {
    val sb = StringBuilder()
    while (walker.hasNext) {
      val next = walker.next
      next match {
        case '>' => return IRI(sb.toString).getOrElse(throw RuntimeException(s"Couldn't make IRI from ${sb.toString}"))
        case _        => sb.append(next)
      }
    }
    ??? //TODO: probably throw parser exception
  }
  def blankNode(walker: Walker): BlankNode = ???
  def literal(walker: Walker): Literal = ???
}
