package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class MulCommand extends FECommand {
	NScripter ns = NScripter.getInstance();	
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mul");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] mul");
		
		ns.error("mul");
	}
}
