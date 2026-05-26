package com.iteye.weimingtom.nsdumper.fecmd;

public class SaveoffCommand extends FECommand {
	@Override
	public boolean check(String str) {
		return checkCommand(str, "saveoff");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] saveoff");
		
		System.err.println("not implement: saveoff");
	}
}
