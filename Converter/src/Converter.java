import javax.swing.JFrame;

/**
 - The main driver program for the GUI based conversion program.
 -
 - @author Aadarsh Kumar Rauniyar
 - Date: 30th November 2020
 */

public class Converter {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Converter"); //Creating and initializing object of JFrame as well as passing argument
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setDefaultCloseOperation as method and EXIT_ON_CLOSE for closing the program//
       
        MainPanel panel = new MainPanel();
        //creating a object panel for mainpanel

        frame.setJMenuBar(panel.setupMenu());
        //setting JMenuBar and calling the setupMenu from MainPanel class

        frame.getContentPane().add(panel);
        // adding panel to the frame

        frame.pack();
        // packing all the properties and components in the frame in a layout
        
        frame.setVisible(true);
        //setting visibility to true
    }
}