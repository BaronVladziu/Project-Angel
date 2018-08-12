package Window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        super("Project-Angel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        setLayout(new BorderLayout());
        //add()
        pack();
        setVisible(true);
    }

}
