
import java.awt.Button;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import java.util.Hashtable;
import java.util.Set;

public class Toolbar extends Panel {
	
	Toolbar(Hashtable<String, ItemListener> checkboxes, String checked) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		CheckboxGroup cbg = new CheckboxGroup();
		Set<String> names = checkboxes.keySet();
        for(String key: names) {
        	Checkbox cb = new Checkbox(key, cbg, key.equals(checked));
        	add(cb);
        	cb.addItemListener(checkboxes.get(key));
        }
	}	
}
