
import model.*;
import view.*;
import controller.*;
import model.*;

public class TicTacToeMVC
{
    public static void main(String args[])
    {
        // Creating components
        Controller controller = new Controller();
        View view = new View();
        Model model = new Model();

        model.registerView(view);
        controller.setModel(model);
        view.setActionListener(controller);
    }
}
