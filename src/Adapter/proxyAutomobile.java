package Adapter;

import java.util.Iterator;
import java.util.Map.Entry;

import Exception.AutoException;
import Model.Automobile;
import Model.AutomobileCollection;
import Scale.EditOptions;
import Util.FileIO;

public abstract class proxyAutomobile {
	private AutomobileCollection autos = new AutomobileCollection();

	public void BuildAuto(String fileName, String type) {
		FileIO fileUtil = new FileIO();
		Automobile auto = fileUtil.buildAutomotive(fileName, type);
		autos.put(auto.getMake() + "-" + auto.getModelName(), auto);

	}

	public void printAuto(String makeName, String ModelName) {
		autos.get(makeName + "-" + ModelName).print();
	}

	public void updateOptionSetName(String makeName, String ModelName, String OptionSetName, String newName) {
		// Since there is only one automobile, the file name does not work
		Automobile auto = autos.get(makeName + "-" + ModelName);
		auto.setOptionSetName(auto.findOptionSetPosition(OptionSetName), newName);
	}

	// Sync
	public void updateOptionSetNameSync(String makeName, String ModelName, String OptionSetName, String newName) {
		// Since there is only one automobile, the file name does not work
		Automobile auto = autos.get(makeName + "-" + ModelName);
		synchronized (auto) {
			System.out.println("lock: enter" + ", threadName: " + Thread.currentThread().getName());
			auto.setOptionSetName(auto.findOptionSetPosition(OptionSetName), newName);
			System.out.println("lock: exit" + ", threadName: " + Thread.currentThread().getName());
		}
	}

	public void updateOptionPrice(String makeName, String ModelName, String OptionSetName, String OptionName,
			float newPrice) {
		Automobile auto = autos.get(makeName + "-" + ModelName);
		auto.setOption(auto.findOptionSetPosition(OptionSetName), auto.findOptionPosition(OptionName)[1],
				((double) (newPrice)), OptionName);
	}

	// Sync
	public void updateOptionPriceSync(String makeName, String ModelName, String OptionSetName, String OptionName,
			float newPrice) {
		Automobile auto = autos.get(makeName + "-" + ModelName);
		synchronized (auto) {
			System.out.println("lock: enter" + ", threadName: " + Thread.currentThread().getName());
			auto.setOption(auto.findOptionSetPosition(OptionSetName), auto.findOptionPosition(OptionName)[1],
					((double) (newPrice)), OptionName);
			System.out.println("lock: exit" + ", threadName: " + Thread.currentThread().getName());
		}
	}

	public String getOptionChoice(String makeName, String ModelName, String setName) {
		return autos.get(makeName + "-" + ModelName).getOptionChoiceName(setName);
	}

	public int getOptionChoicePrice(String makeName, String ModelName, String setName) {
		return autos.get(makeName + "-" + ModelName).getOptionChoicePrice(setName);
	}

	public void setOptionChoice(String makeName, String ModelName, String setName, String optionName) {
		autos.get(makeName + "-" + ModelName).setOptionChoice(setName, optionName);
	}

	// Sync
	public void setOptionChoiceSync(String makeName, String ModelName, String setName, String optionName) {
		Automobile auto = autos.get(makeName + "-" + ModelName);
		synchronized (auto) {
			System.out.println("lock: enter" + ", threadName: " + Thread.currentThread().getName());
			auto.setOptionChoice(setName, optionName);
			System.out.println("lock: exit" + ", threadName: " + Thread.currentThread().getName());
		}
	}

	public int getTotalPrice(String makeName, String ModelName) {
		return autos.get(makeName + "-" + ModelName).getTotalPrice();
	}

	public void multi(int opnumber, String[] x, String threadName, boolean isSync, int... delay) {
		int delayValue = 0;
		// If no arguments are passed, the array length is 0
		if (delay.length > 0) {
			delayValue = delay[0];
		}
		new EditOptions(this, opnumber, x, threadName, isSync, delayValue);
	}

	public void fix(int errno) {
		AutoException ae = new AutoException();
		ae.fix(errno);
	}

	public void addModelToSystem(Automobile auto) {
		autos.put(auto.getMake() + "-" + auto.getModelName(), auto);
	}

	public String getModelNameList() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, Automobile>> it = autos.iterator();
		int count = 0;
		while (it.hasNext()) {
			Entry<String, Automobile> entry = it.next();
			sb.append(entry.getKey()).append("\n");
			count++;
		}
		sb.insert(0, "Available automobiles count: " + count + "\n");
		return sb.toString();
	}

	public Automobile getAuto(String makeName, String modelName) {
		return autos.get(makeName + "-" + modelName);
	}
}
