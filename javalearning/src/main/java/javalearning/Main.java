package javalearning;

import javax.swing.SwingUtilities;

import javalearning.core.ui.frame.MainFrame;

public class Main {

	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new MainFrame().doProcess();
            }
        });
	}

}
