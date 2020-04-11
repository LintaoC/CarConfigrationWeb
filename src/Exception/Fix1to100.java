package Exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Fix1to100 {
	public static String autoMobileFileName = null;
	public static Integer intVariableTobeReplace = null;

	public void fix1() {
		File f = new File(System.getProperty("user.dir"));
		File[] ff = f.listFiles();
		for (File file : ff) {
			if (file.isFile()) {
				boolean isCorrect = false;// a flag
				try {
					FileReader fr = new FileReader(file.getAbsolutePath());
					BufferedReader buff = new BufferedReader(fr);
					boolean eof = false;
					while (!eof) {
						String line = buff.readLine();
						if (line == null) {
							eof = true;
						} else {
							String[] a = line.split(":");
							if (a[0].equals("Model name")) {
								isCorrect = true;
								buff.close();
								fr.close();
								break;// the first line in the file is correct
							}
						}
					}
					buff.close();
					fr.close();
				} catch (Exception er1) {
				}
				if (isCorrect) {
					File newFile = new File(autoMobileFileName);
					if (file.renameTo(newFile)) {
						System.out.println("Fix error 1 finished!");
					} else {
						System.out.println("I/O Exception cuz previous file isn't closed!");
					}
					return;
				}
			}
		}
		System.out.println("Error 1 can not be repaired!");
	}

	public void fix2() {
		boolean flag = false;
		try {
			File newFile = new File(autoMobileFileName);
			File file = new File(autoMobileFileName + "temp");
			FileReader fr = new FileReader(newFile);
			BufferedReader buff = new BufferedReader(fr);
			FileOutputStream fos = new FileOutputStream(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					String[] a = line.split(":");
					if (a[0].equals("Base price")) {
						String replaceString = "Base price:" + intVariableTobeReplace + "\n";
						fos.write(replaceString.getBytes("UTF-8"));
					} else {
						fos.write(line.getBytes("UTF-8"));
						fos.write('\n');
					}
				}
			}
			fos.close();
			buff.close();
			fr.close();
			newFile.delete();
			if (file.renameTo(newFile)) {
				System.out.println("Fix error 2 finished!");
				flag = true;
				File fileTemp = new File(autoMobileFileName + "temp");
				fileTemp.delete();
			} else {
				System.out.println("I/O Exception cuz previous file isn't closed!");
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		if (!flag) {
			System.out.println("Error 2 can not be repaired!");
		}
	}

	public void fix3() {
		// TODO
	}

	public void fix4() {
		// TODO
	}

	public void fix5() {
		// TODO
	}

	public void fix6() {
		// TODO
	}
}
