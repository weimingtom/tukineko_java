package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class ResetCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "reset");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] reset");
		
		ns.error("reset");
	}
}
