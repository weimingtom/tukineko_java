package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class TextspeedCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "textspeed");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] textspeed");
		
		ns.error("textspeed");
	}
}
