package org.lab.spark.language.scala.ch21.sec03

import scala.math._

/**
  * Created by wy on 12/9/17.
  */
class Fraction(n: Int, d: Int) {

  val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)

  val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString: String = num + "/" + den

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
}

object Fraction {

  def apply(n: Int, d: Int): Fraction = new Fraction(n, d)
}

package com.horstmann.impatient {

  object FractionConversions {

    implicit def int2Fraction(n: Int) = Fraction(n, 1)

    implicit def fraction2Double(f: Fraction) = f.num * 1.0 / f.den
  }

}

object Main extends App {

//  import com.horstmann.impatient.FractionConversions._ //引入所以的方法
  import com.horstmann.impatient.FractionConversions.fraction2Double //只引入 fraction2Double 函数
//  import com.horstmann.impatient.FractionConversions.{fraction2Double=> _,_} //表示引入除fraction2Double外的所有成员


  val result = 3 * Fraction(4, 5)

  println(result)
}