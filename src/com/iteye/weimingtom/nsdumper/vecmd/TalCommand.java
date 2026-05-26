package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class TalCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("tal");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] tal");
		
		ns.error("tal");
	}
}
