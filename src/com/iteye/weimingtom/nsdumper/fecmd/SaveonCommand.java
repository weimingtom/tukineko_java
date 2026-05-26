package com.iteye.weimingtom.nsdumper.fecmd;

public class SaveonCommand extends FECommand {
	@Override
	public boolean check(String str) {
		return checkCommand(str, "saveon");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] saveon");
		
		System.err.println("not implement: saveon");
	}
}
