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
    val res = NTriplesLexer.read("\"Hello, World\"").toList
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

  // test("handle write space with single token") {
  //   ???
  // }

  // test("handle comments with single token") {
  //   ???
  // }

  // test("read multiple tokens") {
  //   ???
  // }

  // test("handle new lines") {
  //   ???
  // }
}
