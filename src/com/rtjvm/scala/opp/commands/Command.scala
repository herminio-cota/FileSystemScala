package com.rtjvm.scala.opp.commands

import com.rtjvm.scala.oop.filesystem.State

trait Command {
  
  def apply(state: State): State
  
}

object Command {
 
  def from(input: String): Command =
    new UnknownCommand

}