/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import cats.effect.IO
import cats.parse.{Parser1, Parser => P}
import dev.ligature.{NamedNode, Statement}
import fs2.Stream

object NTriples {
//  private[this] val whitespace: Parser1[Unit] = P.charIn(" \t\r\n").void
//  private[this] val whitespaces0: P[Unit] = whitespace.rep.void

  private val iriStart = P.char('<').void
  private val iriEnd = P.char('>').void
  private val iriContent = P.charsWhile(c => dev.ligature.Ligature.v).map(NamedNode)
  private val iri = (iriStart ~ iriContent ~ iriEnd).map(_._1._2)
  private val statement = (iri ~ iri ~ iri).map(s => Statement(s._1._1, s._1._2, s._2))

  def parseNTriples(in: Stream[IO, String]): Stream[IO, Statement] = {
    in.map { line =>
      statement.parse(line) match {
        case Left(value) => throw new RuntimeException(s"Error parsing $line\n$value")
        case Right(value) => value._2
      }
    }
  }
}
