package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class WaitCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "wait");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] wait");
		
		ns.error("wait");
	}
}
