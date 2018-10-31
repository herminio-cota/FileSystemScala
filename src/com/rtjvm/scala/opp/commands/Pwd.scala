package com.rtjvm.scala.opp.commands

import com.rtjvm.scala.oop.filesystem.State

class Pwd extends Command {
  
  override def apply(state: State): State = 
    state.setMessage(state.wd.path)
  
  
}