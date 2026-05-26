package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class LookbackbuttonCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "lookbackbutton");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] lookbackbutton");
		
		// FIXME:
		 System.err.println("not implement: lookbackbutton");
		//ns.error("not implement: lookbackbutton");
		//
	}
}
