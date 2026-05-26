package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class PlaystopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("playstop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] playstop");
		
		ns.setMsRest();
		//
		// FIXME:
		System.err.println("not implement: playstop");
		//ns.error("playstop");
		//		
	}
}
