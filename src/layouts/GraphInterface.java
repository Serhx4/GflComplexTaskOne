package layouts;

import javax.swing.*;

public class GraphInterface {

    private static JFrame jFrame;
    private static JPanel mainPanel;
    private static LogForm logForm;
    private static JTextArea logText;
    private static JPanel producerPanel;
    private static JPanel producerHolder;
    private static JPanel souvenirHolder;

    public static void setUpInterface(){
        jFrame = new MainFrame();

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Producer Panel
        ProducerForm producerForm = new ProducerForm();
        producerPanel = producerForm.getProducerPanel();
        producerHolder = new JPanel();
        // Souvenir Panel
        SouvenirForm souvenirForm = new SouvenirForm();
        JPanel souvenirPanel = souvenirForm.getSouvenirPanel();
        souvenirHolder = new JPanel();
        //Filter Panel
        FilterForm filterForm = new FilterForm();
        JPanel filterPanel = filterForm.getFilterPanel();

        // Log Panel
        logForm = new LogForm();
        logText = logForm.getLogArea();

        mainPanel.add(producerPanel);
        mainPanel.add(producerHolder);
        mainPanel.add(new JSeparator());
        mainPanel.add(souvenirPanel);
        mainPanel.add(souvenirHolder);
        mainPanel.add(new JSeparator());
        mainPanel.add(filterPanel);
        mainPanel.add(logForm.getLogPanel());

        jFrame.add(mainPanel);
        revalidate();
    }

    public static void fillProducerHolder(ProducerCRUD producerCRUD) {
        clearProducerHolder();
        producerHolder.add(producerCRUD.getProducerCrudPanel());
        revalidate();
    }
    public static void clearProducerHolder() {
        producerHolder.removeAll();
    }

    public static void fillSouvenirHolder(SouvenirCRUD souvenirCRUD) {
        clearSouvenirHolder();
        souvenirHolder.add(souvenirCRUD.getSouvenirCrudPanel());
        revalidate();
    }
    public static void clearSouvenirHolder() {
        souvenirHolder.removeAll();
    }

    public static void revalidate() {
        jFrame.revalidate();
    }

    public static JTextArea getLogText() {
        return logText;
    }
}
