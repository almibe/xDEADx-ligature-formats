/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import munit.FunSuite

class NTriplesSpec extends FunSuite {
  test("support basic IRI triple") {
    //         {
    //            val result = parser.loadNTriples(readText("/ntriples/01-basicTriple.nt"))
    //
    //            result.contains(Quad(spiderMan, enemyOf, greenGoblin)) shouldBe true
    //            result.size shouldBe 1
    //        }
    ???
  }

  test("support multiple IRI triples") {
    //         {
    //            val result = parser.loadNTriples(readText("/ntriples/02-multipleIRITriples.nt"))
    //
    //            result.contains(Quad(spiderMan, enemyOf, greenGoblin)) shouldBe true
    //            result.contains(Quad(spiderMan, enemyOf, blackCat)) shouldBe true
    //            result.size shouldBe 2
    //        }
    ???
  }

  test("support beginning of line and end of line comments") {
    ???
    //         {
    //            val result = parser.loadNTriples(readText("/ntriples/03-comments.nt"))
    //
    //            result.contains(Quad(spiderMan, enemyOf, greenGoblin)) shouldBe true
    //            result.size shouldBe 1
    //        }
  }

  test("support literals with languages and types") {
    ???
    //         {
    //            val result = parser.loadNTriples(readText("/ntriples/04-literals.nt"))
    //
    //            result.containsAll(
    //                setOf(
    //                    Quad(thatSeventiesShow, label, TypedLiteral("That Seventies Show", stringIRI)),
    //                    Quad(thatSeventiesShow, localName, LangLiteral("That Seventies Show", "en")),
    //                    Quad(thatSeventiesShow, localName, LangLiteral("Cette Série des Années Septante", "fr-be")),
    //                    Quad(spiderMan, IRI("http://example.org/text"), TypedLiteral("This is a multi-line\\nliteral with many quotes (\\\"\\\"\\\"\\\"\\\")\\nand two apostrophes ('').", stringIRI)),
    //                    Quad(helium, IRI("http://example.org/elements/atomicNumber"), TypedLiteral("2", IRI("http://www.w3.org/2001/XMLSchema#integer"))),
    //                    Quad(helium, IRI("http://example.org/elements/specificGravity"), TypedLiteral("1.663E-4", IRI("http://www.w3.org/2001/XMLSchema#double")))
    //                )
    //            )
    //            result.size shouldBe 6
    //        }
  }

  test("support blank nodes") {
    ???
    //         {
    //            val result = parser.loadNTriples(readText("/ntriples/05-blankNodes.nt"))
    //
    //            result.contains(Quad(BlankNode ("bob"), IRI ("http://xmlns.com/foaf/0.1/knows"), BlankNode ("alice"))) shouldBe true
    //            result.contains(Quad(BlankNode ("alice"), IRI ("http://xmlns.com/foaf/0.1/knows"), BlankNode ("bob"))) shouldBe true
    //            result.size shouldBe 2
    //        }
    //    }
  }
}
