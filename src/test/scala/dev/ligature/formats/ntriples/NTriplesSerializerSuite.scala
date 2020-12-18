/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.ntriples

import dev.ligature._
import dev.ligature.iris.IRI
import dev.ligature.formats.Common._
import dev.ligature.formats.ntriples._
import munit.FunSuite

class NTriplesSerializerSuite extends FunSuite {
  test("support serializing a single statement") {
    val statement = Statement(IRI("http://localhost").getOrElse(???), IRI("http://localhost").getOrElse(???), IRI("http://localhost").getOrElse(???))
    val ps = PersistedStatement(LocalName("test"), statement, DefaultGraph)
    val res = NTriplesSerializer.serialize(List(ps).iterator).mkString
    val expected = "<http://localhost> <http://localhost> <http://localhost> .\n"
    assertEquals(res, expected)
  }  
}
