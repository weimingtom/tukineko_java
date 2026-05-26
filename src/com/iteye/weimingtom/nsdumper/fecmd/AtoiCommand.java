package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class AtoiCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "atoi");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] atoi");
		
		ns.error("atoi");
	}
}
