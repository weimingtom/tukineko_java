package com.iteye.weimingtom.nsdumper.fecmd;

import java.util.Calendar;
import java.util.Date;

import app.boya.tukineko.parser.NScripter;

public class TimeCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "time");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] time");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("time");
		} else {
			Date localDate = new Date();
			if ((ns.getArg(0).startsWith("%") == true)
					&& (ns.getArg(1).startsWith("%") == true)
					&& (ns.getArg(2).startsWith("%") == true)) {
				// FIXME:
				/*
				 * this.valueNum[evalNum(getArg(0).substring(1))] =
				 * localDate .getHours();
				 * this.valueNum[evalNum(getArg(1).substring(1))] =
				 * localDate .getMinutes();
				 * this.valueNum[evalNum(getArg(2).substring(1))] =
				 * localDate .getSeconds();
				 */
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(localDate);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = calendar
						.get(Calendar.HOUR);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(1).substring(1))] = calendar
						.get(Calendar.MINUTE);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(2).substring(1))] = calendar
						.get(Calendar.SECOND);
			} else {
				ns.error("time");
			}
			ns.makeLineRest(3);
		}		
	}
}
