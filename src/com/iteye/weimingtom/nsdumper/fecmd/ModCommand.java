package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class ModCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mod");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] mod");
		
		ns.error("mod");
	}
}
