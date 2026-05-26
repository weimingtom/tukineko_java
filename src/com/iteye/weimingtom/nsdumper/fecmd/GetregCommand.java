package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class GetregCommand extends FECommand {
	NScripter ns = NScripter.getInstance();		
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "getreg");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] getreg");
		
		ns.error("getreg");
	}
}
