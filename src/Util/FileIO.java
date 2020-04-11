package Util;

import java.io.*;
import java.util.Properties;

import Exception.AutoException;
import Model.Automobile;

public class FileIO {

    /**
     * type from {"prop","my"}
     */
    public Automobile buildAutomotive(String filename, String type) {
        switch (type) {
            case "prop":
                return readPropertiesFile(filename);
            case "my":
                return readMyFile(filename);
        }
        return null;
    }

    public Automobile parseProperties(Properties prop) {
        Automobile car = new Automobile();
        car.setMake(prop.getProperty("MakeName"));
        car.setModelName(prop.getProperty("ModelName"));
        car.setBasePrice(Double.parseDouble(prop.getProperty("BasePrice")));
        int optionSetCount = Integer.parseInt(prop.getProperty("OptionSetCount"));
        car.setOptionSetsArray(car.newOptionSetArray(optionSetCount));
        for (int i = 0; i < optionSetCount; i++) {
            car.setOptionSet(i, car.newOptionSet());
            car.setOptionSetName(i, prop.getProperty("OptionSetName_" + (i + 1)));
            int optionCount = Integer.parseInt(prop.getProperty("OptionCount_" + (i + 1)));
            car.setOptionsArrayList(i, car.newOptionArray(optionCount));
            for (int j = 0; j < optionCount; j++) {
                String param = (i + 1) + "_" + (j + 1);
                car.setOption(i, j, Double.parseDouble(prop.getProperty("OptionPrice_" + param)),
                        prop.getProperty("OptionName_" + param));
            }
        }
        return car;
    }

    public Properties getpropertiesFromFile(String filename) {
        Properties prop = new Properties();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return null;
            }
            FileInputStream in = new FileInputStream(f);
            prop.load(in);
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Automobile readPropertiesFile(String filename) {
        return parseProperties(getpropertiesFromFile(filename));
    }

    public Automobile readMyFile(String fileName) {
        FileReader file = null;
        BufferedReader buff = null;
        try {
            Automobile focus = new Automobile();
            String[] b = null;
            String[] c = null;
            file = new FileReader(fileName);
            buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    String[] a = line.split(":");
                    if (a[0].equals("Make name")) {
                        focus.setMake(a[1]);
                    } else if (a[0].equals("Model name")) {
                        // find Model name and pass in
                        focus.setModelName(a[1]);
                    } else if (a[0].equals("Base price")) {
                        // find base price and pass in
                        if (a.length < 2 || a[1] == null || a[1].trim().equals("")) {
                            throw new AutoException(AutoException.BASE_PRICE_NULL, "The base price data is empty!");
                        } else {
                            focus.setBasePrice(Double.parseDouble(a[1]));
                        }
                    } else if (a[0].equals("Option")) {
                        // pass all option name into an array of string
                        b = a[1].split(",");
                        // and create optionsets array
                        focus.setOptionSetsArray(focus.newOptionSetArray(b.length));
                        for (int i = 0; i < b.length; i++) {
                            // create each optionset and give names
                            focus.setOptionSet(i, focus.newOptionSet());
                            focus.setOptionSetName(i, b[i]);
                        }
                    } else {
                        // whether the required OptionSetName is currently found
                        boolean isFound = false;
                        for (int i = 0; i < b.length; i++) {
                            if (b[i].equals(a[0])) {
                                isFound = true;// find it
                                c = a[1].split(",");
                                // create options array
                                focus.setOptionsArrayList(i, focus.newOptionArray(c.length));
                                for (int j = 0; j < c.length; j++) {
                                    // pass in name and price of option and create in the method
                                    String[] f = c[j].split("~");
                                    if (f.length < 2 || f[1] == null || f[1].trim().equals("")) {
                                        throw new AutoException(AutoException.OPTION_PRICE_NULL,
                                                "The price of the option named " + f[0] + " is not exist");
                                    } else {
                                        try {
                                            focus.setOption(i, j, Double.parseDouble(f[1]), f[0]);
                                        } catch (NumberFormatException numberFormatErr) {
                                            new AutoException(AutoException.OPTION_PRICE_FORMAT_ERROR,
                                                    "The number format of the price from the option called " + f[0]
                                                            + " is error!");
                                        }
                                    }
                                }
                            }
                        }
                        if (!isFound) {
                            // a non-existent name from the third line of the txt
                            throw new AutoException(AutoException.OPTION_SET_NOT_BE_DEFINDED,
                                    "The option set named '" + a[0] + "' is not be definited!");
                        }
                    }
                }
            }
            buff.close();
            file.close();
            return focus;
        } catch (FileNotFoundException fileNotFoundError) {
            try {// must close the file
                if (buff != null) {
                    buff.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e1) {
            } catch (ArrayIndexOutOfBoundsException e2) {

            }
            try {
                throw new AutoException(AutoException.FILE_NOT_FOUND, "The AutoMobile data file not found!");
            } catch (AutoException err) {
                err.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {// must close the file
                if (buff != null) {
                    buff.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e1) {
            }
        }
        return null;
    }

    public void writeObject(Automobile auto, String name) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name));
            oos.writeObject(auto);
            oos.close();
        } catch (NotSerializableException nserr) {
            try {
                throw new AutoException(AutoException.NOT_SERIALIZABLE_EXCEPTION,
                        "Automobile or its related classes do not implement serialization!");
            } catch (AutoException myErr) {
                myErr.printStackTrace();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public Automobile loadObject(String name) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
            Automobile auto = (Automobile) ois.readObject();
            ois.close();
            return auto;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    public static String readFile(String fileName) {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, length));
            }
            return sb.toString();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }
}
