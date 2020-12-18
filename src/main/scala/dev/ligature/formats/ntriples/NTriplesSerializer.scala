/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature._
import dev.ligature.iris.IRI

object NTriplesSerializer {
  def serialize(in: Iterator[PersistedStatement]): Iterator[Char] = {
    val sb = StringBuilder()
    in.foreach { ps =>
      sb.append(statement(ps))
    }
    sb.toString.iterator
  }

  def statement(ps: PersistedStatement): String = {
    val statement = ps.statement
    s"${subject(statement.subject)} ${predicate(statement.predicate)} ${`object`(statement.`object`)} ${graph(ps.graph)}.\n"
  }

  def subject(subject: Subject): String = {
    subject match {
      case i: IRI => iri(i)
      case bn: BlankNode => blankNode(bn)
      case ln: LocalName => localName(ln)
      case de: DefaultGraph.type => defaultGraph()
    }
  }

  def predicate(predicate: Predicate): String = {
    predicate match {
      case i: IRI => iri(i)
      case ln: LocalName => localName(ln)
    }
  }

  def `object`(`object`: Object): String = {
    `object` match {
      case i: IRI => iri(i)
      case bn: BlankNode => blankNode(bn)
      case ln: LocalName => localName(ln)
      case lit: Literal => literal(lit)
      case de: DefaultGraph.type => defaultGraph()
    }
  }

  def graph(graph: Graph): String = {
    graph match {
      case i: IRI => iri(i)
      case bn: BlankNode => blankNode(bn)
      case ln: LocalName => localName(ln)
      case de: DefaultGraph.type => defaultGraph()
    }
  }

  def literal(literal: Literal): String = {
    literal match {
      case _ => ???
    }
  }

  def iri(iri: IRI): String = s"<${iri.value}>"
  def blankNode(bn: BlankNode): String = s"_:${bn.identifier}"
  def localName(ln: LocalName): String = s"`${ln.name}`"
  def defaultGraph(): String = ""  
}
