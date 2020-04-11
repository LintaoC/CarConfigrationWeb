package Model;

import java.io.Serializable;
import java.util.*;

import Exception.AutoException;
import Model.OptionSet.Option;

public class Automobile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String modelName;
    private String make;
    private double basePrice;
    private ArrayList<OptionSet> opset;
    private int opsSize;
    private ArrayList<Option> choice = new ArrayList<Option>();

    public Automobile() {

    }

    public Automobile(String modelName, double basePrice) {
        this.modelName = modelName;
        this.basePrice = basePrice;
    }

    public Automobile(String modelName, double basePrice, int size) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        opset = new ArrayList<OptionSet>(size);
        opsSize = size;
    }

    public Automobile(String modelName, double basePrice, int size, ArrayList<OptionSet> optionSet) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        opset = optionSet;
        opsSize = size;
    }

    public String getOptionChoiceName(String optionSetName) {
        Option optionChoice = findOptionSet(optionSetName).getOptionChoice();
        if (optionChoice == null) {
            return "No";
        }
        return optionChoice.getOptionName();
    }

    public int getOptionChoicePrice(String setName) {
        OptionSet os = findOptionSet(setName);
        if (os == null) {
            System.err.println("[Error]option set isn't exist!");
            return -1;
        }
        Option optionChoice = os.getOptionChoice();
        if (optionChoice == null) {
            return 0;
        }
        return (int) (optionChoice.getPrice());
    }

    public void setOptionChoice(String setName, String optionName) {
        OptionSet a = findOptionSet(setName);
        boolean hasFound = false;
        for (int i = 0; i < choice.size(); i++) {
            if (choice.get(i).equals(a.getOptionChoice())) {
                choice.set(i, a.findOption(optionName));
                hasFound = true;
            }
        }
        if (hasFound == false) {
            choice.add(a.findOption(optionName));
        }
        a.setOptionChoice(optionName);
    }

    public int getTotalPrice() {
        int price = (int) basePrice;
        for (Option a : choice) {
            price += a.getPrice();
        }
        return price;
    }

    public String getModelName() {
        return modelName;
    }

    public String getMake() {
        return make;
    }

    public int getoptionSetSize() {
        opsSize = opset.size();
        return opsSize;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public ArrayList<OptionSet> OptionSetsArray() {
        return opset;
    }

    public OptionSet getOptionSet(int index) {
        return opset.get(index);
    }

    public ArrayList<Option> getOptionsArray(int index) {
        return opset.get(index).getOptionsArray();
    }

    public Option getOption(int optionSetIndex, int optionIndex) {
        return opset.get(optionSetIndex).getOption(optionIndex);
    }

    public String getOptionName(int optionSetIndex, int optionIndex) {
        return opset.get(optionSetIndex).getOption(optionIndex).getOptionName();
    }

    public String getOptionSetName(int index) {
        return opset.get(index).getOptionSetName();
    }

    public void setModelName(String name) {
        modelName = name;
    }

    public void setMake(String name) {
        make = name;
    }

    public void setOptionSetSize(int size) {
        opsSize = size;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setOptionSetsArray(ArrayList<OptionSet> optionSet) {
        opset = optionSet;
        opsSize = optionSet.size();
    }

    public void setOptionSet(int index, OptionSet optionSet) {
        opset.add(index, optionSet);
    }

    public void setOption(Option option, int optionSetIndex, int optionIndex) {
        opset.get(optionSetIndex).setOption(optionIndex, option);
    }

    public void setOptionSetName(int index, String name) {
        opset.get(index).setName(name);
    }

    public void setOptionSize(int index, int size) {
        opset.get(index).setOptionSize(size);
    }

    public void setOptionsArrayList(int index, ArrayList<Option> optionArray) {
        opset.get(index).setOptionsArray(optionArray);
    }

    public void setOption(int optionSetIndex, int optionIndex, Option option) {
        opset.get(optionSetIndex).setOption(optionIndex, option);
    }

    public void setOption(int optionSetIndex, int optionIndex, double Optionprice, String optionName) {
        opset.get(optionSetIndex).setOption(optionIndex, Optionprice, optionName);
    }

    public ArrayList<OptionSet> newOptionSetArray(int size) {
        return new ArrayList<OptionSet>(size);
    }

    public OptionSet newOptionSet() {
        return new OptionSet();
    }

    public ArrayList<Option> newOptionArray(int size) {
        return new ArrayList<Option>(size);
    }

    public OptionSet findOptionSet(String name) {
        Iterator<OptionSet> it = opset.iterator();
        while (it.hasNext()) {
            OptionSet a = it.next();
            if (a.getOptionSetName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public Option findOption(String name) {
        Iterator<OptionSet> it1 = opset.iterator();
        while (it1.hasNext()) {
            OptionSet a = it1.next();
            Iterator<Option> it2 = a.getOptionsArray().iterator();
            while (it2.hasNext()) {
                Option b = it2.next();
                if (b.getOptionName().equals(name)) {
                    return b;
                }
            }
        }

        return null;
    }

    public Option findOption(String optionSetName, String optionName) {
        return findOptionSet(optionSetName).findOption(optionName);
    }

    public int findOptionSetPosition(String name) {
        for (OptionSet optionSet : opset) {
            if (optionSet.getOptionSetName().equals(name))
                ;
        }
        return -1;
    }

    public int[] findOptionPosition(String name) {
        for (int i = 0; i < opset.size(); i++) {
            for (int j = 0; j < opset.size(); j++) {
                if (opset.get(i).getOption(j).getOptionName().equals(name)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public void deleteOption(String name) {
        int[] position = findOptionPosition(name);
        ArrayList<Option> temp = getOptionsArray(position[0]);
        temp.remove(position[1]);
    }

    public void deleteOptionSet(String name) {
        opset.remove(findOptionSetPosition(name));
    }

    public void updateOptionSet(OptionSet optionSet) {
        opset.set(findOptionSetPosition(optionSet.getOptionSetName()), optionSet);
    }

    public void updateOption(Option option) {
        int position[] = findOptionPosition(option.getOptionName());
        opset.get(position[0]).setOption(position[1], option);
    }

    public String toString() {
        StringBuffer setString = new StringBuffer();
        setString.append("Model Name is ");
        setString.append(getModelName()).append(" and Base Price is ").append(getBasePrice());
        setString.append(" and OptionSet size is ").append(getoptionSetSize());
        String b = setString.toString();// convert back to String
        return b;
    }

    public void print() {
        System.out.println(toString());// print out the properties of automotive class
        try {
            for (int i = 0; i < opset.size(); i++) {// print properties of every optionsets
                System.out.println(opset.get(i).toString());
                if (opset.get(i).getOptionsArray() == null) {
                    throw new AutoException(AutoException.OPTION_MISSING, "Options missing or extra Option set");
                }
                for (int j = 0; j < opset.get(i).getOptionsArray().size(); j++) {
                    // print properties of every options
                    System.out.println(getOption(i, j).toString());
                }
            }
        } catch (AutoException a) {
            a.printStackTrace();
        }
    }
}