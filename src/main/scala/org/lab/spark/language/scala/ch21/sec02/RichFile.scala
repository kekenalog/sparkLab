package org.lab.spark.language.scala.ch21.sec02

import java.io.File

import scala.io.Source

/**
  * 扩展读取文件方法的read方法
  * Created by wy on 12/9/17.
  */
class RichFile(val from: File) {

  def read = Source.fromFile(from.getPath).mkString
}


object Main extends App {

  implicit def file2RichFile(from: File) = new RichFile(from)

  val contents = new File("/Users/wy/xcript/cy").read

  println(contents)
}