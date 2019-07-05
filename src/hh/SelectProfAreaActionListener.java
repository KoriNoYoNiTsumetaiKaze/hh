package hh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class SelectProfAreaActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(arg0.getSource());
        JComboBox box = (JComboBox)arg0.getSource();
        ProfArea item = (ProfArea)box.getSelectedItem();
        hhForm.setSelectProfArea(item);
        //System.out.println(item);
	}

}
