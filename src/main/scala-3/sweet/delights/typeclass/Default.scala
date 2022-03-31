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

import java.time.{
  Duration,
  Instant,
  LocalDate,
  LocalDateTime,
  LocalTime,
  MonthDay,
  OffsetDateTime,
  OffsetTime,
  Period,
  Year,
  YearMonth,
  ZoneId,
  ZoneOffset,
  ZonedDateTime
}

import shapeless3.deriving.*

/**
  * Default[T] is a typeclass that constructs a default value for `T`.
  *
  * @tparam T product type to instanciate
  */
trait Default[T] {

  def get: T

}

object Default {

  lazy val defaultString = ""

  lazy val defaultChar = '\u0000'

  lazy val defaultDouble = 0.0d

  lazy val defaultFloat = 0.0f

  lazy val defaultLong = 0L

  lazy val defaultInt = 0

  lazy val defaultShort = 0.toShort

  lazy val defaultByte = 0.toByte

  lazy val defaultBoolean = false

  lazy val defaultDuration = Duration.ZERO

  lazy val defaultInstant = Instant.EPOCH

  lazy val defaultLocalDate = LocalDate.ofEpochDay(0)

  lazy val defaultLocalTime = LocalTime.ofSecondOfDay(0)

  lazy val defaultLocalDateTime = LocalDateTime.of(defaultLocalDate, defaultLocalTime)

  lazy val defaultMonthDay = MonthDay.of(defaultLocalDate.getMonthValue, defaultLocalDate.getDayOfMonth)

  lazy val defaultZoneOffset = ZoneOffset.UTC

  lazy val defaultZoneId: ZoneId = defaultZoneOffset

  lazy val defaultOffsetDateTime = OffsetDateTime.of(defaultLocalDateTime, defaultZoneOffset)

  lazy val defaultOffsetTime = OffsetTime.of(defaultLocalTime, defaultZoneOffset)

  lazy val defaultPeriod = Period.ZERO

  lazy val defaultYear = Year.of(defaultLocalDate.getYear)

  lazy val defaultYearMonth = YearMonth.of(defaultLocalDate.getYear, defaultLocalDate.getMonth)

  lazy val defaultZonedDateTime = ZonedDateTime.of(defaultLocalDate, defaultLocalTime, defaultZoneId)

  lazy val defaultClass = classOf[Any]

  given stringTypeclass: Default[String] with
    def get: String = defaultString

  given optionTypeclass[T](using inst: Default[T]): Default[Option[T]] with
    def get: Option[T] = None

  given listTypeclass[T](using inst: Default[T]): Default[List[T]] with
    def get: List[T] = Nil

  given charTypeclass: Default[Char] with
    def get: Char = defaultChar

  given doubleTypeclass: Default[Double] with
    def get: Double = defaultDouble

  given floatTypeclass: Default[Float] with
    def get: Float = defaultFloat

  given longTypeclass: Default[Long] with
    def get: Long = defaultLong

  given intTypeclass: Default[Int] with
    def get: Int = defaultInt

  given shortTypeclass: Default[Short] with
    def get: Short = defaultShort

  given byteTypeclass: Default[Byte] with
    def get: Byte = defaultByte

  given booleanTypeclass: Default[Boolean] with
    def get: Boolean = defaultBoolean

  given durationTypeclass: Default[Duration] with
    def get: Duration = defaultDuration

  given instantTypeclass: Default[Instant] with
    def get: Instant = defaultInstant

  given localDateTypeclass: Default[LocalDate] with
    def get: LocalDate = defaultLocalDate

  given localTimeTypeclass: Default[LocalTime] with
    def get: LocalTime = defaultLocalTime

  given localDateTimeTypeclass: Default[LocalDateTime] with
    def get: LocalDateTime = defaultLocalDateTime

  given monthDayTypeclass: Default[MonthDay] with
    def get: MonthDay = defaultMonthDay

  given zoneOffsetTypeclass: Default[ZoneOffset] with
    def get: ZoneOffset = defaultZoneOffset

  given zoneIdTypeclass: Default[ZoneId] with
    def get: ZoneId = defaultZoneId

  given offsetDateTimeTypeclass: Default[OffsetDateTime] with
    def get: OffsetDateTime = defaultOffsetDateTime

  given offsetTimeTypeclass: Default[OffsetTime] with
    def get: OffsetTime = defaultOffsetTime

  given periodTypeclass: Default[Period] with
    def get: Period = defaultPeriod

  given yearTypeclass: Default[Year] with
    def get: Year = defaultYear

  given yearMonthTypeclass: Default[YearMonth] with
    def get: YearMonth = defaultYearMonth

  given zonedDateTimeTypeclass: Default[ZonedDateTime] with
    def get: ZonedDateTime = defaultZonedDateTime

  given defaultGen[T](using inst: K0.ProductInstances[Default, T]): Default[T] with
    def get: T = inst.construct([t] => (mt: Default[t]) => mt.get)

  inline def derived[T](using gen: K0.ProductGeneric[T]): Default[T] = defaultGen

  def apply[T](using inst: Default[T]): T = inst.get
}
