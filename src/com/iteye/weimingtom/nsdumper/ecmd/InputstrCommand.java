package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class InputstrCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "inputstr");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] inputstr");
		
		ns.error("inputstr");
	}
}
