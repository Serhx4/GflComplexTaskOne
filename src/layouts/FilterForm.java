package layouts;

import dao.DataDao;
import dao.ProducerDao;
import entities.Producer;
import entities.Souvenir;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FilterForm {
    private JButton showAllButton;
    private JButton showProdSouvenirsButton;
    private JComboBox countryBox;
    private JButton showCountrSouvenirsButton;
    private JTextField priceText;
    private JButton showPriceSouvenirButton;
    private JComboBox souvenirBox;
    private JTextField yearText;
    private JButton showSouvenirYearButton;
    private JButton chronologicalButton;
    private JPanel filterPanel;
    private JTextField producerIdText;

    public FilterForm() {
        initialize();
    }

    private void initialize() {
        ProducerDao.getAll().forEach(System.out::println);
        Arrays.stream(Producer.Country.values()).forEach(country -> countryBox.addItem(country));
        Arrays.stream(Souvenir.SouvenirType.values()).forEach(souvenirType -> souvenirBox.addItem(souvenirType));

        showAllButton.addActionListener(showAllListener());
        showProdSouvenirsButton.addActionListener(showSouvenirsByProducerListener());
        showCountrSouvenirsButton.addActionListener(showSouvenirsByCountryListener());
        showPriceSouvenirButton.addActionListener(showProducersByPriceListener());
        showSouvenirYearButton.addActionListener(showProducersByYearListener());
        chronologicalButton.addActionListener(showAllYearsListener());
    }

    private ActionListener showAllListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showAllProducersAndSouvenirs();
        };
    }
    private ActionListener showSouvenirsByProducerListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showAllSouvenirsMadeBy(Integer.parseInt(producerIdText.getText()));
        };
    }
    private ActionListener showSouvenirsByCountryListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showAllSouvenirsMadeIn((Producer.Country) countryBox.getSelectedItem());
        };
    }
    private ActionListener showProducersByPriceListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showProducersWithPricesLower(Double.parseDouble(priceText.getText()));
        };
    }
    private ActionListener showProducersByYearListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showSouvenirProducersAtYear(
                    (Souvenir.SouvenirType) souvenirBox.getSelectedItem(), yearText.getText());
        };
    }
    private ActionListener showAllYearsListener() {
        return e -> {
            InterfaceController.clearLog();
            DataDao.showAllSouvenirsGroupByYear();
        };
    }

    public JPanel getFilterPanel() {
        return filterPanel;
    }
}
