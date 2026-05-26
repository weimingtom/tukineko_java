package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class GetiniCommand extends FECommand {
	NScripter ns = NScripter.getInstance();		
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "getini");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] getini");
		
		ns.error("getini");
	}
}
