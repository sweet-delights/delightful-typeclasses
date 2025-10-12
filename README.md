[![Build status](https://github.com/sweet-delights/delightful-cron/actions/workflows/scala.yml/badge.svg)](https://github.com/sweet-delights/delightful-cron/actions/workflows/scala.yml)
[![Maven Central](https://img.shields.io/maven-central/v/org.sweet-delights/delightful-typeclasses_2.13.svg)](https://maven-badges.herokuapp.com/maven-central/org.sweet-delights/delightful-typeclasses_2.13)

`delightful-typeclasses` is Scala a library containing utility typeclasses.

This library is built for Scala 2.12.15, 2.13.8 and 3.1.2

### SBT
```scala
libraryDependencies += "org.sweet-delights" %% "delightful-typeclasses" % "0.2.0"
```

### Maven
```xml
<dependency>
  <groupId>org.sweet-delights</groupId>
  <artifactId>delightful-typeclasses_2.12</artifactId>
  <version>0.2.0</version>
</dependency>
```

## [License](LICENSE.md)

All files in `delightful-typeclasses` are under the GNU Lesser General Public License version 3.
Please read files [`COPYING`](COPYING) and [`COPYING.LESSER`](COPYING.LESSER) for details.

## Available typeclasses

### `Default[T]`

The `Default[T]` typeclass creates a default instance of `T`.

*Step 1*: define case classes

```scala
import java.time.LocalDateTime

case class Foo(
  opt: Option[Int],
  str: String,
  double: Double,
  more: List[Bar]
)

case class Bar(
  list: List[LocalDateTime]
)
```

*Step 2*: instantiate!

```scala


val default = Default[Foo]
println(default)
// Foo(
//   opt = Some(0),
//   str = "",
//   double = "0.0",
//   List(
//     Bar(List(LocalDateTime.parse("1970-01-01T00:00:00.000")))
//   )
// )
```

## Acknowledgments

- the [`shapeless`](https://github.com/milessabin/shapeless) library
- the [The Type Astronaut's Guide to Shapeless](https://underscore.io/books/shapeless-guide/) book
- the [Scala 3](https://docs.scala-lang.org/scala3/reference/contextual/derivation.html) documentation