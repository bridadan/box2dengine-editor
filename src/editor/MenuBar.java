package editor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MenuBar {
	public class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println(event);
			
		}		
	}
	
	Editor parentEditor;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	MenuListener menuListener;
	
	MenuBar() {
		menuListener = new MenuListener();
		
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentEditor.exit();
			}
		});
		
		menu.add(menuItem);
	}
	
	public void setEditor(Editor editor) {
		this.parentEditor = editor;
		editor.setJMenuBar(menuBar);
	}
}
