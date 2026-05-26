package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class PuttextCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("puttext");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] puttext");
		
		ns.error("puttext");
	}
}
