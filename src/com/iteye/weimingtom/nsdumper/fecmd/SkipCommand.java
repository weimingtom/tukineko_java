package com.iteye.weimingtom.nsdumper.fecmd;

import java.io.IOException;

import app.boya.tukineko.parser.NScripter;

public class SkipCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "skip");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] skip");
		
		int j;
		if (ns.parseArgs(true) < 1) {
			ns.error("skip");
		} else {
			int i = ns.nd.evalNum(ns.getArg(0));
			if (i < 0) {
				if ((i < -99) || (ns.nd.historyCount < -i)) {
					ns.error("skip:" + Integer.toString(i));
				} else {
					try {
						ns.backHistory(-i);
					} catch (IOException localIOException3) {
						ns.error("skip-");
					}
				}
			} else if (i > 1) {
				for (j = 0; j < i - 1; j++) {
					try {
						ns.readLine();
					} catch (IOException e) {
						ns.error("skip+");
					}
				}
			}
			ns.makeLineRest(1);
		}		
	}
}
