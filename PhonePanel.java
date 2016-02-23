package phonelisting;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 *
 * @author Austin Boucher
 * @version 13 Jan 2015
 */
public class PhonePanel extends JPanel {

    // back-end: board data
    private JTextField name;//creates a temporary slot for name
    private JTextField number;//creates a temporart slot for numbers
    JTextArea textName;//creates a java text area for input
    Stack list = new Stack();//creates a list stack
    Stack listtemp = new Stack();//creates a temp list stack 
    Stack sort = new Stack();//creates a sort list stack
    private int buttonpress;//keeps count of times a contact has been added

    //constructor
    
    public PhonePanel() //makes the board
    {
        setPreferredSize(new Dimension(700, 175));
        setLayout(new BorderLayout());

        //Field with label
        name = new JTextField(16);
        number = new JTextField(25);
        JLabel nameLabel2 = new JLabel("Field: ");
        nameLabel2.setLabelFor(number);
        JLabel nameLabel = new JLabel("Field: ");
        nameLabel.setLabelFor(name);

        JPanel fieldsPanel = new JPanel();//creates a grid for the text fields
        fieldsPanel.setLayout(new GridLayout(2,1));
        fieldsPanel.add(name);
        fieldsPanel.add(number);
        name.setText("Name");
        number.setText("Number");//sets the text inside the text fields

        //Text area.
        textName = new JTextArea("\n" + "\n");//creates a 3 line text area
        textName.setLineWrap(true);
        textName.setWrapStyleWord(true);
        textName.setEditable(false);
        textName.setBorder(BorderFactory.createLineBorder(Color.black));
       
        //Button:
         JButton button = new JButton( new AbstractAction("Search Contacts")//creates a java button that you can click
        {
            @Override
            public void actionPerformed(ActionEvent e)//custom action event for the specific button
            {
                String value = name.getText();//records the first value of the first text box
                name.setText("Name");//fills the text area after the text has been used
                String value2 = number.getText();//records the second value of the second text box
                number.setText("Number");//fills the text area after the text has been used
                textName.setText("Name: " + value + "\nNumber: " + value2 + "\nAre being searched for");//fills the top bar with comment   
                //pop up
                JDialog popup = new JDialog();
                String output = new String();
                while(buttonpress != 0)//fills a temporary list with code while reporting the list to a new popup
                {
                    String ouput;
                    Object x = list.peek();
                    ouput = x.toString();
                    if(ouput.contains(value) == true || ouput.contains(value2) == true)
                    {
                        output += list.peek() + "\n";
                    }
                    listtemp.push(list.peek());
                    list.pop();
                    buttonpress--;
                    }
                    if(buttonpress == 0)//refills the original list with the code from the temporary list
                    {
                     int k = listtemp.getSize();
                     for(int i = 0; i < k; i++)
                    {
                     list.push(listtemp.pop());
                     buttonpress++;
                    }
                    }
                    if(output.contains(value) != true && output.contains(value2) != true)
                    {
                        output = "The number " + value2 + " was not found, nor was the name " + value;
                    }
                JTextArea popText = new JTextArea(output);
                popText.setLineWrap(false);
                popText.setWrapStyleWord(true);
                popText.setEditable(false);
                popText.setBorder(BorderFactory.createLineBorder(Color.black));
                popup.add(popText);
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }  
        }); 
        JButton button2;//creats a second button
        button2 = new JButton( new AbstractAction("Show All")//custom action event for the specific button
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                name.setText("Name");
                number.setText("Number");
                textName.setText("\n" + "\n");//fills the top bar with comment
                
                //pop up
                JDialog popup = new JDialog();
                String output = new String();
                
                    while(buttonpress != 0)//allows the list to be printed out
                    {
                    listtemp.push(list.peek());
                    Object t = list.pop();
                    String ouput = t.toString();
                    output += ouput + "\n";
                    buttonpress--;
                    }
                    if(buttonpress == 0)//refills the original list with the temporary list
                    {
                     int k = listtemp.getSize();
                     if(k == buttonpress)
                     {
                         output = "There is nothing saved in the contacts";
                     }
                     for(int i = 0; i < k; i++)
                    {
                     list.push(listtemp.pop());
                     buttonpress++;
                    }
                    }
                JTextArea popText = new JTextArea(output);
                popText.setLineWrap(false);
                popText.setWrapStyleWord(true);
                popText.setEditable(false);
                popText.setBorder(BorderFactory.createLineBorder(Color.black));
                popup.add(popText);
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        }); 
        JButton button3 = new JButton( new AbstractAction("Save Contact")
        {
            @Override
            public void actionPerformed(ActionEvent e)//custom action to save the inputted values and attempts to sort them
            {
                String value = "";
                value = name.getText();
                String value2 = number.getText();
                textName.setText("Name: " + value + ("\nNumber: " + value2 + "\nHas been saved to contacts"));
                list.push(value + "\n" + value2 + "\n");
                number.setText("Number");
                name.setText("Name");
                buttonpress++;
                //pop up
                JDialog popup = new JDialog();
                String output = "The name: " + value + " and number: " + value2 + " have been saved";
                JTextArea popText = new JTextArea(output);
                popText.setLineWrap(false);
                popText.setWrapStyleWord(true);
                popText.setEditable(false);
                popText.setBorder(BorderFactory.createLineBorder(Color.black));
                popup.add(popText);
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }  
        });        
        
