package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class Btnwait2Command extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "btnwait2");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] btnwait2");
		
		ns.error("btnwait2");
	}
}
