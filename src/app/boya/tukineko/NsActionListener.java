package app.boya.tukineko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.boya.tukineko.parser.NScripter;

public class NsActionListener implements ActionListener {
	private NScripter ns;
	private NsWindow tn;

	public NsActionListener(NScripter ns, NsWindow tn) {
		this.ns = ns;
		this.tn = tn;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object localObject = event.getSource();
		String str = event.getActionCommand();
		if (localObject == tn.menuSave) {
			ns.save(str);
			return;
		} else if (localObject == tn.menuLoad) {
			ns.load(str);
			return;
		} else {
			ns.menu3(str);
			return;
		}
	}
}
