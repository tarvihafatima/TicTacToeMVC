package adapter;

import controller.*;
import view.*;
import java.awt.event.*;
import java.util.*;

public class Adapter implements ActionListener
{
    private Controller controller;
    private View view;

    // initializing controller and view classes references
    public Adapter(Controller c, View v)
    {
        this.controller = c;
        this.view = v;
    }

    // Implementation of the actionPerformed method for the ActionListener interface
    public void actionPerformed(ActionEvent event)
    {
        // adapter asks the controller to perform desired action based on the button pressed
        if(view.isReset(event))
        {
            controller.setRequest();
        }
        else
            {
            ArrayList<Integer> position = view.getPosition(event);
            controller.setRequest(position);
        }
    }

}

