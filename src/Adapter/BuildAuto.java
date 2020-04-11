package Adapter;

import Model.Automobile;
import Server.AutoServer;

public class BuildAuto extends proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, ChooseAuto, ThreadAuto,AutoServer {

}
