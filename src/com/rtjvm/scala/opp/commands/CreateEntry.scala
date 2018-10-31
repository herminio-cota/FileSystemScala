package com.rtjvm.scala.opp.commands

import com.rtjvm.scala.oop.filesystem.State
import com.rtjvm.scala.opp.files.Directory
import com.rtjvm.scala.opp.files.DirEntry

abstract class CreateEntry(entryName: String) extends Command {
    
  override def apply(state: State): State = {
      val wd = state.wd
      if (wd.hasEntry(entryName)) {
        state.setMessage("Entry " + entryName + " already exists!")
      } else if (entryName.contains(Directory.SEPARATOR)) {
        state.setMessage(entryName + " must not contain separators!")
      } else if (checkIllegal(entryName)) {
        state.setMessage(entryName + ": illegal entry name!")
      } else {
        doCreateEntry(state, entryName)
      } 
  }
  
  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }
  
  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }
    
    val wd = state.wd
    
    val allDirsInPath = wd.getAllFoldersInPath
    
    val newEntry: DirEntry = createSpesificEntry(state)
    
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    
    val newWd = newRoot.findDescendant(allDirsInPath)
    
    State(newRoot, newWd)
  }
  
  def createSpesificEntry(state: State): DirEntry
  
}