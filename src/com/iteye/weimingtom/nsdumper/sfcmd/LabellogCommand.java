package com.iteye.weimingtom.nsdumper.sfcmd;

import java.io.File;

import app.boya.tukineko.parser.NScripter;

public class LabellogCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "labellog");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] labellog");
		
		ns.setMsRest();
		ns.nd.labellog = true;
		File localFile = new File(ns.path + "NSCRLLOG.DAT");
		if (localFile.exists() == true) {
			NScripter.loadLogData(ns.path + "NSCRLLOG.DAT", ns.nd.lchk);
		}		
	}
}
