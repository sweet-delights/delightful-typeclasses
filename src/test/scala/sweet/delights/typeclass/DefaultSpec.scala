// This file is part of delightful-typeclasses.
//
// delightful-typeclasses is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.
package sweet.delights.typeclass

import java.time.LocalDateTime

import org.specs2.mutable.Specification

class DefaultSpec extends Specification {

  case class Foo(
    opt: Option[Int],
    str: String,
    double: Double,
    bar: Bar
  )

  case class Bar(
    list: List[LocalDateTime]
  )

  "Default[T]" should {
    "create a default value of T" in {
      val expected = Foo(
        opt = None,
        str = Default.defaultString,
        double = Default.defaultDouble,
        bar = Bar(list = Nil)
      )
      Default[Foo] mustEqual expected
    }
  }
}
