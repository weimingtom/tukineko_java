package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class BgaliaCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "bgalia");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] bgalia");
	
		ns.error("bgalia");
	}
}
