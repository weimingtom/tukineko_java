package com.iteye.weimingtom.nsdumper.fecmd;

import java.util.Calendar;
import java.util.Date;

import app.boya.tukineko.parser.NScripter;

public class DateCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "date");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] date");
		
		if (ns.parseArgs(true) < 3) {
			ns.error("date");
		} else {
			Date localDate = new Date();
			if ((ns.getArg(0).startsWith("%") == true)
					&& (ns.getArg(1).startsWith("%") == true)
					&& (ns.getArg(2).startsWith("%") == true)) {
				// FIXME:
				/*
				 * this.valueNum[evalNum(getArg(0).substring(1))] =
				 * (localDate .getYear() + 1900);
				 * this.valueNum[evalNum(getArg(1).substring(1))] =
				 * (localDate .getMonth() + 1);
				 * this.valueNum[evalNum(getArg(2).substring(1))] =
				 * localDate .getDate();
				 */
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(localDate);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(0).substring(1))] = calendar
						.get(Calendar.YEAR);
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(1).substring(1))] = calendar
						.get(Calendar.MONTH) + 1;
				ns.nd.valueNum[ns.nd.evalNum(ns.getArg(2).substring(1))] = calendar
						.get(Calendar.DAY_OF_MONTH);
			} else {
				ns.error("date");
			}
			ns.makeLineRest(3);
		}		
	}
}
