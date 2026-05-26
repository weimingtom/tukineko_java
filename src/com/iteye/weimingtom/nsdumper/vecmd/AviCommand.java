package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class AviCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "avi");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] avi");
		
		ns.error("avi");
	}
}
