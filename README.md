[![Build Status](https://travis-ci.com/sweet-delights/delightful-typeclasses.svg?branch=master)](https://travis-ci.com/sweet-delights/delightful-typeclasses)
[![Maven Central](https://img.shields.io/maven-central/v/org.sweet-delights/delightful-typeclasses_2.13.svg)](https://maven-badges.herokuapp.com/maven-central/org.sweet-delights/delightful-typeclasses_2.13)

`delightful-typeclasses` is Scala a library containing utility typeclasses.

This library is built for Scala 2.12.12 and 2.13.3

### SBT
```scala
libraryDependencies += "org.sweet-delights" %% "delightful-typeclasses" % "0.0.1"
```

### Maven
```xml
<dependency>
  <groupId>org.sweet-delights</groupId>
  <artifactId>delightful-typeclasses_2.12</artifactId>
  <version>0.0.1</version>
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
import sweet.delights.typeclass.Default._

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
