package layouts;

import dao.SouvenirDao;
import entities.Souvenir;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SouvenirForm {
    private JPanel souvenirPanel;
    private JButton addSouvenirButton;
    private JTextField souvenirIdText;
    private JButton findSouvenirButton;

    public SouvenirForm() {
        addSouvenirButton.addActionListener(addSouvenirListener());
        findSouvenirButton.addActionListener(findSouvenirListener());
    }

    public JPanel getSouvenirPanel() {
        return souvenirPanel;
    }

    private ActionListener addSouvenirListener() {
        return e -> {
            GraphInterface.fillSouvenirHolder(new SouvenirCRUD(
                    new Souvenir(Souvenir.SouvenirType.UNKNOWN, -1, LocalDate.now(), 0)
            ).emptyForm()
            );
        };
    }

    private ActionListener findSouvenirListener() {
        return e -> {
            int id = Integer.parseInt(souvenirIdText.getText());
            if (SouvenirDao.get(id).isPresent()) {
                GraphInterface.fillSouvenirHolder(
                        new SouvenirCRUD(SouvenirDao.get(id).get())
                );
            }
        };
    }
}
