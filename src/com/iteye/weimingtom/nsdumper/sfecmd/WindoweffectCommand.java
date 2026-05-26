package com.iteye.weimingtom.nsdumper.sfecmd;

import app.boya.tukineko.entry.NsEffect;
import app.boya.tukineko.parser.NScripter;

public class WindoweffectCommand extends SFECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "windoweffect");
	}
	
	@Override
	public void execute() {
		debug("[SFECommand] windoweffect");
		
		switch (ns.parseArgs(true)) {
		case 1:
			ns.nd.effect.put("window",
					new NsEffect(ns.nd.evalNum(ns.getArg(0))));
			break;

		case 2:
			ns.nd.effect.put(
					"window",
					new NsEffect(ns.nd.evalNum(ns.getArg(0)), ns.nd
							.evalNum(ns.getArg(1))));
			break;

		case 3:
			ns.nd.effect
					.put("window",
							new NsEffect(ns.nd.evalNum(ns.getArg(0)),
									ns.nd.evalNum(ns.getArg(1)), ns.nd
											.evalStr(ns.getArg(2))));
			break;

		default:
			ns.error("windoweffect");
		}		
	}
}
