package Views;

import Models.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Chat extends JPanel {

	private int lastIndex = 0;
	private JList<String> list;
	private JPanel pChat;
	private LinkedList<ChatPanel> cp;
	private ArrayList<User> users;
	
	/**
	 * Create the panel.
	 */
	public Chat() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pHead = new JPanel();
		add(pHead, BorderLayout.NORTH);
		
		final JLabel lHead = new JLabel("Text to All");
		lHead.setFont(new Font("Arial Black", Font.BOLD, 35));
		pHead.add(lHead);
		
		JPanel pList = new JPanel();
		add(pList, BorderLayout.WEST);
		
		list = new JList<String>();
		// Länge der Clientliste des Servers
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		pList.add(list);
		
		// Namen der Clients (evtl inklusive IP)
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		list.setModel(dlm);

		pChat = new JPanel();
		add(pChat, BorderLayout.EAST);
		pChat.setLayout(new BorderLayout(10, 10));
		
		cp = new LinkedList<>();
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					setVisible(false);
					pChat.setVisible(false);
					pChat.remove(cp.get(lastIndex));
					lastIndex = list.getSelectedIndex();
					pChat.add(cp.get(lastIndex), BorderLayout.CENTER);
					pChat.repaint();
					pChat.setVisible(true);
					setVisible(true);
				}
				
			}
		});

		//pChat.add(cp[0], BorderLayout.EAST);
	}
	
	private void updateTable(){
		DefaultListModel<String> model= new DefaultListModel<>();
		for (int i = 0; i < users.size(); i++) {
			model.addElement(users.get(i).getName());
                        cp.add(new ChatPanel());
		}
		list.setModel(model);
		
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
		updateTable();
	}

    public LinkedList<ChatPanel> getCp() {
        return cp;
    }
        

}