        JButton button4 = new JButton( new AbstractAction("Remove Contact") //removes all contacts with the specific entry 
        {
            @Override
            public void actionPerformed( ActionEvent e ) {
                String value = name.getText();
                String value2 = number.getText();
                name.setText("Name");
                number.setText("Number");
                textName.setText("Name: " + value + "\nNumber: ");
                //pop up
                JDialog popup = new JDialog();
                String output = new String();
                int count = 0;
                while(buttonpress != 0)//fills a secondary list
                {
                    String ouput;
                    Object x = list.peek();
                    ouput = x.toString();//converts an object to string
                    if(ouput.contains(value) == true && ouput.contains(value2) == true)//searches all linked lists for the inputted variables
                    {
                        count++;
                    }
                    if(count == 1)//generates a singular long comment
                    {
                    output = "All entries entered with the name: " + value + " and number: " + value2 + " have been deleted\n";
                    }
                    if(ouput.contains(value) == true && ouput.contains(value2) == true)//shows all deleted contacts
                    {
                        output += list.peek() + "\n";
                        list.pop();
                        buttonpress--;
                    }
                    else
                    {
                    listtemp.push(list.peek());
                    list.pop();
                    buttonpress--;
                    }
                    }
                    if(buttonpress == 0)//refills the entire list minus the removed contacts
                    {
                     int k = listtemp.getSize();
                     for(int i = 0; i < k; i++)
                    {
                     list.push(listtemp.pop());
                     buttonpress++;
                    }
                    }
                    if(output.contains(value) != true && output.contains(value2) != true)//returns a negitive value if both number and name are not found
                    {
                        output = "The number " + value2 + " was not found, nor was the name " + value + "\nThey cannot be deleted \nPlease enter in exactly what you wish to be deleted";
                    }
                JTextArea popText = new JTextArea(output + "         ");
                popText.setLineWrap(false);
                popText.setWrapStyleWord(true);
                popText.setEditable(false);
                popText.setBorder(BorderFactory.createLineBorder(Color.black));
                popup.add(popText);
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
            
        });
        JButton button5 = new JButton("Save to a text file");
        button5.addActionListener(new DoSomething());
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(2, 2));
        buttonPane.add(button);
        buttonPane.add(button2);
        buttonPane.add(button3);
        buttonPane.add(button4);
        JPanel buttonPane3 = new JPanel();
        buttonPane3.setLayout(new GridLayout(1,2));
        buttonPane3.add(button5);
        
        
        //pack stuff together
        JPanel pane = new JPanel(new BorderLayout());
        pane.add(textName, BorderLayout.NORTH);
        pane.add(buttonPane, BorderLayout.WEST);
        pane.add(buttonPane3, BorderLayout.SOUTH);
        pane.add(fieldsPanel, BorderLayout.EAST);

        add(pane, BorderLayout.CENTER);
    }
    private class DoSomething implements ActionListener //should save the file 
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String value = name.getText();
            name.setText("");
            textName.setText("You typed: \n" + value);

            //pop up
            JDialog popup = new JDialog();
            String output = value;
            while(buttonpress != 0)//allows the list to be printed out
                    {
                    listtemp.push(list.peek());
                    Object t = list.pop();
                    String ouput = t.toString();
                    output += ouput + "                 \n";
                    buttonpress--;
                    }
                    if(buttonpress == 0)//refills the original list with the temporary list
                    {
                     int k = listtemp.getSize();
                     for(int i = 0; i < k; i++)
                    {
                     list.push(listtemp.pop());
                     buttonpress++;
                    }
                    }
                    
            try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(".//contact.txt") );
			writer.write(output); 
			writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PhonePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JTextArea popText = new JTextArea("You data has been saved under contacts.txt");
            popText.setLineWrap(false);
            popText.setWrapStyleWord(true);
            popText.setEditable(false);
            popText.setBorder(BorderFactory.createLineBorder(Color.black));
            popup.add(popText);
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
            
    }
    private void sort()//this code refuses to work, if you remove the while loops the code runs fine but with them, the compareTo() methods always return 0
    {
        int p = 0;
        boolean check = false;
        while(check == false)
        {
            listtemp.push(list.peek());
            while(list.peek() != null)
            {
            String ouput;
            String temp1;
            Object x = list.peek();
            ouput = x.toString();
            Object k = list.pop();
            temp1 = k.toString();
            if(ouput.compareToIgnoreCase(temp1) <= 0)
            {
                sort.push(list.peek());
                p++;
            }
            else
            {
                sort.push(listtemp.pop());
                listtemp.push(list.peek());
                p = 0;
            }
             }
            if(p == buttonpress)
            {
                check = true;
            }
            while(sort.peek() != null)
            {
            String ouput;
            String temp1;
            Object x = sort.peek();
            ouput = x.toString();
            Object k = sort.pop();
            temp1 = k.toString();
            if(ouput.compareToIgnoreCase(temp1) <= 0)
            {
                list.push(sort.peek());
                p++;
            }
            else
            {
                list.push(listtemp.pop());
                listtemp.push(sort.peek());
                p = 0;
            }
            }
    }
        for(int i = 0; i < listtemp.getSize(); i++)
        {
                     list.push(listtemp.pop());
        }
        }
    }
    }


    
             
               

