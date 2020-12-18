/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.formats.common

/**
  * TokenWalker is a simple class that wraps an Iterator[T].
  *
  * @param itr
  */
final class TokenWalker[T](private val itr: Iterator[T]) {
  private var _current: Option[T] = None
  def current: Option[T] = _current
  private def current_= (newCurrent: Option[T]): Unit = this._current = newCurrent

  def hasNext: Boolean = itr.hasNext

  def next: T = {
    consume
    current match {
      case Some(t) => t
      case None    => throw RuntimeException("Illegal call to next.")
    }
  }

  def consume: Unit = {
    if (itr.hasNext) {
      val next = itr.next
      current = Some(next)
    } else {
      current = None
    }    
  }
}
