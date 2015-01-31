package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Editor extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static int defaultFrameWidth = 1280;
	private static int defaultFrameHeight = 720;
	
	MenuBar menuBar;
	JSplitPane vertSplitPane, horizSplitPane;

	Editor() {
		menuBar = new MenuBar();
		menuBar.setEditor(this);
		
		horizSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		vertSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JScrollPane engineScrollPane = new JScrollPane();
		Dimension minimumSize = new Dimension(640, 480);
		engineScrollPane.setMinimumSize(minimumSize);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Root");
		top.add(new DefaultMutableTreeNode("Player"));
		top.add(new DefaultMutableTreeNode("Obstacle"));
		top.add(new DefaultMutableTreeNode("Player"));
		top.add(new DefaultMutableTreeNode("Obstacle"));
		top.add(new DefaultMutableTreeNode("Player"));
		top.add(new DefaultMutableTreeNode("Obstacle"));
		
		JPanel hierarchyPanel = new JPanel();
		hierarchyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRootVisible(false);
		
		JScrollPane hierarchyScrollPane = new JScrollPane(tree);
		minimumSize = new Dimension(200, 100);
		hierarchyScrollPane.setMinimumSize(minimumSize);
		
		//hierarchyScrollPane.add(tree);
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
	    c.gridwidth = 1;
	    c.weightx = 1;
	    c.weighty = 0;
	    c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		hierarchyPanel.add(new JLabel("Hierarchy"), c);

		c.weighty = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridy = 1;
		hierarchyPanel.add(hierarchyScrollPane, c);
		
		JScrollPane propertyEditorScrollPane = new JScrollPane();
		propertyEditorScrollPane.setMinimumSize(minimumSize);
		
		horizSplitPane.setLeftComponent(vertSplitPane);
		horizSplitPane.setRightComponent(engineScrollPane);
		horizSplitPane.setOneTouchExpandable(false);
		horizSplitPane.setContinuousLayout(false);
		
		vertSplitPane.setTopComponent(hierarchyPanel);
		vertSplitPane.setBottomComponent(propertyEditorScrollPane);
		vertSplitPane.setOneTouchExpandable(false);
		vertSplitPane.setContinuousLayout(false);
		vertSplitPane.setResizeWeight(0.5);
		
		add(horizSplitPane);
		
        this.setSize(defaultFrameWidth,defaultFrameHeight);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	exit();
	        }
        });
        
        setVisible(true);
	}
	
	public static void main(String args[]) {
		new Editor();
    }
	
	public void exit() {
		int result = JOptionPane.showOptionDialog(this,
			"The project has been modified, do you wish to save your changes before exiting?",
			"Project Modified",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null,
			null,
			null);
		
		switch (result) {
			case JOptionPane.YES_OPTION:
				
			break;
			
			case JOptionPane.NO_OPTION:
				
			break;
		}
		
		if (result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
}
