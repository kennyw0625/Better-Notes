package LPTFOURVTWO.Utils;

import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import LPTFOURVTWO.Main;

@SuppressWarnings("serial")
public class JTreeRenderer extends DefaultTreeCellRenderer{

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		JComponent c = (JComponent) super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value; 

		for(FileStorage.Nodes curnode : Main.allnodes) {
			if(curnode.node == node) {


				if(curnode.folder) {

					if(Main.selected != curnode) {
						if(isSelected) {
							c.setBackground(new Color(218, 221, 225));
							Main.selectedParent = curnode;
						}
					}

					if(expanded) {						
						c.setBackground(new Color(218, 221, 225));
						c.setOpaque(true);

						setIcon(new ImageIcon(Main.class.getResource("FolderExpanded.png")));

						return c;
					}

					if(!expanded) {					
						int nodes = node.getChildCount();

						if(nodes != 0) {
							setIcon(new ImageIcon(Main.class.getResource("FolderNotExpandedWChild.png")));
							c.setBackground(new Color(227, 229, 232));
							c.setOpaque(true);
						}else if(nodes == 0){
							setIcon(new ImageIcon(Main.class.getResource("FolderNotExpandedWOChild.png")));
						}

						return c;
					}
				}

				if(curnode.extension.equals(".mp4")) 
					setIcon(new ImageIcon(Main.class.getResource("VideoIcon.png")));

				if(curnode.extension.equals(".png") || curnode.extension.equals(".jpg")) 
					setIcon(new ImageIcon(Main.class.getResource("ImageIcon.png")));

				if(curnode.extension.equals(".txt")) 
					setIcon(new ImageIcon(Main.class.getResource("TextIcon.png")));

				if(Main.selected != curnode) {
					if(isSelected) {
						c.setBackground(new Color(218, 221, 225));
						c.setOpaque(true);
						Main.selectedParent = new FileStorage.Nodes((DefaultMutableTreeNode) curnode.node.getParent(), "???", curnode.file.getParentFile(), true);
						Main.selected = curnode;
						if(curnode.extension.equals(".txt"))
							try {
								Main.addTextFile(curnode);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

						if(curnode.extension.equals(".png")) {
							try {
								Main.addImageFile(curnode);
							} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
								e.printStackTrace();
							}
						}

						return c;
					}
				}

				if(!isSelected) {
					c.setBackground(new Color(227, 229, 232));
					c.setOpaque(true);
					return c;
				}

			}
		}

		return c;

	}
}