package com.cristhian.camacho.command;

interface Command {
	 
	public void execute();
	 
	public void undo();
	 
	public void redo();
	 
}