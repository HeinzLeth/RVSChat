package Views;

import Models.User;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainView extends JFrame {
    
    public interface TestListener{
        public void doIt();
    }
    
    public void registerListener(TestListener listener){
        this.listener = listener;
    }

    int RVSServerPort = 2534;

    private Login login;
    private Chat chat;
    private TestListener listener;

    private JTextField tf_ip;
    private JTextField tf_port;
    private JTextField tf_user;

    public Login getLogin() {
        return login;
    }

    public Chat getChat() {
        return chat;
    }

    public String getTf_ipText() {
        return tf_ip.getText();
    }

    public String getTf_portText() {
        return tf_port.getText();
    }

    public String getTf_userText() {
        return tf_user.getText();
    }

    private User you;

    private Pattern pattern;
    private Matcher matcher;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Main frame = new Main();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
    /**
     * Create the frame.
     */
    public MainView() {
        super("Super-Messanger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        chat = new Chat();
        login = new Login();
        this.add(login);

        tf_ip = login.tfIP;
        tf_port = login.tfPort;
        tf_user = login.tfUser;

        login.bConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChatPanel();
                listener.doIt();
            }
        });

        login.bReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tf_ip.setText("");
                tf_port.setText("");
                tf_user.setText("");
                login.bConnect.setEnabled(false);
                setLocationRelativeTo(null);
            }
        });

        login.tfIP.getDocument().addDocumentListener(new DocumentHandler());
        login.tfUser.getDocument().addDocumentListener(new DocumentHandler());
        login.tfPort.getDocument().addDocumentListener(new DocumentHandler());
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void showChatPanel() {
        this.remove(login);
        this.add(chat);
        this.repaint();
        this.pack();
        this.setLocationRelativeTo(null);
    }


    private class DocumentHandler implements DocumentListener {

        private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        private static final String PORT_PATTERN = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";

        /**
         * Validate ip address with regular expression
         *
         * @param ip ip address for validation
         * @return true valid ip address, false invalid ip address
         */
        private boolean validateIP(final String ip) {
            matcher = pattern.compile(IPADDRESS_PATTERN).matcher(ip);
            return matcher.matches();
        }

        /**
         * Validate ip address with regular expression
         *
         * @param ip ip address for validation
         * @return true valid ip address, false invalid ip address
         */
        private boolean validatePort(final String ip) {
            matcher = pattern.compile(PORT_PATTERN).matcher(ip);
            return matcher.matches();
        }

        private boolean checkUserInput() {
            return validateIP(tf_ip.getText().trim())
                    && validatePort(tf_port.getText().trim())
                    && tf_user.getText().trim().length() > 0;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (checkUserInput()) {
                login.bConnect.setEnabled(true);
            } else {
                login.bConnect.setEnabled(false);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (checkUserInput()) {
                login.bConnect.setEnabled(true);
            } else {
                login.bConnect.setEnabled(false);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (checkUserInput()) {
                login.bConnect.setEnabled(true);
            } else {
                login.bConnect.setEnabled(false);
            }
        }
    }
}
