import jscriptlanguage.client.gui.ScriptFrame;
import jscriptlanguage.mainserver.net.ScriptServer;

public class Main {

	public static void main(String[] args) {
		new ScriptFrame().setVisible(true);
		new ScriptServer().start();
	}

}
