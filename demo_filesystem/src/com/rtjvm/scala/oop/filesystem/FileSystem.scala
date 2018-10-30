package com.rtjvm.scala.oop.filesystem

import java.util.Scanner
import com.rtjvm.scala.opp.files.Directory
import com.rtjvm.scala.opp.commands.Command

object FileSystem extends App{
  
  val root = Directory.ROOT
  var state = State (root, root)
  
  val scanner = new Scanner(System.in) 
  
  while(true){
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
  
}