/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.turtle

import munit.FunSuite

class TurtleSpec extends FunSuite {
}

//import io.kotlintest.shouldBe
//import io.kotlintest.specs.StringSpec
//import org.almibe.ligature.*
//import org.almibe.ligature.formats.enemyOf
//import org.almibe.ligature.formats.greenGoblin
//import org.almibe.ligature.formats.readText
//import org.almibe.ligature.formats.spiderMan
//
//class TurtleSpec : StringSpec() {
//    init {
//        val turtle = Turtle()
//        val xsd = "http://www.w3.org/2001/XMLSchema#"
//        val foafKnows = IRI("http://xmlns.com/foaf/0.1/knows")
//        val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
//        val stringIRI = IRI("http://www.w3.org/2001/XMLSchema#string")
//
//        "support basic IRI triple" {
//            val model = turtle.loadTurtle(readText("/turtle/01-basicTriple.ttl"))
//
//            model.contains(Quad(spiderMan, enemyOf, greenGoblin)) shouldBe true
//            model.size shouldBe 1
//        }
//
//        "support predicate lists" {
//            val model = turtle.loadTurtle(readText("/turtle/02-predicateList.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(spiderMan, enemyOf, greenGoblin),
//                    Quad(spiderMan, IRI("http://xmlns.com/foaf/0.1/name"),
//                            TypedLiteral("Spiderman", IRI("http://www.w3.org/2001/XMLSchema#string")))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "support object lists" {
//            val model = turtle.loadTurtle(readText("/turtle/03-objectList.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(spiderMan, IRI("http://xmlns.com/foaf/0.1/name"),
//                            TypedLiteral("Spiderman", IRI("http://www.w3.org/2001/XMLSchema#string"))),
//                    Quad(spiderMan, IRI("http://xmlns.com/foaf/0.1/name"),
//                            LangLiteral("Человек-паук", "ru"))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "support comments" {
//            val model = turtle.loadTurtle(readText("/turtle/04-comments.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(spiderMan, enemyOf, greenGoblin),
//                    Quad(spiderMan, IRI("http://xmlns.com/foaf/0.1/name"),
//                            TypedLiteral("Spiderman", IRI("http://www.w3.org/2001/XMLSchema#string")))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "support multiline triples" {
//            val model = turtle.loadTurtle(readText("/turtle/05-multilineTriple.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(spiderMan, enemyOf, greenGoblin)
//            )) shouldBe true
//            model.size shouldBe 1
//        }
//
//        val base = "http://one.example/"
//        val base2 = "http://one.example2/"
//        val baseTwo = "http://two.example/"
//        val baseTwo2 = "http://two.example2/"
//        val base3 = "http://another.example/"
//
//        "turtle IRI parsing with base" {
//            val model = turtle.loadTurtle(readText("/turtle/06-baseTriples.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("${base}subject2"), IRI("${base}predicate2"), IRI("${base}object2")),
//                    Quad(IRI("${base2}subject2"), IRI("${base2}predicate2"), IRI("${base2}object2"))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "turtle IRI parsing with prefixes" {
//            val model = turtle.loadTurtle(readText("/turtle/07-prefixTriples.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("${baseTwo}subject3"), IRI("${baseTwo}predicate3"), IRI("${baseTwo}object3")),
//                    Quad(IRI("${baseTwo2}subject3"), IRI("${baseTwo2}predicate3"), IRI("${baseTwo2}object3")),
//                    Quad(IRI("${base2}path/subject4"), IRI("${base2}path/predicate4"), IRI("${base2}path/object4")),
//                    Quad(IRI("${base3}subject5"), IRI("${base3}predicate5"), IRI("${base3}object5")),
//                    Quad(IRI("${base3}subject6"), IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"), IRI("${base3}subject7")),
//                    Quad(IRI("http://伝言.example/?user=أكرم&amp;channel=R%26D"), IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"), IRI("${base3}subject8"))
//            )) shouldBe true
//            model.size shouldBe 6
//        }
//
//        "support language literals" {
//            val model = turtle.loadTurtle(readText("/turtle/08-literalWithLanguage.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("http://example.org/#spiderman"),
//                            IRI("http://xmlns.com/foaf/0.1/name"), LangLiteral("Человек-паук", "ru"))
//            )) shouldBe true
//            model.size shouldBe 1
//        }
//
//        "support quoted literals" {
//            val base = "http://www.libraryweasel.org/fake/madeup#"
//            val show = IRI("http://example.org/vocab/show/218")
//            val show219 = IRI("http://example.org/vocab/show/219")
//            val label = IRI("http://www.w3.org/2000/01/rdf-schema#label")
//            val localName = IRI("http://example.org/vocab/show/localName")
//            val blurb = IRI("http://example.org/vocab/show/blurb")
//            val multilineText = "This is a multi-line\n" +
//                    "literal with many quotes (\"\"\"\"\")\n" +
//                    "and up to two sequential apostrophes ('')."
//            val multilineText2 = "Another\n" +
//                    "multiline string with' 'a' \"custom datatype\"\\\"."
//            val model = turtle.loadTurtle(readText("/turtle/09-quotedLiterals.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(show, label, TypedLiteral("That Seventies Show", stringIRI)),
//                    Quad(show, IRI("${base}pred"), TypedLiteral("That Seventies Show", IRI("${base}string"))),
//                    Quad(show, localName, LangLiteral("That Seventies Show", "en")),
//                    Quad(show, localName, LangLiteral("Cette Série des Années Soixante-dix", "fr")),
//                    Quad(show, localName, LangLiteral("Cette Série des Années Septante", "fr-be")),
//                    Quad(show, blurb, TypedLiteral(multilineText, stringIRI)),
//                    Quad(show219, blurb, TypedLiteral(multilineText2, IRI("${base}long-string"))),
//                    Quad(show219, blurb, TypedLiteral("", stringIRI))
//            )) shouldBe true
//            model.size shouldBe 8
//        }
//
//        "support number types" {
//            val helium = "http://en.wikipedia.org/wiki/Helium"
//            val prefix = "http://example.org/elements"
//            val model = turtle.loadTurtle(readText("/turtle/10-numbers.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI(helium), IRI("${prefix}atomicNumber"), TypedLiteral("2", IRI("${xsd}integer"))),
//                    Quad(IRI(helium), IRI("${prefix}atomicMass"), TypedLiteral("4.002602", IRI("${xsd}float"))),
//                    Quad(IRI(helium), IRI("${prefix}specificGravity"), TypedLiteral("1.663E-4", IRI("${xsd}double")))
//            )) shouldBe true
//            model.size shouldBe 3
//        }
//
//        "support booleans" {
//            val model = turtle.loadTurtle(readText("/turtle/11-booleans.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("http://somecountry.example/census2007"), IRI("http://example.org/stats/isLandlocked"),
//                            TypedLiteral("false", IRI("${xsd}boolean")))
//            )) shouldBe true
//            model.size shouldBe 1
//        }
//
//        "support blank nodes" {
//            val model = turtle.loadTurtle(readText("/turtle/12-blankNodes.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(BlankNode("alice"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("bob")),
//                    Quad(BlankNode("bob"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("alice"))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "unlabeled blank nodes" {
//            val model = turtle.loadTurtle(readText("/turtle/13-unlabeledBlankNodes.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("http://example.com/person/bob"), foafKnows, IRI("http://example.com/person/george")),
//                    Quad(BlankNode("ANON1"), foafKnows, IRI("http://example.com/person/george")),
//                    Quad(IRI("http://example.com/person/bob"), foafKnows, BlankNode("ANON2")),
//                    Quad(BlankNode("ANON3"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("ANON4"))
//            )) shouldBe true
//            model.size shouldBe 4
//        }
//
//        "nested unlabeled blank nodes" {
//            val model = turtle.loadTurtle(readText("/turtle/14-nestedUnlabeledBlankNodes.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(BlankNode("ANON2"), IRI("http://xmlns.com/foaf/0.1/name"), TypedLiteral("Bob", stringIRI)),
//                    Quad(BlankNode("ANON1"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("ANON2"))
//            )) shouldBe true
//            model.size shouldBe 2
//        }
//
//        "complex unlabeled blank nodes" {
//            val model = turtle.loadTurtle(readText("/turtle/15-complexUnlabeledBlankNodes.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(BlankNode("ANON1"), IRI("http://xmlns.com/foaf/0.1/name"), TypedLiteral("Alice", stringIRI)),
//                    Quad(BlankNode("ANON2"), IRI("http://xmlns.com/foaf/0.1/name"), TypedLiteral("Bob", stringIRI)),
//                    Quad(BlankNode("ANON1"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("ANON2")),
//                    Quad(BlankNode("ANON3"), IRI("http://xmlns.com/foaf/0.1/name"), TypedLiteral("Eve", stringIRI)),
//                    Quad(BlankNode("ANON2"), IRI("http://xmlns.com/foaf/0.1/knows"), BlankNode("ANON3")),
//                    Quad(BlankNode("ANON2"), IRI("http://xmlns.com/foaf/0.1/mbox"), IRI("http://bob@example.com"))
//            )) shouldBe true
//            model.size shouldBe 6
//        }
//
//        "support collections" {
//            val model = turtle.loadTurtle(readText("/turtle/16-collections.ttl"))
//
//            model.containsAll(setOf(
//                    Quad(IRI("http://example.org/foo/subject"), IRI("http://example.org/foo/predicate"), BlankNode("ANON1")),
//                    Quad(BlankNode("ANON1"), IRI("${rdf}first"), IRI("http://example.org/foo/a")),
//                    Quad(BlankNode("ANON1"), IRI("${rdf}rest"), BlankNode("ANON2")),
//                    Quad(BlankNode("ANON2"), IRI("${rdf}first"), IRI("http://example.org/foo/b")),
//                    Quad(BlankNode("ANON2"), IRI("${rdf}rest"), BlankNode("ANON3")),
//                    Quad(BlankNode("ANON3"), IRI("${rdf}first"), IRI("http://example.org/foo/c")),
//                    Quad(BlankNode("ANON3"), IRI("${rdf}rest"), IRI("${rdf}nil")),
//                    Quad(IRI("http://example.org/foo/subject"), IRI("http://example.org/foo/predicate2"), IRI("${rdf}nil"))
//            )) shouldBe true
//            model.size shouldBe 8
//        }
//    }
//
//////////
//////////    //TODO examples 19-26 and wordnetStinkpot.ttl
//////////    final def wordnetTest() {
//////////        final def expectedResults = [
//////////                Triple(IRI(""),IRI(""),IRI(""))
//////////        )
//////////        val model = turtle.loadTurtle(readText("/turtle/wordnetStinkpot.ttl"))
//////////        compareModels(model, expectedModel)
//////////    }
////////
////////    final def malformedQuotedLiterals() {
////////        try {
////////            val model = turtle.loadTurtle(readText("/turtle/malformed/09-quotedLiterals.ttl"))
////////        } catch (exception: RuntimeException) {
////////            return
////////        }
////////        throw RuntimeException("Test failed")
////////    }
//}
