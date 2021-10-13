import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 - The main graphical panel used to display conversion components.
 - @author Aadarsh Kumar Rauniyar
 - Date 30th November 2020
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {// MainPanel inheriting JPanel

    private final String[] list = {"Inches/Cm", "Pounds/Kilograms", "Degrees/Radians", "Acres/Hectares", "Miles/Kilometres",
            "Yards/Metres", "Celsius/Fahrenheit"};					//Creating Array for all the available conversion 

    // Declaring necessary java swing properties
    private JTextField textField;
    private JLabel label;
    private JLabel countLabel;
    private JComboBox<String> combo;
    private JCheckBox reverse;
    private ImageIcon fileIcon;
    private ImageIcon helpIcon;
    private ImageIcon exitIcon;
    private ImageIcon aboutIcon;

    //creating a string called message with text which is to be displayed in About
    private final String message = "\t \t \t \t \t \t \t \t \t \t CONVERTER version 2.1 \n" +
            "\n" +
            "This Application is a GUI based Conversion Program.\n" +
            "\n " +
            "The Application is designed for Convert between two types of measuring unit. \n" +
            " \"Inches/Cm\", \"Pounds/Kilograms\", \"Degrees/Radians\", \"Acres/Hectares\", \"Miles/Kilometres\",\r\n" + 
            "\"Yards/Metres\", \"Celsius/Fahrenheit\" \n" +
            
            "Different user friendly GUI features of java are used for easier user-converter interaction \n"+
            
            "\nAuthor: Aadarsh Kumar Rauniyar \n© 2020 Aadarsh K. Rauniyar All Rights Reserved";
    
    // Declaring an integer type variable 'count' to count total conversions
    int count = 0;

    // Method of type JMenuBar which will be used to add all menu and menu items and return them
    JMenuBar setupMenu() {

        JMenuBar menuBar = new JMenuBar();							// declaring and initializing JMenuBar

        // Declaring and initializing menu bar objects "File" as well as showing tool tip
        JMenu fileMenu = new JMenu("File"); 
        fileMenu.setToolTipText("Show more file options");
        fileMenu.setMnemonic(KeyEvent.VK_F); 						// mnemonics for file menu
        	
        // Declaring and initializing menu bar objects "File" as well as showing tool tip
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setToolTipText("Show more help options");
        helpMenu.setMnemonic(KeyEvent.VK_H); 						// mnemonics for help menu


        //Adding icons to the menu objects
        helpIcon = new ImageIcon(new ImageIcon("images/help.png").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        fileIcon = new ImageIcon(new ImageIcon("images/file.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        

        //setting image icons to the menu objects
        fileMenu.setIcon(fileIcon);
        helpMenu.setIcon(helpIcon);

        //adding menu objects to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Declaring and Initializing menu item 'exit' 
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("Close the Converter");					//tool tip for the exit item
        exit.setMnemonic('E');										//mnemonics for 'exit' menu option
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK)); //shortcut key to exit the program

        //actionListener for the exit menu item
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);										// to end the program
            }
        });

        //setting and adding the icon for the menu item 'exit'
        exitIcon = new ImageIcon(new ImageIcon("images/exit.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        exit.setIcon(exitIcon);

        //Declaring and initializing menu item 'about'
        JMenuItem about = new JMenuItem("About"); 					
        about.setToolTipText("Show information about the program");	//tool tip for the about menu item
        about.setMnemonic('A');										//mnemonics for 'about' menu option
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));// shortcut key for about

        //actionListener for the about menu item
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, message, "Converter", JOptionPane.INFORMATION_MESSAGE); //to show a message dialog box
            }
        });

        //setting and adding the icon for the menu item 'about'
        aboutIcon = new ImageIcon(new ImageIcon("images/about.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        about.setIcon(aboutIcon);

        //adding the menu items to the menu option
        fileMenu.add(exit);
        helpMenu.add(about);										// Adding the item to  the menu

        return menuBar;												// returns the menuBar to the frame
    }

    //constructor of mainPanel
    MainPanel() {

        //creating object for inner classes
        ActionListener listener = new ConvertListener();
        ActionListener clearListener = new ButtonListener();


        combo = new JComboBox<String>(list);						//initializing the combo box with array as list
        combo.addActionListener(listener); 							//convert values when option changed
        combo.setToolTipText("List of all the available conversions");//tool tip to combo

        JLabel inputLabel = new JLabel("Enter value:");				//declaring and initializing inputlabel 
        inputLabel.setToolTipText("Enter value for conversion");	//tool tip for inputlabel

        JButton convertButton = new JButton("Convert");				//declaring and initializing button 
        convertButton.setToolTipText("Click to convert");			//tool tip for convert button
        convertButton.addActionListener(listener); 					// adding and calling ConvertListener class for conversion

        label = new JLabel("____");									//initializing the label
        textField = new JTextField(5);								//initializing textfield with 5 columns 
        textField.setToolTipText("Enter Value for Conversion");		//tool tip for textfield
        textField.addActionListener(listener);						//adding ConvertListener class for conversion

        reverse = new JCheckBox("Reverse Conversion");				//initializing reverse 
        reverse.setToolTipText("Click to Reverse the Conversion");	//tool tip for reverse
        reverse.addActionListener(listener);						//adding ConvertListener class for conversion

        JButton clearButton = new JButton("Clear");					//declaring and initializing button
        clearButton.setToolTipText("Click to Reset the Program");	// tool tip for clear button
        clearButton.addActionListener(clearListener);				//adding and calling ButtonListener class to clear


        countLabel = new JLabel("Count: " + count);					//initializing and naming the label


        //adding all the properties to the panel
        add(combo);
        add(inputLabel);
        add(textField);
        add(convertButton);
        add(label);
        add(reverse);
        add(clearButton);
        add(countLabel);

        setPreferredSize(new Dimension(800, 160));					//to set the size of the panel
        setBackground(Color.LIGHT_GRAY);							//to set background color panel
    }

    //creating a private inner class which implements ActionListener
    private class ConvertListener implements ActionListener {

        @Override
        //method of actionListener
        public void actionPerformed(ActionEvent event) {

            String text = textField.getText().trim();				//storing the data of textfield to text after declaring

            double value = 0;										//declaring a double variable
            if (reverse.isSelected()) {								// if statement to check if reverse is selected or not

                if (text.isEmpty() == false) {						// if statement to check if the text is empty or not


                    try {											//try catch statement
                        value = Double.parseDouble(text);			//parsing the string value to double


                        // the factor applied during the conversion
                        double factor = 0;

                        // the offset applied during the conversion.
                        double offset = 0;

                        // switch case statement to get the factor to use for conversion
                        switch (combo.getSelectedIndex()) {			//to get the index of the selected option

                            case 0: 								// cm to inches
                                factor = 0.394;						// adding a factor to convert centimeters to inches
                                break;
                            case 1: 								// kg to Pounds
                                factor = 2.2; 						//adding a factor to convert pounds to kilogram
                                break;
                            case 2: 								// radian to Degrees
                                factor = 57.296; 					// adding a factor to convert acres to hectares
                                break;
                            case 3: 								// hectares to Acres
                                factor = 2.471; 					// adding a factor to convert hectares to acres 
                                break;
                            case 4:									// kilometres to miles
                                factor = 0.621; 					// adding a factor to convert kilometres to miles
                                break;
                            case 5: 								// metres to yards
                                factor = 1.094; 					//adding a factor to convert yards to metres
                                break;
                            case 6: 								// Fahrenheit to Celsius
                                factor = (5 - (160 / value)) / 9; 	// adding a factor to convert fahrenheit to celsius
                                break;

                        }
                        
                        //creating a variable result of type double to calculate  and store the result data//
                        double result = factor * value + offset;
                        count++;									// increment of count
                        
                        //declaring a String object and rounding off result to 2 decimals//
                        String roundOffResult = String.format(("%.2f"), result);
                        label.setText(roundOffResult);				//displaying the result
                        countLabel.setText("Count: " + count);		//displaying the incremented count
                    } catch (Exception e) {							// catch to handle exception
                    	
                        //to display a dialog box if invalid number is enter
                        JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else { 											// else statement if text is empty
                    //to show a dialog box to inform textfield is empty
                    JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.WARNING_MESSAGE);
                }


            } else {												// else statement if reverse isn't selected
                if (text.isEmpty() == false) {						// if statement to check whether the text is empty or not


                    try {											//try catch statement
                        value = Double.parseDouble(text);			//parsing the string value to double


                        // the factor applied during the conversion
                        double factor = 0;

                        // the offset applied during the conversion.
                        double offset = 0;

                        // switch case statement to get the factor to use for conversion
                        switch (combo.getSelectedIndex()) {			//to get the index of the selected option

                            case 0: 								// inches to cm
                                factor = 2.54; 						// adding a factor to convert inches to cm
                                break;
                            case 1: 								// Pounds to kg
                                factor = 0.45359237; 				// factor 
                                break;
                            case 2:									// Degrees to radian
                                factor = 0.0174533; 				// factor 
                                break;
                            case 3: 								// Acres to hectares
                                factor = 0.404686; 					// factor 
                                break;
                            case 4:									// miles to kilometers
                                factor = 1.60934; 					// factor 
                                break;
                            case 5: 								// Yards to meters
                                factor = 0.9144;					// factor 
                                break;
                            case 6: 								//Celsius to Fahrenheit
                                factor = 1.8 + (32 / value); 		// factor 
                                break;

                        }

                        double result = factor * value + offset;/*creating a variable result of type double to calculate
                        and store the result data*/
                        count++;									// increment of count
                        String roundOffResult = String.format(("%.2f"), result);/*declaring a String object
                         and rounding off result to 2 decimals*/
                        label.setText(roundOffResult);				//displaying the result
                        countLabel.setText("Count: " + count);		//displaying the incremented count
                    } catch (Exception e) {							// catch to handle exception
                        //to display a dialog box if invalid number is enter
                        JOptionPane.showMessageDialog(null, "NumberNotValid. Please Enter a Valid Number", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {											//else statement if the text is empty
                    //to display a dialog box informing textfield is empty
                    JOptionPane.showMessageDialog(null, "Please Enter a Number", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }


    //creating private inner class which implements ActionListener
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            	// resetting the values to 0 when clear is triggered
            count = 0;
            label.setText("____");
            countLabel.setText("Count: " + 0);
            textField.setText(null);

        }


    }


}
