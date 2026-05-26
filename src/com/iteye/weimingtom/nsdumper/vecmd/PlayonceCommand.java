package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class PlayonceCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("playonce");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] playonce");
		
		ns.error("playonce");
	}
}
