package com.iteye.weimingtom.nsdumper.sfecmd;

public class CaptionCommand extends SFECommand {
	@Override
	public boolean check(String str) {
		return checkCommand(str, "caption");
	}
	
	@Override
	public void execute() {
		debug("[SFECommand] caption");
		
		System.err.println("not implement: caption");
	}
}
