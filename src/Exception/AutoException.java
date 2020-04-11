package Exception;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Adapter.FixAuto;

public class AutoException extends Exception{
	private static final long serialVersionUID = 1L;
	public final static SimpleDateFormat TIME_SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
	private int errorno;
	private String errormsg;
	public final static int FILE_NOT_FOUND = 1;
	public final static int BASE_PRICE_NULL = 2;
	public final static int OPTION_PRICE_NULL = 3;
	public final static int OPTION_PRICE_FORMAT_ERROR = 4;
	public final static int OPTION_SET_NOT_BE_DEFINDED = 5;
	public final static int NOT_SERIALIZABLE_EXCEPTION = 6;
	public final static int OPTION_MISSING=7;

	public AutoException() {
		super();
		
	}

	public AutoException(int errorno) {
		super();
		this.errorno = errorno;
		printmyproblem();
		saveError();
	}

	public AutoException(String errormsg) {
		super();
		this.errormsg = errormsg;
		printmyproblem();
		saveError();
	}

	public AutoException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		printmyproblem();
		saveError();
	}

	public void printmyproblem() {
		System.out.println("AutoException [errorno=" + errorno + ", errormsg=" + errormsg + "]");
	}

	public void saveError() {
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		sb.append(TIME_SDF.format(c.getTime())).append(" -> ");
		sb.append("ErrorCode:").append(this.errorno).append(" ");
		sb.append("ErrorMessage:").append(this.errormsg);
		sb.append("\n");
		// System.err.print(sb.toString());
		try {
			FileOutputStream fos = new FileOutputStream("log.txt", true);
			fos.write(sb.toString().getBytes("UTF-8"));
			fos.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public void fix(int errno) {
		if (errno <= 3) {
			FixHelper f1 = new FixHelper();
			switch (errno) {
			case 1:
				f1.fix1();
				break;
			case 2:
				f1.fix2();
				break;
			case 3:
				f1.fix3();
				break;
			case 4:
				f1.fix4();
				break;
			case 5:
				f1.fix5();
				break;
			case 6:
				f1.fix6();
				break;
			}

		}

	}
}