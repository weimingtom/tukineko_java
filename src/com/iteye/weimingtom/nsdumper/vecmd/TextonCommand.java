package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class TextonCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("texton");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] texton");
		
		ns.error("texton");
	}
}
