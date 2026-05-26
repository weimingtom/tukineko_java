package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class ItoaCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "itoa");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] itoa");
		
		ns.error("itoa");
	}
}
