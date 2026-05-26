package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class OfscpyCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "ofscpy");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] ofscpy");
		
		ns.setMsRest();
		System.err.println("not implement: cfscpy");		
	}
}
