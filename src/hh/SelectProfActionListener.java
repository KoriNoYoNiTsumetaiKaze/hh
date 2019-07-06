package hh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class SelectProfActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(arg0.getSource());
        JComboBox box = (JComboBox)arg0.getSource();
        Prof item = (Prof)box.getSelectedItem();
        hhForm.setSelectProf(item);
        //System.out.println(item);
	}

}
