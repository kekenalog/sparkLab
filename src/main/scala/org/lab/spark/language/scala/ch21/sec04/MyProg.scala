package org.lab.spark.language.scala.ch21.sec04

import scala.math._

class Fraction(n: Int, d: Int) {
  val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
  val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);

  override def toString = num + "/" + den

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
}

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}

object FractionConversions {
  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  implicit def fraction2Double(f: Fraction) = f.num * 1.0 / f.den

  implicit def fraction2String(f: Fraction) = f.toString
}

object Main extends App {

  import FractionConversions._

  println(sqrt(Fraction(1, 4))) // calls fraction2Double
  println(Fraction(1, 4).replace("/", "÷")) // calls fraction2Float
  println(3 * Fraction(4, 5)) // Calls int2Fraction(3)
}

/**
使用 scalac -Xprint:typer MyProg.scala

package org.lab.spark.language.scala.ch21.sec04 {
  import scala.math._;
  class Fraction extends scala.AnyRef {
    <paramaccessor> private[this] val n: Int = _;
    <paramaccessor> private[this] val d: Int = _;
    def <init>(n: Int, d: Int): org.lab.spark.language.scala.ch21.sec04.Fraction = {
      Fraction.super.<init>();
      ()
    };
    private[this] val num: Int = if (Fraction.this.d.==(0))
      1
    else
      Fraction.this.n.*(Fraction.this.sign(Fraction.this.d))./(Fraction.this.gcd(Fraction.this.n, Fraction.this.d));
    <stable> <accessor> def num: Int = Fraction.this.num;
    private[this] val den: Int = if (Fraction.this.d.==(0))
      0
    else
      Fraction.this.d.*(Fraction.this.sign(Fraction.this.d))./(Fraction.this.gcd(Fraction.this.n, Fraction.this.d));
    <stable> <accessor> def den: Int = Fraction.this.den;
    override def toString: String = Fraction.this.num.+("/").+(Fraction.this.den);
    def sign(a: Int): Int = if (a.>(0))
      1
    else
      if (a.<(0))
        -1
      else
        0;
    def gcd(a: Int, b: Int): Int = if (b.==(0))
      scala.math.`package`.abs(a)
    else
      Fraction.this.gcd(b, a.%(b));
    def *(other: org.lab.spark.language.scala.ch21.sec04.Fraction): org.lab.spark.language.scala.ch21.sec04.Fraction = new Fraction(Fraction.this.num.*(other.num), Fraction.this.den.*(other.den))
  };
  object Fraction extends scala.AnyRef {
    def <init>(): org.lab.spark.language.scala.ch21.sec04.Fraction.type = {
      Fraction.super.<init>();
      ()
    };
    def apply(n: Int, d: Int): org.lab.spark.language.scala.ch21.sec04.Fraction = new Fraction(n, d)
  };
  object FractionConversions extends scala.AnyRef {
    def <init>(): org.lab.spark.language.scala.ch21.sec04.FractionConversions.type = {
      FractionConversions.super.<init>();
      ()
    };
    implicit def int2Fraction(n: Int): org.lab.spark.language.scala.ch21.sec04.Fraction = Fraction.apply(n, 1);
    implicit def fraction2Double(f: org.lab.spark.language.scala.ch21.sec04.Fraction): Double = f.num.*(1.0)./(f.den);
    implicit def fraction2String(f: org.lab.spark.language.scala.ch21.sec04.Fraction): String = f.toString()
  };
  object Main extends AnyRef with App {
    def <init>(): org.lab.spark.language.scala.ch21.sec04.Main.type = {
      Main.super.<init>();
      ()
    };
    import FractionConversions._;
    scala.this.Predef.println(scala.math.`package`.sqrt(FractionConversions.fraction2Double(Fraction.apply(1, 4))));
    scala.this.Predef.println(FractionConversions.fraction2String(Fraction.apply(1, 4)).replace("/", "÷"));
    scala.this.Predef.println(3.*(FractionConversions.fraction2Double(Fraction.apply(4, 5))))
  }
}
**/