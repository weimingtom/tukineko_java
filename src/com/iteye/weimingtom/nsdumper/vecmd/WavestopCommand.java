package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class WavestopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("wavestop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] wavestop");
		
		ns.error("wavestop");
	}
}
