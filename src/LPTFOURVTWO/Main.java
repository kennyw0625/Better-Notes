package LPTFOURVTWO;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

import LPTFOURVTWO.Utils.DragBarButtons;
import LPTFOURVTWO.Utils.DragNDropFrame;
import LPTFOURVTWO.Utils.FileStorage;
import LPTFOURVTWO.Utils.JTreeRenderer;
import LPTFOURVTWO.Utils.TopbarListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

	static LookAndFeel defaultlf = UIManager.getLookAndFeel();

	public static JFrame frame = new JFrame("Better Notes");

	private static JPanel dragbar = new JPanel(), topbar = new JPanel(), notes = new JPanel(), work = new JPanel();

	private static JLabel BetterNotes = new JLabel("Better Notes");

	public static JButton minimize = new JButton(), fullscreen = new JButton(), close = new JButton();

	private static JMenu file, edit, help;

	public static JMenuItem newfolder, newtextdocument, newwhiteboard, refresh;

	public static JMenuItem copy, paste, cut;

	static JMenuItem website;

	public static File dir;

	public static JTree notetree;

	public static DefaultMutableTreeNode root = new DefaultMutableTreeNode();

	public static LinkedList <FileStorage.Nodes> allnodes = new LinkedList <FileStorage.Nodes>();

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
		frame.setVisible(true);
	}

	private static void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		frame.getContentPane().setLayout(new GroupLayout(frame.getContentPane()));
		frame.setSize(1280, 720);
		frame.setUndecorated(true);

		dragbar.setBackground(new Color(227, 229, 232));
		dragbar.setLayout(new GroupLayout(dragbar));
		dragbar.setSize(frame.getWidth(), 20);

		BetterNotes.setSize(new Dimension(150, 20));
		minimize.setSize(new Dimension(30, 20));
		fullscreen.setSize(new Dimension(30, 20));
		close.setSize(new Dimension(30, 20));

		ImageIcon minimizeicon = new ImageIcon(Main.class.getResource("MinimizeIcon.png"));

		ImageIcon fullscreenicon = new ImageIcon(Main.class.getResource("FullScreenIcon.png"));

		ImageIcon closeicon = new ImageIcon(Main.class.getResource("ExitIcon.png"));

		minimize.setIcon(minimizeicon);
		fullscreen.setIcon(fullscreenicon);
		close.setIcon(closeicon);

		DragBarButtons dbl = new DragBarButtons();

		minimize.setBackground(new Color(227, 229, 232));
		minimize.setBorderPainted(false);
		minimize.setFocusable(false);
		minimize.addActionListener(dbl);
		minimize.addMouseListener(dbl);

		fullscreen.setBackground(new Color(227, 229, 232));
		fullscreen.setBorderPainted(false);
		fullscreen.setFocusable(false);
		fullscreen.addActionListener(dbl);
		fullscreen.addMouseListener(dbl);

		close.setBackground(new Color(227, 229, 232));
		close.setBorderPainted(false);
		close.setFocusable(false);
		close.addActionListener(dbl);
		close.addMouseListener(dbl);

		close.setLocation(dragbar.getWidth()-close.getWidth(), 0);
		fullscreen.setLocation(dragbar.getWidth()-(fullscreen.getWidth()+close.getWidth()), 0);
		minimize.setLocation(dragbar.getWidth()-(minimize.getWidth()+fullscreen.getWidth()+close.getWidth()), 0);

		DragNDropFrame dnd = new DragNDropFrame();

		dragbar.addMouseListener(dnd);
		dragbar.addMouseMotionListener(dnd);
		dragbar.add(BetterNotes);
		dragbar.add(minimize);
		dragbar.add(fullscreen);
		dragbar.add(close);

		frame.getContentPane().add(dragbar);

		topbar.setBackground(new Color(242, 243, 245));
		topbar.setLayout(new CardLayout());
		topbar.setSize(frame.getWidth(), 20);
		topbar.setLocation(0, dragbar.getHeight());

		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		Font f = new Font("sans-serif", Font.PLAIN, 12);
		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);

		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");

		newfolder = new JMenuItem("New Folder");
		newtextdocument = new JMenuItem("New Text");
		newwhiteboard = new JMenuItem("New Whiteboard");
		refresh = new JMenuItem("Refresh Files");

		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		cut = new JMenuItem("Cut");

		website = new JMenuItem("Website");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(242, 243, 245));
		menuBar.setBorder(null);

		//UIManager.setLookAndFeel(defaultlf);

		topbar.add(menuBar);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);

		file.add(newfolder);
		file.add(newtextdocument);
		file.add(newwhiteboard);
		//file.add(refresh);

		edit.add(copy);
		edit.add(paste);
		edit.add(cut);

		help.add(website);

		TopbarListener tbl = new TopbarListener();
		newfolder.addActionListener(tbl);
		newtextdocument.addActionListener(tbl);
		newwhiteboard.addActionListener(tbl);
		refresh.addActionListener(tbl);

		copy.addActionListener(tbl);
		paste.addActionListener(tbl);
		cut.addActionListener(tbl);

		help.addActionListener(tbl);

		frame.getContentPane().add(topbar);

		notes.setLayout(new CardLayout());
		notes.setSize(200, frame.getHeight()-dragbar.getHeight());
		notes.setLocation(0, dragbar.getHeight()+topbar.getHeight());

		String name = System.getProperty("user.name");
		dir = new File("C:\\Users\\" + name + "\\Desktop\\Better Notes");
		if(dir.isDirectory()) {
			addNodes(root, dir);
		}else {
			if(dir.mkdir()) {
				addNodes(root, dir);
			}else {
				System.exit(0);
			}
		}

		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		notetree = new JTree(root);
		JTreeRenderer jtr = new JTreeRenderer();
		notetree.setCellRenderer(jtr);
		//UIManager.setLookAndFeel(defaultlf);
		JScrollPane notepane = new JScrollPane(notetree);

		notetree.setBackground(new Color(227, 229, 232));
		notetree.setRootVisible(false);
		notepane.setBorder(null);

		notes.add(notepane);		

		frame.getContentPane().add(notes);

		work.setBackground(new Color(255, 255, 255));
		work.setLayout(new GroupLayout(work));
		work.setSize(frame.getWidth()-notes.getWidth(), frame.getHeight()-(dragbar.getHeight()+topbar.getHeight()));
		work.setLocation(notes.getWidth(), dragbar.getHeight()+topbar.getHeight());

		frame.getContentPane().add(work);
	}

	public static void addNodes(DefaultMutableTreeNode parentnode, File dir) {
		if(dir.isDirectory()) {
			for (File fileEntry : dir.listFiles()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileEntry.getName());
				parentnode.add(node);
				if (fileEntry.isDirectory()) {
					allnodes.add(new FileStorage.Nodes(node, getExtension(fileEntry.getName()), fileEntry, true));
					addNodes(node, new File(fileEntry.getPath()));
				}else {
					allnodes.add(new FileStorage.Nodes(node, getExtension(fileEntry.getName()), fileEntry, false));
				}
			}
		}
	}

	private static String getExtension(String filename) {
		int startin = filename.lastIndexOf('.');
		if(startin == -1) {
			return "???";
		}
		return filename.substring(startin, filename.length());
	}
}
