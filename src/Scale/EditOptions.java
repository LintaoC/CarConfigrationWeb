package Scale;

import Adapter.proxyAutomobile;

public class EditOptions extends proxyAutomobile {
	private proxyAutomobile proxy;
	private boolean isSync = false;

	public EditOptions(proxyAutomobile proxy, int opnumber, String[] x, String threadName, boolean isSync, int delay) {
		this.proxy = proxy;
		this.isSync = isSync;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread: " + threadName + " << started!");
				if (delay != 0) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				switch (opnumber) {
				case 0:// updateOptionSetName
					updateOptionSetName(x[0], x[1], x[2], x[3]);
					break;
				case 1:// updateOptionPrice
					updateOptionPrice(x[0], x[1], x[2], x[3], Float.parseFloat(x[4]));
					break;
				case 2:// setOptionChoice
					setOptionChoice(x[0], x[1], x[2], x[3]);
					break;
				default:
					System.err.println("Error: opnumber err!");
				}
				System.out.println("Thread: " + threadName + " << end!");
			}
		}, threadName);
		t.start();
	}

	@Override
	public String getOptionChoice(String makeName, String ModelName, String setName) {
		// Not used in multithreading
		return null;
	}

	@Override
	public int getOptionChoicePrice(String makeName, String ModelName, String setName) {
		// Not used in multithreading
		return 0;
	}

	@Override
	public void setOptionChoice(String makeName, String ModelName, String setName, String optionName) {
		if (isSync) {
			proxy.setOptionChoiceSync(makeName, ModelName, setName, optionName);
		} else {
			proxy.setOptionChoice(makeName, ModelName, setName, optionName);
		}
	}

	@Override
	public int getTotalPrice(String makeName, String ModelName) {
		// Not used in multithreading
		return 0;
	}

	@Override
	public void updateOptionSetName(String makeName, String ModelName, String OptionSetName, String newName) {
		if (isSync) {
			proxy.updateOptionSetNameSync(makeName, ModelName, OptionSetName, newName);
		} else {
			proxy.updateOptionSetName(makeName, ModelName, OptionSetName, newName);
		}
	}

	@Override
	public void updateOptionPrice(String makeName, String ModelName, String OptionSetName, String OptionName,
			float newPrice) {
		if (isSync) {
			proxy.updateOptionPriceSync(makeName, ModelName, OptionSetName, OptionName, newPrice);
		} else {
			proxy.updateOptionPrice(makeName, ModelName, OptionSetName, OptionName, newPrice);
		}
	}
}
