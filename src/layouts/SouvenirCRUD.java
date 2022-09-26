package layouts;

import dao.ProducerDao;
import dao.SouvenirDao;
import data.ProducerData;
import date.Dates;
import entities.Producer;
import entities.Souvenir;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SouvenirCRUD {
    private JPanel souvenirCrudPanel;
    private JComboBox souvenirTypeBox;
    private JComboBox producerBox;
    private JTextField yearText;
    private JTextField priceText;
    private JButton editButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JLabel idLabel;
    private Souvenir souvenir;

    public SouvenirCRUD(Souvenir souvenir) {
        this.souvenir = souvenir;

        Arrays.stream(Souvenir.SouvenirType.values())
                .forEach(souvenirType -> souvenirTypeBox.addItem(souvenirType));
        souvenirTypeBox.setSelectedItem(souvenir.getSouvenirType());

        ProducerDao.getAll().forEach(producer -> producerBox.addItem(producer));
        producerBox.setSelectedItem(souvenir.getProducer());

        idLabel.setText(souvenir.getId() + "");
        yearText.setText(souvenir.getCreatedAt().getYear() + "");
        priceText.setText(souvenir.getPrice() + "");

        editButton.addActionListener(editListener());
        saveButton.addActionListener(saveListener());
        deleteButton.addActionListener(deleteListener());
    }
    public SouvenirCRUD emptyForm() {
        deleteButton.setVisible(false);
        setPanelConditions(true);
        return this;
    }

    public JPanel getSouvenirCrudPanel() {
        return souvenirCrudPanel;
    }

    private ActionListener editListener() {
        return e -> {
            setPanelConditions(true);
        };
    }
    private ActionListener saveListener() {
        return e -> {
            souvenir.setId(Integer.parseInt(idLabel.getText()));
            souvenir.setSouvenirType((Souvenir.SouvenirType)souvenirTypeBox.getSelectedItem());
            souvenir.setProducerId(((Producer)producerBox.getSelectedItem()).getId());
            souvenir.setCreatedAt(Dates.getDateByYear(yearText.getText()));
            souvenir.setPrice(Double.parseDouble(priceText.getText()));
            SouvenirDao.save(souvenir);
        };
    }
    private ActionListener deleteListener() {
        return e -> {
            SouvenirDao.delete(souvenir.getId());
            GraphInterface.clearSouvenirHolder();
        };
    }

    private void setPanelConditions(boolean condition) {
        souvenirTypeBox.setEnabled(condition);
        producerBox.setEnabled(condition);
        yearText.setEditable(condition);
        priceText.setEditable(condition);

        editButton.setVisible(!condition);
        saveButton.setVisible(condition);
    }
}
