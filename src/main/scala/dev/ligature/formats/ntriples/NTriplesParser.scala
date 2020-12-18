/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature.formats.common.TokenWalker
import dev.ligature.{ Statement, BlankNode, Literal, Subject, Object }
import dev.ligature.iris.IRI

object NTriplesParser {
  def read(in: String): Iterator[Statement] = read(in.iterator)

  def read(in: Iterator[Char]): Iterator[Statement] = {
    val tokens = TokenWalker(NTriplesLexer.read(in))
    val res = scala.collection.mutable.ListBuffer[Statement]()
    while (tokens.hasNext) {
      val next = tokens.next
      if (next.isInstanceOf[NTriplesToken.IRI] || next.isInstanceOf[NTriplesToken.BlankNodeLabel]) { //TODO handle LocalNames too
        res += statement(tokens)
      } else if (!next.isInstanceOf[NTriplesToken.EndOfLine]) {
        throw RuntimeException(s"Illegal statement, expecting subject but found $next")
      }
    }
    res.iterator
  }

  def statement(tokens: TokenWalker[NTriplesToken]): Statement = {
    val s: Subject = subject(tokens)
    val p = iri(tokens)
    val o: Object = `object`(tokens)
    endOfStatement(tokens)
    if (tokens.hasNext) {
      endOfLine(tokens)
    }
    Statement(s, p, o)
  }

  def subject(tokens: TokenWalker[NTriplesToken]): Subject = {
    val next = tokens.current.get
    next match {
      case iri: NTriplesToken.IRI            => createIRI(iri)
      case bnl: NTriplesToken.BlankNodeLabel => ???
      case _                                 => throw RuntimeException(s"Unexpected Subject token: $next")
    }
  }

  def `object`(tokens: TokenWalker[NTriplesToken]): Object = {
    if (tokens.hasNext) {
      val next = tokens.next
      next match {
        case iri: NTriplesToken.IRI            => createIRI(iri)
        case bnl: NTriplesToken.BlankNodeLabel => ???
        case lit: NTriplesToken.Literal        => literal()
        case _                                 => throw RuntimeException(s"Unexpected token, not IRI or Blank Node Lable: $next")
      }
    } else {
      throw RuntimeException("Invalid statement.")
    }
  }

  def iri(tokens: TokenWalker[NTriplesToken]): IRI = {
    if (tokens.hasNext) {
      val next = tokens.next
      next match {
        case iri: NTriplesToken.IRI => createIRI(iri)
        case _                      => throw RuntimeException(s"Unexpected Predicate token: $next")
      }
    } else {
      throw RuntimeException("Invalid statement.")
    }
  }

  def endOfStatement(tokens: TokenWalker[NTriplesToken]): Unit = {
    if (tokens.hasNext) {
      val next = tokens.next
      if (!next.isInstanceOf[NTriplesToken.EndOfStatement]) {
        throw RuntimeException(s"Invalid statement, expecting end of statement but got $next.")
      }
    } else {
      throw RuntimeException("Invalid statement.")
    }
  }

  def endOfLine(tokens: TokenWalker[NTriplesToken]): Unit = {
    if (tokens.hasNext) {
      val next = tokens.next
      if (!next.isInstanceOf[NTriplesToken.EndOfLine]) {
        throw RuntimeException(s"Invalid statement, expecting end of line but got $next.")
      }
    } else {
      throw RuntimeException("Invalid statement.")
    }
  }

  def createIRI(token: NTriplesToken.IRI): IRI = {
    val option = IRI(token.value)
    option match {
      case Right(iri) => iri
      case _          => throw RuntimeException(s"Illegal IRI: ${token.value}")
    }
  }

  def literal(): Object = ???
}
