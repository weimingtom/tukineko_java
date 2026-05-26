package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class TrapCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "trap");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] trap");
		
		ns.error("trap");
	}
}
