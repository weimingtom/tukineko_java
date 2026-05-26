package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class TextclearCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("textclear");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] textclear");
		
		ns.error("textclear");
	}
}
