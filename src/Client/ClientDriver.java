package Client;



import Util.ClientUtil;
import Util.MessageSender;

    public class ClientDriver {
        public static void main(String[] args) {
            Run r = new Run();
            r.setClient(new ClientUtil() {
                @Override
                public void handle(MessageSender ms) {
                    r.handle(ms);
                }
            });
            r.showMenu();
            String choice = null;
            while (!(choice = r.getChoice()).equals("0")) {
                r.execute(choice);
                System.out.println();
                r.showMenu();
            }
            r.closeScanner();
            System.out.println("Goodbye!");
        }
    }

