package com.rtjvm.scala.opp.commands

import com.rtjvm.scala.opp.files.File
import com.rtjvm.scala.oop.filesystem.State
import com.rtjvm.scala.opp.files.DirEntry

class Touch(name: String) extends CreateEntry(name){
  
  override def createSpesificEntry(state: State): DirEntry = 
    File.empty(state.wd.path, name)
  
  
}