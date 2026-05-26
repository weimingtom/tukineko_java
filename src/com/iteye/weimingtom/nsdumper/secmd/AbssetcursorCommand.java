package com.iteye.weimingtom.nsdumper.secmd;

import app.boya.tukineko.parser.NScripter;

public class AbssetcursorCommand extends SECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("abssetcursor");
	}
	
	@Override
	public void execute() {
		debug("[SECommand] abssetcursor");
		
		ns.error("abssetcursor");
	}
}
