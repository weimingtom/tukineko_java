package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class ArcCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "arc");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] arc");
		
		ns.tn.initSar(ns.path + "ARC.SAR");
	}
}
