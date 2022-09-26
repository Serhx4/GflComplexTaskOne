package layouts;

import dao.ProducerDao;
import entities.Producer;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProducerForm {
    private JButton addButton;
    private JTextField idText;
    private JButton findButton;
    private JPanel producerPanel;

    public ProducerForm() {
        addButton.addActionListener(addProducerListener());
        findButton.addActionListener(findProducerListener());
    }

    public JPanel getProducerPanel() {
        return producerPanel;
    }

    private ActionListener addProducerListener() {
        return e -> GraphInterface.fillProducerHolder(
                new ProducerCRUD(new Producer(Producer.getIdAutoIncrement(), "", Producer.Country.UNKNOWN))
                        .emptyForm()
        );
    }
    private ActionListener findProducerListener() {
        return e -> {
            int id = Integer.parseInt(idText.getText());
            if (ProducerDao.get(id).isPresent()) {
                GraphInterface.fillProducerHolder(
                        new ProducerCRUD(ProducerDao.get(id).get())
                );
            }
        };
    }
}
