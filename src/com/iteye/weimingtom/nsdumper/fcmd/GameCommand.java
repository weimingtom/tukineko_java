package com.iteye.weimingtom.nsdumper.fcmd;

import app.boya.tukineko.parser.NScripter;

public class GameCommand extends FCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("game");
	}
	
	@Override
	public void execute() {
		debug("[FCommand] game");
		
		ns.setMsRest();
		ns.tn.makemenu(ns.nd.savenumber, ns.path, ns.nd.savenameTitle);
		try {
			ns.gotoLabel("*start");
		} catch (Exception e) {
			ns.error("game");
		}		
	}
}
