package Server;

import Util.MessageSender;
import Util.ServerUtil;

import javax.servlet.http.HttpSessionListener;

//This class starts with tomcat
public class RunServerListener implements HttpSessionListener {
    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Run r = new Run();
                ServerUtil server = new ServerUtil() {
                    @Override
                    public MessageSender handle(MessageSender ms) {
                        return r.handle(ms);
                    }
                };
            }
        }).start();
    }
}
