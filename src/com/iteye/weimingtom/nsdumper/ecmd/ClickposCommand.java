package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class ClickposCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "clickpos");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] clickpos");
		
		ns.error("clickpos");
	}
}
