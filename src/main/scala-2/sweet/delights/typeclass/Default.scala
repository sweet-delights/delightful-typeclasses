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

import shapeless.{:+:, ::, CNil, Coproduct, Generic, HList, HNil, Lazy}

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

  implicit lazy val stringTypeclass: Default[String] = create(defaultString)

  implicit def optionTypeclass[T](implicit instance: Default[T]): Default[Option[T]] = create(None)

  implicit def listTypeclass[T](implicit instance: Default[T]): Default[List[T]] = create(Nil)

  implicit lazy val charTypeclass: Default[Char] = create(defaultChar)

  implicit lazy val doubleTypeclass: Default[Double] = create(defaultDouble)

  implicit lazy val floatTypeclass: Default[Float] = create(defaultFloat)

  implicit lazy val longTypeclass: Default[Long] = create(defaultLong)

  implicit lazy val intTypeclass: Default[Int] = create(defaultInt)

  implicit lazy val shortTypeclass: Default[Short] = create(defaultShort)

  implicit lazy val byteTypeclass: Default[Byte] = create(defaultByte)

  implicit lazy val booleanTypeclass: Default[Boolean] = create(defaultBoolean)

  implicit lazy val durationTypeclass: Default[Duration] = create(defaultDuration)

  implicit lazy val instantTypeclass: Default[Instant] = create(defaultInstant)

  implicit lazy val localDateTypeclass: Default[LocalDate] = create(defaultLocalDate)

  implicit lazy val localTimeTypeclass: Default[LocalTime] = create(defaultLocalTime)

  implicit lazy val localDateTimeTypeclass: Default[LocalDateTime] = create(defaultLocalDateTime)

  implicit lazy val monthDayTypeclass: Default[MonthDay] = create(defaultMonthDay)

  implicit lazy val zoneOffsetTypeclass: Default[ZoneOffset] = create(defaultZoneOffset)

  implicit lazy val zoneIdTypeclass: Default[ZoneId] = create(defaultZoneId)

  implicit lazy val offsetDateTimeTypeclass: Default[OffsetDateTime] = create(defaultOffsetDateTime)

  implicit lazy val offsetTimeTypeclass: Default[OffsetTime] = create(defaultOffsetTime)

  implicit lazy val periodTypeclass: Default[Period] = create(defaultPeriod)

  implicit lazy val yearTypeclass: Default[Year] = create(defaultYear)

  implicit lazy val yearMonthTypeclass: Default[YearMonth] = create(defaultYearMonth)

  implicit lazy val zonedDateTimeTypeclass: Default[ZonedDateTime] = create(defaultZonedDateTime)

  implicit lazy val classTypeclass: Default[Class[_]] = create(defaultClass)

  implicit lazy val cnilTypeclass: Default[CNil] = create(???)

  implicit def coproductTypeclass[L, R <: Coproduct](
    implicit
    left: Lazy[Default[L]],
    right: Default[R]
  ): Default[L :+: R] = create(???)

  implicit lazy val hnilTypeclass: Default[HNil] = create(HNil)

  implicit def hlistTypeclass[H, T <: HList](
    implicit
    head: Lazy[Default[H]],
    tail: Default[T]
  ): Default[H :: T] = create(head.value.get :: tail.get)

  implicit def genericTypeclass[T, HL <: HList](
    implicit
    gen: Generic.Aux[T, HL],
    default: Lazy[Default[HL]]
  ): Default[T] = create(gen.from(default.value.get))

  def apply[T: Default]: T = implicitly[Default[T]].get

  def create[T](func: => T): Default[T] = new Default[T] {
    override def get: T = func
  }

}
