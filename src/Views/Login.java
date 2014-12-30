package Views;

import GUI.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class Login extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5549746014555487833L;
	public JTextField tfIP;
	public JTextField tfPort;
	public JTextField tfUser;
	public JButton bConnect;
	public JButton bReset;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pLogin = new JPanel();
		pLogin.setLayout(new SpringLayout());
		
		JLabel lIPAddress = new JLabel("IP-Address");
		
		tfIP = new JTextField();
		tfIP.setToolTipText("IP-Adresse eintragen!");
		tfIP.setText("127.0.0.1");
		tfIP.setVisible(true);
		
		JLabel lPort = new JLabel("Port");
		
		tfPort = new JTextField();
		tfPort.setToolTipText("Port eintragen!");
		tfPort.setText("5555");
		tfPort.setVisible(true);
		
		JLabel lUsername = new JLabel("Username");
		
		tfUser = new JTextField();
		tfUser.setToolTipText("Benutzername eintragen!");
		tfUser.setText("heinz");
		tfUser.setVisible(true);
		
		pLogin.add(lIPAddress);
		pLogin.add(tfIP);
		pLogin.add(lPort);
		pLogin.add(tfPort);
		pLogin.add(lUsername);
		pLogin.add(tfUser);
		
		SpringUtilities.makeCompactGrid(pLogin, 3, 2, 30, 30, 30, 30);
                
		
		JPanel pSend = new JPanel();
		
		bConnect = new JButton("Connect");
		bConnect.setMaximumSize(new Dimension(120, 30));
		bConnect.setPreferredSize(new Dimension(100, 25));
		bConnect.setEnabled(false);
		pSend.add(bConnect);
		
		bReset = new JButton("Reset");
		bReset.setPreferredSize(new Dimension(100, 25));
		bReset.setMinimumSize(new Dimension(60, 20));
		bReset.setMaximumSize(new Dimension(120, 30));
		pSend.add(bReset);
		
		JLabel lHead = new JLabel("Super-Messanger");
		add(lHead, BorderLayout.NORTH);
		lHead.setFont(new Font("Arial Black", Font.BOLD, 39));
		lHead.setHorizontalAlignment(SwingConstants.CENTER);
		lHead.setVerticalAlignment(SwingConstants.CENTER);
		lHead.setEnabled(true);
		this.add(pLogin, BorderLayout.CENTER);
		this.add(pSend, BorderLayout.PAGE_END);
	}
}
