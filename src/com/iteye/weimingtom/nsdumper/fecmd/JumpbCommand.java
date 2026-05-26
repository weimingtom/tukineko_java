package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class JumpbCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "jumpb");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] jumpb");
		
		try {
			ns.setFilePointer(ns.nd.jumpBack);
		} catch (Exception e) {

		}
		ns.nd.historyPos = 0;
		ns.nd.historyCount = 0;
		ns.lineRest = null;		
	}
}
