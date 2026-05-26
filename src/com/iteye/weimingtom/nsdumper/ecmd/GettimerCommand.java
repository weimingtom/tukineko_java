package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class GettimerCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "gettimer");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] gettimer");
		
		ns.error("gettimer");
	}
}
