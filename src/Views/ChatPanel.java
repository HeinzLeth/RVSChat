package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {
	
    public interface MessageSend{
        public void sendMessage(String message);        
    }
    
    public void registerListener(MessageSend messageSend){
        this.messageSend = messageSend;
    }
	JTextField tfText;
        MessageSend messageSend;
	
	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		setLayout(new BorderLayout(0, 0));
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textArea.setEditable(false);
		textArea.setColumns(50);
		textArea.setRows(10);
		this.add(textArea, BorderLayout.CENTER);
		
		JPanel pTextnSend = new JPanel();
		this.add(pTextnSend, BorderLayout.SOUTH);
		
		tfText = new JTextField();
		tfText.setPreferredSize(new Dimension(19, 30));
		tfText.setHorizontalAlignment(SwingConstants.LEFT);
		tfText.setToolTipText("Ur Message");
		pTextnSend.add(tfText);
		tfText.setColumns(40);
		
		JButton bSend = new JButton("Senden");
		pTextnSend.add(bSend);
                bSend.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        messageSend.sendMessage(tfText.getText());
                    }
                });
	}

}