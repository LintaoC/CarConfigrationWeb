package bean;

import Model.Automobile;
import Server.Run;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;

public class UserSelectedBean {
    private Automobile automobile;

    //Data Transfer Object
    public class ChoiceDTO {
        private String opSetName;
        private String opChoiceName;
        private int opChoicePrice;

        public ChoiceDTO(String opSetName, String opChoiceName, int opChoicePrice) {
            this.opSetName = opSetName;
            this.opChoiceName = opChoiceName;
            this.opChoicePrice = opChoicePrice;
        }

        public String getOpSetName() {
            return opSetName;
        }

        public void setOpSetName(String opSetName) {
            this.opSetName = opSetName;
        }

        public String getOpChoiceName() {
            return opChoiceName;
        }

        public void setOpChoiceName(String opChoiceName) {
            this.opChoiceName = opChoiceName;
        }

        public int getOpChoicePrice() {
            return opChoicePrice;
        }

        public void setOpChoicePrice(int opChoicePrice) {
            this.opChoicePrice = opChoicePrice;
        }

        @Override
        public String toString() {
            return "choiceDTO{" +
                    "opSetName='" + opSetName + '\'' +
                    ", opChoiceName='" + opChoiceName + '\'' +
                    ", opChoicePrice=" + opChoicePrice +
                    '}';
        }
    }

    public UserSelectedBean() {
        super();
    }

    public String getBasePrice() {
        return String.valueOf(automobile.getBasePrice());
    }

    public int getTotalPrice() {
        return automobile.getTotalPrice();
    }

    public ArrayList<ChoiceDTO> getChoicesInf() {
        ArrayList<ChoiceDTO> al = new ArrayList<>();
        int opSetSize = automobile.getoptionSetSize();
        for (int i = 0; i < opSetSize; i++) {
            String opSetName = automobile.getOptionSetName(i);
            String opChoiceName = automobile.getOptionChoiceName(opSetName);
            int opChoicePrice = automobile.getOptionChoicePrice(opSetName);
            al.add(new ChoiceDTO(opSetName, opChoiceName, opChoicePrice));
        }
        return al;
    }

    public String getName() {
        return automobile.getMake() + "-" + automobile.getModelName();
    }

    public boolean handle(HttpServletRequest request) {
        try {
            String data = request.getParameter("data");
            data = URLDecoder.decode(data, "utf-8");
            JSONObject jsonObject = JSON.parseObject(data);
            automobile = Run.proxy.getAuto(jsonObject.getString("makeName"), jsonObject.getString("modelName"));
            if (automobile == null) {
                System.out.println("[Error]" + "[handleUserSelected]" + "automobile null");
                return false;
            }
            JSONArray choices = jsonObject.getJSONArray("choices");
            for (int i = 0; i < choices.size(); i++) {
                JSONObject choiceObj = (JSONObject) choices.get(i);
                automobile.setOptionChoice(choiceObj.getString("optionSetName"), choiceObj.getString("choiceName"));
            }
            return true;
        } catch (Exception err) {
            System.out.println("[Error]" + "[handleUserSelected]" + err.getMessage());
        }
        return false;
    }
}


