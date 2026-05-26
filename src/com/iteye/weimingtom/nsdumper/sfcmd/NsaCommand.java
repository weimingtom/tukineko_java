package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class NsaCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "nsa");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] nsa");
		
		ns.tn.initNsa(ns.path + "ARC.NSA");
	}
}
