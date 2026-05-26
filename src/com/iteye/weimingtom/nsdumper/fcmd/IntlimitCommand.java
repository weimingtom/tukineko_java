package com.iteye.weimingtom.nsdumper.fcmd;

import app.boya.tukineko.parser.NScripter;

public class IntlimitCommand extends FCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "intlimit");
	}
	
	@Override
	public void execute() {
		debug("[FCommand] intlimit");
		
		ns.error("intlimit");
	}
}
