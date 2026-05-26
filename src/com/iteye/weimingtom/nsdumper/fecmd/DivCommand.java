package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class DivCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "div");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] div");
		
		ns.error("div");
	}
}
