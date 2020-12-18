/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature.Statement
import dev.ligature.formats.Common._
import munit.FunSuite

class NTriplesParserSuite extends FunSuite {
  test("support basic IRI triple") {
    val result = NTriplesParser.read(readText("ntriples/01-basicTriple.nt")).toSet
    assert(result.contains(Statement(spiderMan, enemyOf, greenGoblin)))
    assertEquals(result.size, 1)
  }

  test("support multiple IRI triples") {
    val result = NTriplesParser.read(readText("ntriples/02-multipleIRITriples.nt")).toSet
    assert(result.contains(Statement(spiderMan, enemyOf, greenGoblin)))
    assert(result.contains(Statement(spiderMan, enemyOf, blackCat)))
    assertEquals(result.size, 2)
  }

  test("support beginning of line and end of line comments") {
    val result = NTriplesParser.read(readText("ntriples/03-comments.nt")).toSet
    assert(result.contains(Statement(spiderMan, enemyOf, greenGoblin)))
    assertEquals(result.size, 1)
  }

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
