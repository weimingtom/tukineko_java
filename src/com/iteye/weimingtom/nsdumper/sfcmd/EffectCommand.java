package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.entry.NsEffect;
import app.boya.tukineko.parser.NScripter;

public class EffectCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "effect");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] effect");
		
		switch (ns.parseArgs(true)) {
		case 2:
			ns.nd.effect.put(
					Integer.toString(ns.nd.evalNum(ns.getArg(0))),
					new NsEffect(ns.nd.evalNum(ns.getArg(1))));
			break;

		case 3:
			ns.nd.effect.put(
					Integer.toString(ns.nd.evalNum(ns.getArg(0))),
					new NsEffect(ns.nd.evalNum(ns.getArg(1)), 
							ns.nd.evalNum(ns.getArg(2))));
			break;

		case 4:
			ns.nd.effect.put(
					Integer.toString(ns.nd.evalNum(ns.getArg(0))),
					new NsEffect(ns.nd.evalNum(ns.getArg(1)),
							ns.nd.evalNum(ns.getArg(2)), 
							ns.nd.evalStr(ns.getArg(3))));
			break;

		default:
			ns.error("effect");
		}		
	}
}
