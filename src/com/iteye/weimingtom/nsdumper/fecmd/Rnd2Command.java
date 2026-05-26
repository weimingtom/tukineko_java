package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class Rnd2Command extends FECommand {
	NScripter ns = NScripter.getInstance();	
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "rnd2");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] rnd2");
		
		ns.error("rnd2");
	}
}
