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
    ???
  }

  test("handle comments with single token") {
    ???
  }

  test("read multiple tokens") {
    ???
  }

  test("handle new lines") {
    ???
  }

  // test("support multiple IRI triples") {
  //   val result = NTriples.parseNTriples(readText("ntriples/02-multipleIRITriples.nt")).toSet
  //   assert(result.contains(Statement(spiderMan, enemyOf, greenGoblin)))
  //   assert(result.contains(Statement(spiderMan, enemyOf, blackCat)))
  //   assertEquals(result.size, 2)
  // }

  // test("support beginning of line and end of line comments") {
  //   val result = NTriples.parseNTriples(readText("ntriples/03-comments.nt")).toSet
  //   assert(result.contains(Statement(spiderMan, enemyOf, greenGoblin)))
  //   assertEquals(result.size, 1)
  // }

//  test("support literals with languages and types") {
//    val result = NTriples.parseNTriples(readText("/ntriples/04-literals.nt")).compile.toList.unsafeRunSync()
//    result.contains(
//        setOf(
//            Statement(thatSeventiesShow, label, TypedLiteral("That Seventies Show", stringIRI)),
//            Statement(thatSeventiesShow, localName, LangLiteral("That Seventies Show", "en")),
//            Statement(thatSeventiesShow, localName, LangLiteral("Cette Série des Années Septante", "fr-be")),
//            Statement(spiderMan, IRI("http://example.org/text"), TypedLiteral("This is a multi-line\\nliteral with many quotes (\\\"\\\"\\\"\\\"\\\")\\nand two apostrophes ('').", stringIRI)),
//            Statement(helium, IRI("http://example.org/elements/atomicNumber"), TypedLiteral("2", IRI("http://www.w3.org/2001/XMLSchema#integer"))),
//            Statement(helium, IRI("http://example.org/elements/specificGravity"), TypedLiteral("1.663E-4", IRI("http://www.w3.org/2001/XMLSchema#double")))
//        )
//    )
//    result.size shouldBe 6
//  }
//
//  test("support blank nodes") {
//    val result = NTriples.parseNTriples(readText("/ntriples/05-blankNodes.nt")).compile.toList.unsafeRunSync()
//    result.contains(Statement(BlankNode ("bob"), IRI ("http://xmlns.com/foaf/0.1/knows"), BlankNode ("alice"))) shouldBe true
//    result.contains(Statement(BlankNode ("alice"), IRI ("http://xmlns.com/foaf/0.1/knows"), BlankNode ("bob"))) shouldBe true
//    result.size shouldBe 2
//  }
}
