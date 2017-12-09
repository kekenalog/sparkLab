class Pair[T: Ordering](val first: T, val second: T) {
  def smaller(implicit ord: Ordering[T]) =
    if (ord.compare(first, second) < 0) first else second
}

val v1 = new Pair[Int](40, 2).smaller

println(s"40和2进行比较, $v1 更加小")


class Pair1[T: Ordering](val first: T, val second: T) {
  def smaller =
    if (implicitly[Ordering[T]].compare(first, second) < 0) first else second
}

val v2 = new Pair1(50, 3).smaller

println(s"50和3进行比较, $v2 更加小")


class Pair3[T : Ordering](val first: T, val second: T) {
  def smaller = {
    import Ordered._
    if (first < second) first else second
  }
}

val v3 = new Pair3(60, 4).smaller

println(s"60和4进行比较, $v3 更加小")


import java.awt.Point

// No ordering available
//val v4 = new Pair(new Point(3, 4), new Point(2, 5)).smaller

//println(v4)

implicit object PointOrdering extends Ordering[Point] {

  def compare(a: Point, b: Point) =
    a.x * a.x + a.y * a.y - b.x * b.x + b.y * b.y
}

// Now there is an ordering available
//implicitly[Ordering[Point]]
//implicit PointOrdering._
val v5 = new Pair(new Point(3, 3), new Point(7, 5)).smaller
println(v5)
// Namely this one

//implicitly[Ordering[Point]]