def smaller[T](a: T, b: T)(implicit order: T => Ordered[T])
= if (order(a) < b) a else b

val v = smaller(40, 2)

println(v)

val v2 = smaller("Hello", "World")

println(v2)

def smaller1[T](a: T, b: T)(implicit order: T => Ordered[T])
= if (a < b) a else b // Can omit call to order

val v3 = smaller1(50,3)

println(v3)

val v4 = smaller1("Hello1","World1")

println(v4)

