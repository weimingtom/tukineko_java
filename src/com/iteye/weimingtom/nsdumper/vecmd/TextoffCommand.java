package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class TextoffCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("textoff");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] textoff");
		
		ns.error("textoff");
	}
}
