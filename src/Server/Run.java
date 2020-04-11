package Server;

import java.util.Properties;

import Adapter.BuildAuto;
import Model.Automobile;
import Util.MessageSender;
import Util.ServerUtil;

public class Run {
    public static BuildAuto proxy = new BuildAuto();
    private final static MessageSender ERROR_FUNCTION_NO_EXIST = new MessageSender("error", "function doesn't exist!");
    private final static MessageSender ERROR_EXCEPTION = new MessageSender("error", "Internal server error!");
    private final static String TEXT_INFO_RECIEVE_SUCCESS = "Your request has been received by the server!";

    public MessageSender handle(MessageSender ms) {
        try {
            String code = ms.getPostType();
            Object object = ms.getContent();
            switch (code) {
                case "buildModel": {
                    BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions();
                    Automobile mobile = buildCarModelOptions.parseAuto((Properties) object);
                    buildCarModelOptions.addModelToSystem(proxy, mobile);
                    return new MessageSender("buildModel", TEXT_INFO_RECIEVE_SUCCESS);
                }
                case "getModelList": {
                    return new MessageSender("buildModel", proxy.getModelNameList());
                }
                case "getModel": {
                    String[] ss = ((String) object).split("-");
                    Automobile am = proxy.getAuto(ss[0], ss[1]);
                    return new MessageSender("getModel", am);
                }
                default:
                    return ERROR_FUNCTION_NO_EXIST;
            }
        } catch (Exception err) {
            err.printStackTrace();
            return ERROR_EXCEPTION;
        }
    }

    // main
  /*  public static void main(String[] args) {
        Run r = new Run();
        ServerUtil server = new ServerUtil() {
            @Override
            public MessageSender handle(MessageSender ms) {
                return r.handle(ms);
            }
        };
    }*/
}
