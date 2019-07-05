package hh;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class hhForm {
	
	private static ArrayList<ProfArea> ProfData	= null;
		
	public static void createGUI()
    {
        JFrame frame = new JFrame("Test frame");
        //frame.setSize(500, 500);
        Container cp	= frame.getContentPane(); 
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JComboBox ProfArea = new JComboBox(ProfData.toArray());
        //ProfArea.setPreferredSize(new Dimension(100, 100));
        cp.add(ProfArea);
        
        //JComboBox Prof = new JComboBox((ComboBoxModel) ProfData);
        //ProfArea.setPreferredSize(new Dimension(100, 100));
        //cp.add(Prof);
        
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.pack();
        frame.setVisible(true);          
   }
}
