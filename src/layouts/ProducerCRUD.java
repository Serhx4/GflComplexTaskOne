package layouts;

import dao.DataDao;
import dao.ProducerDao;
import entities.Producer;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProducerCRUD {
    private JLabel idLabel;
    private JTextField producerNameText;
    private JButton editButton;
    private JButton deleteButton;
    private JComboBox countryBox;
    private JPanel producerCrudPanel;
    private JButton saveButton;
    private Producer producer;

    public ProducerCRUD(Producer producer) {
        this.producer = producer;

        idLabel.setText(producer.getId() + "");
        producerNameText.setText(producer.getName());
        for (Producer.Country country : Producer.Country.values()) {
            countryBox.addItem(country);
        }
        countryBox.setSelectedItem(producer.getCountry());

        editButton.addActionListener(editListener());
        saveButton.addActionListener(saveListener());
        deleteButton.addActionListener(deleteListener());
    }

    public ProducerCRUD emptyForm() {
        idLabel.setText(Producer.getIdAutoIncrement() + "");
        deleteButton.setVisible(false);
        setButtonsCondiiton(true);
        return this;
    }

    public JPanel getProducerCrudPanel() {
        return producerCrudPanel;
    }

    private ActionListener editListener() {
        return e -> {
            setButtonsCondiiton(true);
        };
    }

    private ActionListener saveListener() {
        return e -> {
            producer.setId(Integer.parseInt(idLabel.getText()));
            producer.setName(producerNameText.getText());
            producer.setCountry((Producer.Country)countryBox.getSelectedItem());
            ProducerDao.save(producer);
            setButtonsCondiiton(false);
        };
    }

    private ActionListener deleteListener() {
        return e -> {
            DataDao.deleteProducerAndSouvenirs(producer);
            GraphInterface.clearProducerHolder();
        };
    }

    private void setButtonsCondiiton(boolean condiiton) {
        producerNameText.setEditable(condiiton);
        editButton.setVisible(!condiiton);
        saveButton.setVisible(condiiton);
    }
}
