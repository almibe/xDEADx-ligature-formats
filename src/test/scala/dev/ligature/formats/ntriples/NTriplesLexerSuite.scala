/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature.Statement
import dev.ligature.formats.Common._
import dev.ligature.formats.ntriples._
import munit.FunSuite

class NTriplesLexerSuite extends FunSuite {
  private def readSingleToken(in: String): NTriplesToken = {
    val res = NTriplesLexer.read(in).toList
    assertEquals(res.size, 1)
    res.head
  }

  test("support basic tokens") {
    assertEquals(readSingleToken("\"Hello, World\""), NTriplesToken.Literal("Hello, World"))
    assertEquals(readSingleToken("\"Hello,\nWorld\""), NTriplesToken.Literal("Hello,\nWorld"))
    assertEquals(readSingleToken("\"5\""), NTriplesToken.Literal("5"))
    assertEquals(readSingleToken("<urn:isbn:978-1-93435-645-6>"), NTriplesToken.IRI("urn:isbn:978-1-93435-645-6"))
    assertEquals(readSingleToken("<https://github.com/scala/scala>"), NTriplesToken.IRI("https://github.com/scala/scala"))
    assertEquals(readSingleToken("^^"), NTriplesToken.TypeSymbol())
    assertEquals(readSingleToken("."), NTriplesToken.EndOfStatement())
    assertEquals(readSingleToken("@jp"), NTriplesToken.LangTag("jp"))
    assertEquals(readSingleToken("_:bn"), NTriplesToken.BlankNodeLabel("bn"))
    assertEquals(readSingleToken("\n"), NTriplesToken.EndOfLine())
  }

  test("handle write space with single token") {
    assertEquals(readSingleToken("  \"Hello, World\""), NTriplesToken.Literal("Hello, World"))
    assertEquals(readSingleToken("  \"Hello,\nWorld\"  "), NTriplesToken.Literal("Hello,\nWorld"))
    assertEquals(readSingleToken("\t\"5\"\t"), NTriplesToken.Literal("5"))
    assertEquals(readSingleToken(" \t  <urn:isbn:978-1-93435-645-6>"), NTriplesToken.IRI("urn:isbn:978-1-93435-645-6"))
    assertEquals(readSingleToken("<https://github.com/scala/scala>   \t   "), NTriplesToken.IRI("https://github.com/scala/scala"))
    assertEquals(readSingleToken("  ^^   \t"), NTriplesToken.TypeSymbol())
    assertEquals(readSingleToken("  \t .   "), NTriplesToken.EndOfStatement())
    assertEquals(readSingleToken("  @jp   \t"), NTriplesToken.LangTag("jp"))
    assertEquals(readSingleToken("\t\t  \t_:bn \t "), NTriplesToken.BlankNodeLabel("bn"))
    assertEquals(readSingleToken("\t\t  \n  \t"), NTriplesToken.EndOfLine())
  }

  test("handle comments with single token") {
    assertEquals(NTripplesLexer.read("  #this is a comment").toList, List())
    assertEquals(NTripplesLexer.read("#this is a comment   \n# so is this\n     #and this").toList, List())
    assertEquals(readSingleToken("\"Hello, World\" # test"), NTriplesToken.Literal("Hello, World"))
    assertEquals(readSingleToken("\"Hello,\nWorld#\" # test"), NTriplesToken.Literal("Hello,\nWorld#"))
    assertEquals(readSingleToken("\"5\" # test"), NTriplesToken.Literal("5"))
    assertEquals(readSingleToken("<urn:isbn:978-1-93435-645-6>#test"), NTriplesToken.IRI("urn:isbn:978-1-93435-645-6"))
    assertEquals(readSingleToken("<https://github.com/scala/scala> # test"), NTriplesToken.IRI("https://github.com/scala/scala"))
    assertEquals(readSingleToken("^^ # test"), NTriplesToken.TypeSymbol())
    assertEquals(readSingleToken(". # test"), NTriplesToken.EndOfStatement())
    assertEquals(readSingleToken("@jp # test"), NTriplesToken.LangTag("jp"))
    assertEquals(readSingleToken("_:bn # test"), NTriplesToken.BlankNodeLabel("bn"))
    assertEquals(readSingleToken("\n # test"), NTriplesToken.EndOfLine())    
  }

  test("read multiple tokens") {
    val example = "\"text\"@en^^<https://ligature.dev> \"hello\"^^@el-x-koine\t \n\n #comment\n_:stuff"

    val res = NTriplesLexer.read(example).toList

    assertEquals(res, List(
      NTriplesToken.Literal("text"),
      NTriplesToken.LangTag("en"),
      NTriplesToken.TypeSymbol(),
      NTriplesToken.IRI("https://ligature.dev"),
      NTriplesToken.Literal("hello"),
      NTriplesToken.TypeSymbol(),
      NTriplesToken.LangTag("el-x-koine"),
      NTriplesToken.EndOfLine(),
      NTriplesToken.EndOfLine(),
      NTriplesToken.EndOfLine(),
      NTriplesToken.BlankNodeLabel("stuff")
    ))
  }
}
