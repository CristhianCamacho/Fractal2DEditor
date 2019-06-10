package com.cc.fractal2d_editor.command;

interface Command {
	 
	public void execute();
	 
	public void undo();
	 
	public void redo();
	 
}