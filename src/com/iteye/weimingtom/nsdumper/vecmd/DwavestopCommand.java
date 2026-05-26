package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class DwavestopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "dwavestop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] dwavestop");
		
		ns.error("dwavestop");
	}
}
