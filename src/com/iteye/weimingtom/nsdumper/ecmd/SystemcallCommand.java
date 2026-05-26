package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class SystemcallCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "systemcall");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] systemcall");
		
		ns.error("systemcall");
	}
}
