/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.common

final class Walker(private val itr: Iterator[Char]) {
  private var _line: Long = 0L
  private var _space: Int = 0
  private var _current: Option[Char] = None

  def hasNext: Boolean = itr.hasNext

  def next: Char = {
    if (itr.hasNext) {
      val next = itr.next
      current = Some(next)
      next
    } else {
      current = None
      itr.next //TODO: Maybe handle this differently?
    }
  }

  def line: Long = _line
  def space: Int = _space
  def current: Option[Char] = _current

  private def line_= (newLine: Long): Unit = this._line = newLine
  private def space_= (newSpace: Int): Unit = this._space = newSpace
  private def current_= (newCurrent: Option[Char]): Unit = this._current = newCurrent
}
