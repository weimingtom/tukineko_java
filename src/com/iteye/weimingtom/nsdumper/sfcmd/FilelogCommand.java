package com.iteye.weimingtom.nsdumper.sfcmd;

import java.io.File;

import app.boya.tukineko.parser.NScripter;

public class FilelogCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "filelog");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] filelog");
		
		ns.setMsRest();
		ns.nd.filelog = true;
		File localFile = new File(ns.path + "NSCRFLOG.DAT");
		if (localFile.exists() == true) {
			NScripter.loadLogData(ns.path + "NSCRFLOG.DAT", ns.nd.fchk);
		}		
	}
}
