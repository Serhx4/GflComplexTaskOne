package layouts;

public class InterfaceController {
    public static void appendLog(String text) {
        GraphInterface.getLogText().append("\n" + text);
    }
    public static void appendLog(Object object) {
        GraphInterface.getLogText().append("\n" + object.toString());
    }
    public static void clearLog() {
        GraphInterface.getLogText().setText("");
    }
}
