import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> The Caesar GUI Class</h1>
 * This class contains the entire main GUI for the program, including the creation of
 * the JFrame, all the widgets and the listens needed for it to work.
 **/
public class CaesarGUI extends JFrame {

    //Create variables for all the widgets in the JFrame
    private JLabel header;
    private JLabel info;
    private JTextField shift;
    private JButton encrypt;
    private JButton decrypt;
    private JTextField text;
    private JTextField output;
    private String instuction = "This program will encrypt any text by the shift entered. It can also " +
            "decrypt a given text if it's shift is entered as a negative value.";

    /**
     * The class contains all the creation of the widgets, from adding them to the JFrame
     * to there size, text or listeners.
     */
    public CaesarGUI(){
        setLayout(null);

        //Define each widget and set appropriate text for each.
        header = new JLabel("Rotation Encryption Program");
        info = new JLabel("<html><div style='text-align: center;'>" + instuction +
                "</div></html>");
        shift = new HintTextField("    Enter shift");
        text = new HintTextField("              Enter text here");
        output = new HintTextField("                                            Encrypted Text");
        encrypt = new JButton("Press here!");
        decrypt  = new JButton("Decrypt");

        //Add the widgets to the JFrame
        add(header);
        add(info);
        add(shift);
        add(text);
        add(encrypt);
        add(output);
        add(decrypt);

        //Set and alter how each widget is displayed in the JFrame
        //HEADER
        header.setBounds(95,7,300,30);
        header.setFont(header.getFont().deriveFont(18.0f));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        //header.setBorder(BorderFactory.createLineBorder(Color.black));

        //INFO
        info.setBounds(95,30,300,60);
        //info.setBorder(BorderFactory.createLineBorder(Color.black));

        //TEXT
        text.setBounds(140,105,220,30);

        //SHIFT
        shift.setBounds(195, 140, 110, 30);

        //ENCRYPT
        encrypt.setBounds(200, 180, 100, 30);
        encrypt.setEnabled(false);

        //OUTPUT
        output.setBounds(20, 220, 450, 30);
        output.setHorizontalAlignment(SwingConstants.CENTER);
        output.setEditable(false);
        output.setEnabled(true);

        //DECRYPT
        decrypt.setBounds(415, 5, 80, 30);

        //Add the listeners to widgets that will be interacted with
        buttonPress button = new buttonPress();
        encrypt.addActionListener(button);
        decrypt.addActionListener(button);

        enteredText textcheck = new enteredText();
        text.getDocument().addDocumentListener(textcheck);
        shift.getDocument().addDocumentListener(textcheck);

    }

    /**
     * The class contains the ButtonPress Action Listener. This implements the ActionListener
     * class.
     */
    //Listener that will run the appropriate function when the button is pressed
    private class buttonPress implements ActionListener {

        /**
         * This method checks if the encrypt or the decrypt button has been pressed
         * and then acts accordingly.
         * @param event The parameter is in reference to the event it gets send from the
         *              assigned widget
         */
        public void actionPerformed(ActionEvent event) {

            /* Call the rotate method and send it the text from the JTExtField and the
            number int he shift value, displaying the result in the output text field.
             */
            if(event.getSource() == encrypt){
                String set = text.getText();
                int num = Integer.parseInt(shift.getText());
                Caesar r = new Caesar();
                String done = r.rotate(set,num);
                output.setText(String.valueOf(done));

                text.setText("");
                shift.setText("");
            }
            // Run the DecryptGUI JFrame and dispose of the CaesarGUI JFrame.
            if(event.getSource() == decrypt){
                DecryptGUI.decryptWindow();
                dispose();
            }


        }
    }

    /**
     * This class contains all the appropriate methods for the DocumentListener. This
     * EnteredText class implements the DocumentListener, it then monitors the assigned
     * JTextFields for any and all inputs.
     */
    //Listener that will disable the button until both text fields contain text
    private class enteredText implements DocumentListener {
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
         * This method checks each text field continuously checking if both the fields contain
         * a value, then if the shift field is an integer before finally releasing the encrypt
         * button form its lock.
         */
        public void jump() {

            if (text.getText().equals("") || shift.getText().equals("")) {
                encrypt.setEnabled(false);
            }
            else if (intCheck(shift.getText())){
                encrypt.setEnabled(false);
            }
            else {
                encrypt.setEnabled(true);
            }
        }
    }

    /**
     * This class uses a try and catch to check if a given string is an integer.
     * @param test This string will be parsed to see if it contains an integer.
     * @return It will return a boolean depending on if the value is a integer or not.
     */
    //This checks if the string variable can be parsed to an integer, returning a boolean.
    public boolean intCheck(String test){

        try{
            Integer.parseInt(test);
            return false;
        }
        catch (NumberFormatException e){
            return true;
        }
    }

    /**
     * This is the main method and creates the GUI JFrame that the user interacts with.
     * @param args There are no console inputs for this program.
     */
    //Main function that creates the JFrame and all its parameters.
    public static void main(String[] args) {
        CaesarGUI frame = new CaesarGUI();
        frame.setTitle("Encryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 170, 500, 300);
        frame.setVisible(true);

    }


}

/**
 *    Title: Extension of JTextField
 *    Author: Adam Gawne-Cain
 *    Date: 07/04/2019
 *    Code version: 1.0
 *    Availability: https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint
 *                  https://stackoverflow.com/users/3805036/adam-gawne-cain
 *
 **/
class HintTextField extends JTextField {
    public HintTextField(String hint) {
        _hint = hint;
    }
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    private final String _hint;
}


