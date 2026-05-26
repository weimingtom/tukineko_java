package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class RmodeCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "rmode");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] rmode");
		
		ns.error("rmode");
	}
}
