package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class PlayCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "play");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] play");
		
		// FIMXE:
		System.err.println("not implement: play");
		//ns.error("play");
		//		
	}
}
