package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class NegaCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("nega");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] nega");
		
		ns.error("nega");
	}
}
