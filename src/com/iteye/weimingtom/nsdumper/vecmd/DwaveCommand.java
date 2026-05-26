package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class DwaveCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "dwave");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] dwave");
		
		ns.error("dwave");
	}
}
