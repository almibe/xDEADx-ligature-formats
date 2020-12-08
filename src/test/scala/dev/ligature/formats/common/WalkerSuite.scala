/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.common

import munit.FunSuite

class WalkerSuite extends FunSuite {
  test("walker should start at 0,0") {
    val walker = Walker("Hello".iterator)

    assertEquals(walker.current, None)
    assertEquals(walker.line, 0L)
    assertEquals(walker.space, 0)
  }

  test("read one char") {
    val walker = Walker("Hello".iterator)
    walker.next

    assertEquals(walker.current, Some('H'))
    assertEquals(walker.line, 1L)
    assertEquals(walker.space, 1)
  }

  test("read two chars") {
    val walker = Walker("Hello".iterator)
    walker.next
    walker.next

    assertEquals(walker.current, Some('e'))
    assertEquals(walker.line, 1L)
    assertEquals(walker.space, 2)
  }

  test("handle new lines") {
    val walker = Walker("H\nello".iterator)
    walker.next
    walker.next

    assertEquals(walker.current, Some('\n'))
    assertEquals(walker.line, 1L)
    assertEquals(walker.space, 2)

    walker.next

    assertEquals(walker.current, Some('e'))
    assertEquals(walker.line, 2L)
    assertEquals(walker.space, 1)
  }
}
