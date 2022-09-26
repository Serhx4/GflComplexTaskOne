package layouts;


import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    MainFrame() {
        super();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 400, 1000, 800);
        this.setTitle("Souvenirs");
        this.revalidate();
        this.setVisible(true);
    }
}
