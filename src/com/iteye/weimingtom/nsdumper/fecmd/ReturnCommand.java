package com.iteye.weimingtom.nsdumper.fecmd;

import java.io.IOException;

import app.boya.tukineko.parser.NScripter;

public class ReturnCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("return");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] return");
		
		ns.setMsRest();
		ns.nd.historyPos = 0;
		ns.nd.historyCount = 0;
		ns.nd.gosubPos -= 1;
		try {
			ns.setFilePointer(ns.nd.gosub[ns.nd.gosubPos].retpos);
		} catch (IOException e) {
			ns.error("return");
		}
		ns.lineRest = ns.nd.gosub[ns.nd.gosubPos].rest;		
	}
}
