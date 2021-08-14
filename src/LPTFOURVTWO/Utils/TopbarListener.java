package LPTFOURVTWO.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import LPTFOURVTWO.Main;

public class TopbarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == Main.newfolder) {
			return;
		}

		if(e.getSource() == Main.newtextdocument) {
			return;
		}

		if(e.getSource() == Main.newwhiteboard) {
			return;
		}

		if(e.getSource() == Main.refresh) {
			Main.allnodes.clear();
//			DefaultTreeModel model = (DefaultTreeModel) Main.notetree.getModel();
//			DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//			root.removeAllChildren();
//			model.reload();
//
//			Main.addNodes(root, Main.dir);
			return;
		}


		if(e.getSource() == Main.copy) {
			return;
		}

		if(e.getSource() == Main.paste) {
			return;
		}

		if(e.getSource() == Main.cut) {
			return;
		}
	}

}