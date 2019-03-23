package controller;

import java.util.ArrayList;

import model.*;
public class Controller
{
    private Model model;

    // initializing model class reference
    public void setModel(Model m)
    {
        this.model = m;
    }

    // requesting model to reset
    public void setRequest()
    {
        model.ResetModel();
    }

    // requesting model to update board
    public void setRequest(ArrayList<Integer> position)
    {
        model.PlayMove(position.get(0), position.get(1));
    }

}
