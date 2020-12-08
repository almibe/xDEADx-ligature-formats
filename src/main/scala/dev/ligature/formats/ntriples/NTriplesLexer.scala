/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature.formats.common.Walker

enum NTriplesToken { //TODO probably add parameters to this type to track line and space number
  case IRI(value: String)
  case BlankNodeLabel(value: String)
  case Literal(value: String)
  case TypeSymbol()
  case LangTag(value: String)
  case EndOfStatement()
  case EndOfLine()
}

object NTriplesLexer {
  def read(in: String): Iterator[NTriplesToken] = read(in.iterator)

  def read(in: Iterator[Char]): Iterator[NTriplesToken] = {
    val walker = Walker(in)
    val res = scala.collection.mutable.ListBuffer[NTriplesToken]()

    while (walker.hasNext) {
      val char = walker.next
      val token = char match {
        case '<'  => iri(walker)
        case '^'  => typeSymbol(walker)
        case '@'  => langTag(walker)
        case '"'  => literal(walker)
        case '_'  => blankNodeLabel(walker)
        case '.'  => endOfStatement(walker)
        case '\n' => endOfLine(walker)
        case _    => throw RuntimeException(s"Error") //TODO include line + space info
      }
      res += token
    }

    res.iterator
  }

  private def iri(walker: Walker): NTriplesToken.IRI = {
    val sb = StringBuilder()
    while (walker.hasNext) {
      val next = walker.next
      next match {
        case '>' => return NTriplesToken.IRI(sb.toString)
        case _   => sb.append(next)
      }
    }
    ??? //TODO: probably throw parser exception
  }

  private def typeSymbol(walker: Walker): NTriplesToken.TypeSymbol = ???
  private def langTag(walker: Walker): NTriplesToken.LangTag = ???
  private def literal(walker: Walker): NTriplesToken.Literal = ???
  private def blankNodeLabel(walker: Walker): NTriplesToken.BlankNodeLabel = ???
  private def endOfStatement(walker: Walker): NTriplesToken.EndOfStatement = ???
  private def endOfLine(walker: Walker): NTriplesToken.EndOfLine = ???
}
