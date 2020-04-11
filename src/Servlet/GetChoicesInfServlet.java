package Servlet;

import Model.Automobile;
import Model.OptionSet;
import Server.Run;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetChoicesInfServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        if (make == null || model == null) {
            System.out.println("[Error][getChoicesInf] Parameter missing!");
            return;
        }
        Automobile automobile = Run.proxy.getAuto(make, model);
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.write(generateOpSetJSON(automobile));
        pw.flush();
        pw.close();
    }

    private String generateOpSetJSON(Automobile automobile) {
        AutoDTO autoDTO = new AutoDTO();
        int opSetSize = automobile.getoptionSetSize();
        for (int i = 0; i < opSetSize; i++) {
            OptionSet optionSet = automobile.getOptionSet(i);
            int opSize = automobile.getOptionsArray(i).size();
            OpSetDTO opSetDTO = new OpSetDTO(automobile.getOptionSetName(i));
            for (int j = 0; j < opSize; j++) {
                opSetDTO.getOptions().add(automobile.getOptionName(i, j));
            }
            autoDTO.getOptionSets().add(opSetDTO);
        }
        return JSON.toJSONString(autoDTO);
    }
}

class AutoDTO {
    private ArrayList<OpSetDTO> optionSets = new ArrayList<>();

    public AutoDTO() {
        super();
    }

    public ArrayList<OpSetDTO> getOptionSets() {
        return optionSets;
    }

    public void setOptionSets(ArrayList<OpSetDTO> optionSets) {
        this.optionSets = optionSets;
    }

}

class OpSetDTO {
    private String name;
    private ArrayList<String> options = new ArrayList<>();

    public OpSetDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}