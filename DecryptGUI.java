import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> The Decrypt GUI</h1>
 * This class contains the decrypt GUI for the program, including the creation of
 * the JFrame, all the widgets and the listens needed for it to work.
 *
 */
public class DecryptGUI extends JFrame{

    //Create variables for all the widgets in the JFrame
    private JLabel header;
    private JLabel info;
    private HintTextField text;
    private HintTextField output;
    private JButton decrypt;
    private JButton back;
    private String instruction = "This program will decrypt text without a shift value" +
            " use with cation!";


    /**
     * The class contains all the creation of the widgets, from adding them to the JFrame
     * to there size, text or listeners.
     */
    public DecryptGUI(){
        setLayout(null);

        //Define each widget and set appropriate text for each.
        header = new JLabel("Secret Decryption");
        info = new JLabel("<html><div style='text-align: center;'>" + instruction +
                "</div></html>");
        text = new HintTextField("                Enter your text");
        output = new HintTextField("                                            Decrypted text");
        decrypt = new JButton("Decrypt");
        back = new JButton("Return");

        //Add the widgets to the JFrame
        add(header);
        add(info);
        add(text);
        add(output);
        add(decrypt);
        add(back);

        //Set and alter how each widget is displayed in the JFrame
        //HEADER
        header.setBounds(95,7,300,30);
        header.setFont(header.getFont().deriveFont(18.0f));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        //INFO
        info.setBounds(95,30,300,60);

        //TEXT
        text.setBounds(140,105,220,30);

        //DECRYPT
        decrypt.setBounds(200, 150, 100, 30);
        decrypt.setEnabled(false);

        //OUTPUT
        output.setBounds(20, 220, 450, 30);
        output.setHorizontalAlignment(SwingConstants.CENTER);
        output.setEditable(false);
        output.setEnabled(true);

        //BACK
        back.setBounds(415, 5, 80, 30);

        //Add the listeners to widgets that will be interacted with
        enteredDecrypt textcheck = new enteredDecrypt();
        text.getDocument().addDocumentListener(textcheck);

        decryptButton buttonCheck = new decryptButton();
        decrypt.addActionListener(buttonCheck);
        back.addActionListener(buttonCheck);

    }

    /**
     * This class contains all the appropriate methods for the DocumentListener. This
     * EnteredDecrypt class implements the DocumentListener, it then monitors the assigned
     * JTextFields for any and all inputs.
     */
    //Listener that will disable the button until the text file contains a string
    private class enteredDecrypt implements DocumentListener {

        /**
         * This method just calls the jump method. Any and all actions in the field are
         * diverted there.
         * @param event The event just references the action preformed in the assigned widget.
         */
        public void insertUpdate(DocumentEvent event) {

            jump();
        }

        /**
         * This method just calls the jump method. Any and all actions in the field are
         * diverted there.
         * @param event The event just references the action preformed in the assigned widget.
         */
        public void removeUpdate(DocumentEvent event) {
            jump();
        }

        /**
         * This method just calls the jump method. Any and all actions in the field are
         * diverted there.
         * @param event The event just references the action preformed in the assigned widget.
         */
        public void changedUpdate(DocumentEvent event) {
            jump();
        }

        /**
         * This method checks the text field continuously checking if the field contains
         * a value, it then releases the button form the lock making it available to
         * click.
         */
        public void jump() {

            //Change the availability of the button depending on the value of the JTextField
            if (text.getText().equals("")) {
                decrypt.setEnabled(false);
            } else {
                decrypt.setEnabled(true);
            }
        }
    }

    /**
     * The class contains the ButtonPress Action Listener. This implements the ActionListener
     * class.
     */
    //Listener that will run the appropriate function when the button is pressed.
    private class decryptButton implements ActionListener{

        /**
         * This method checks if the decrypt or the back button has been pressed
         * and then acts accordingly.
         * @param event The parameter is in reference to the event it gets send from the
         *              assigned widget
         */
        public void actionPerformed(ActionEvent event) {

            /* Call the decipher method and send it the text from the JTextField, displaying
            the output in the output text field.*/
            if(event.getSource() == decrypt) {
                String set = text.getText();
                Caesar r = new Caesar();
                String done = r.decipher(set);
                output.setText(String.valueOf(done));
            }
            // Run the CaesarGUI JFrame and dispose of the DecryptGUI JFrame.
            if(event.getSource() == back){
                CaesarGUI.main(null);
                dispose();
            }

        }
    }

    /**
     * This is the decryptWindow method and creates the GUI JFrame that the user interacts with.
     */
    //Decrypt Window that creates the JFrame and all its parameters.
    public static void decryptWindow(){
        DecryptGUI frame = new DecryptGUI();
        frame.setTitle("Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 170, 500, 300);
        frame.setVisible(true);

    }

}
