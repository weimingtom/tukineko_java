package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class StopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("stop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] stop");
		
		ns.error("stop");
	}
}
