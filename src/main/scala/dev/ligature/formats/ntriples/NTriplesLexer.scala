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
  case WhiteSpace()
  case Comment()
}

object NTriplesLexer {
  def read(in: String): Iterator[NTriplesToken] = read(in.iterator)

  def read(in: Iterator[Char]): Iterator[NTriplesToken] = {
    val walker = Walker(in)
    val res = scala.collection.mutable.ListBuffer[NTriplesToken]()
    walker.consume //eat first character

    while (walker.current.isDefined) {
      val char = walker.current.get
      val token = char match {
        case '<'        => iri(walker)
        case '^'        => typeSymbol(walker)
        case '@'        => langTag(walker)
        case '"'        => literal(walker)
        case '_'        => blankNodeLabel(walker)
        case '.'        => endOfStatement(walker)
        case '\n'       => endOfLine(walker)
        case '\t' | ' ' => whiteSpace(walker)
        case '#'        => comment(walker)
        case _          => throw RuntimeException(s"Error: $char") //TODO include line + space info
      }
      if (!token.isInstanceOf[NTriplesToken.WhiteSpace] && !token.isInstanceOf[NTriplesToken.Comment]) {
        res += token
      }
    }

    res.iterator
  }

  private def iri(walker: Walker): NTriplesToken.IRI = {
    val sb = StringBuilder()
    if (!walker.hasNext) {
      throw RuntimeException("Invalid IRI.")
    }
    while (walker.hasNext && walker.next != '>') {
      sb.append(walker.current.get)
    }
    if (walker.current.isDefined && walker.current.get == '>') {
      walker.consume
      NTriplesToken.IRI(sb.toString)
    } else {
      throw RuntimeException("Invalid IRI.")
    }
  }

  private def typeSymbol(walker: Walker): NTriplesToken.TypeSymbol = {
    if (walker.hasNext && walker.next == '^') {
      walker.consume
      return NTriplesToken.TypeSymbol()
    }
    else {
      throw RuntimeException(s"Invalid type symbol @ ${walker.line},${walker.space}")
    }
  }

  private def langTag(walker: Walker): NTriplesToken.LangTag = {
    val sb = StringBuilder()
    if (!walker.hasNext) {
      throw RuntimeException("Invalid lang tag.")
    }
    while (walker.hasNext && (walker.next.isLetter || walker.current.get == '-')) {
      sb.append(walker.current.get)
    }
    if (!walker.hasNext) {
      walker.consume
    }
    if (!sb.isEmpty) {
      NTriplesToken.LangTag(sb.toString)
    } else {
      throw RuntimeException("Invalid lang tag.")
    }
  }
  
  private def literal(walker: Walker): NTriplesToken.Literal = {
    val sb = StringBuilder()
    if (!walker.hasNext) {
      throw RuntimeException("Invalid literal.")
    }
    while (walker.hasNext && walker.next != '"') {
      sb.append(walker.current.get)
    }
    if (walker.current.isDefined && walker.current.get == '"') {
      walker.consume
      NTriplesToken.Literal(sb.toString)
    } else {
      throw RuntimeException("Invalid literal.??")
    }
  }
  
  private def blankNodeLabel(walker: Walker): NTriplesToken.BlankNodeLabel = {
    val sb = StringBuilder()
    if (!walker.hasNext) {
      throw RuntimeException("Invalid blank node label.")
    }
    if (walker.next != ':') {
      throw RuntimeException("Invalid blank node label.")
    }
    if (!walker.hasNext) {
      throw RuntimeException("Invalid blank node label.")
    }
    while (walker.hasNext && (walker.next.isLetter)) {
      sb.append(walker.current.get)
    }
    if (!sb.isEmpty) {
      walker.consume
      NTriplesToken.BlankNodeLabel(sb.toString)
    } else {
      throw RuntimeException("Invalid blank node label.")
    }
  }

  private def endOfStatement(walker: Walker): NTriplesToken.EndOfStatement = {
    walker.consume
    NTriplesToken.EndOfStatement()
  }
  
  private def endOfLine(walker: Walker): NTriplesToken.EndOfLine = {
    walker.consume
    NTriplesToken.EndOfLine()
  }

  private def whiteSpace(walker: Walker): NTriplesToken.WhiteSpace = {
    while (walker.hasNext) {
      val next = walker.next
      if (next != ' ' || next != '\t') {
        return NTriplesToken.WhiteSpace()
      }
    }
    walker.consume
    return NTriplesToken.WhiteSpace()
  }

  def comment(walker: Walker): NTriplesToken.Comment = {
    while (walker.hasNext) {
      val next = walker.next
      if (next == '\n') {
        return NTriplesToken.Comment()
      }
    }
    walker.consume
    return NTriplesToken.Comment()
  }
}
