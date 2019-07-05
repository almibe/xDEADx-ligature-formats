/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.almibe.ligature.formats.nquads

import org.almibe.ligature.*
import org.almibe.ligature.parser.nquads.NQuadsBaseListener
import org.almibe.ligature.parser.nquads.NQuadsLexer
import org.almibe.ligature.parser.nquads.NQuadsParser
import org.almibe.ligature.parser.ntriples.NTriplesParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.Reader

class NQuads {
    fun loadNQuads(reader: Reader): Set<Quad> {
        val stream = CharStreams.fromReader(reader)
        val lexer = NQuadsLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = NQuadsParser(tokens)
        val walker = ParseTreeWalker()
        val listener = NQuadsListener()
        walker.walk(listener, parser.nQuadsDoc())
        return listener.model
    }
}

private class NQuadsListener : NQuadsBaseListener() {
    val model = mutableSetOf<Quad>()
    lateinit var currentQuad: TempQuad
    val blankNodes = HashMap<String, BlankNode>()

    override fun enterTriple(ctx: NTriplesParser.TripleContext) {
        currentQuad = TempQuad()
    }

    override fun exitSubject(ctx: NTriplesParser.SubjectContext) {
        currentQuad.subject = when {
            ctx.IRIREF() != null -> handleIRI(ctx.IRIREF().text)
            ctx.BLANK_NODE_LABEL() != null -> handleBlankNode(ctx.BLANK_NODE_LABEL().text)
            else -> throw RuntimeException("Unexpected Subject Type")
        }
    }

    override fun exitPredicate(ctx: NTriplesParser.PredicateContext) {
        currentQuad.predicate = handleIRI(ctx.IRIREF().text)
    }

    override fun exitObject(ctx: NTriplesParser.ObjectContext) {
        when {
            ctx.IRIREF() != null -> handleObject(handleIRI(ctx.IRIREF().text))
            ctx.BLANK_NODE_LABEL() != null -> handleObject(handleBlankNode(ctx.BLANK_NODE_LABEL().text))
            ctx.literal() != null -> handleLiteral(ctx.literal())
            else -> throw RuntimeException("Unexpected Object Type")
        }
    }

    override fun visitErrorNode(node: ErrorNode) {
        throw RuntimeException(node.toString()) //TODO do I need this or will ANTLR throw its own RTE?
    }

    internal fun handleIRI(iriRef: String): IRI {
        if (iriRef.length > 2) {
            return IRI(iriRef.substring(1, (iriRef.length-1)))
        } else {
            throw RuntimeException("Invalid iriRef - $iriRef")
        }
    }

    internal fun handleLiteral(literal: NTriplesParser.LiteralContext) {
        val value = if (literal.STRING_LITERAL_QUOTE().text.length >= 2) {
            literal.STRING_LITERAL_QUOTE().text.substring(1, literal.STRING_LITERAL_QUOTE().text.length-1)
        } else {
            throw RuntimeException("Invalid literal.")
        }
        val result = when {
            literal.LANGTAG() != null -> LangLiteral(value, literal.LANGTAG().text.substring(1))
            literal.IRIREF() != null -> TypedLiteral(value, handleIRI(literal.IRIREF().text))
            else -> TypedLiteral(value)
        }
        model.addStatement(currentQuad.subject, currentQuad.predicate, result)
    }

    fun handleBlankNode(blankNode: String): BlankNode {
        return if (blankNode.length > 2) {
            val blankNodeLabel = blankNode.substring(2)
            if (blankNodes.containsKey(blankNodeLabel)) {
                blankNodes[blankNodeLabel]!!
            } else {
                val newBlankNode = BlankNode(blankNodeLabel)
                blankNodes[blankNodeLabel] = newBlankNode
                newBlankNode
            }
        } else {
            throw RuntimeException("Invalid blank node label - $blankNode")
        }
    }

    fun handleObject(objectVertex: Object) {
        model.addStatement(currentQuad.subject, currentQuad.predicate, objectVertex)
    }

    internal class TempQuad {
        lateinit var subject: Subject
        lateinit var predicate: Predicate
        lateinit var `object`: Object
    }
}
