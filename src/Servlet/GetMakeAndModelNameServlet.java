package Servlet;

import Server.Run;
import Util.ClientUtil;
import Util.MessageSender;
import com.alibaba.fastjson.JSON;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class GetMakeAndModelNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String list = Run.proxy.getModelNameList();
        String[] array1 = list.split("\\n");
        LinkedList<ModelDTO> modelList = new LinkedList<>();
        for (String s : array1) {
            String[] array2 = s.split("-");
            if (array2.length == 2) {
                modelList.add(new ModelDTO(array2[0], array2[1]));
            }
        }
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.write(JSON.toJSONString(modelList));
        pw.flush();
        pw.close();
    }
}

class ModelDTO {
    private String make;
    private String model;

    public ModelDTO(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
