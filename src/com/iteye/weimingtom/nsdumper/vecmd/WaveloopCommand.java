package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class WaveloopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("waveloop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] waveloop");
		
		ns.error("waveloop");
	}
}
