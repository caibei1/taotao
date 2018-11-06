package sha256;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Test {

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			new Thread(new Test().new RunThread(), "线程" + i).start();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class RunThread implements Runnable {

		/**
		 * 获取去掉-的uuid
		 */
		public String getUUID() {
			UUID uuid = UUID.randomUUID();
			String s = uuid.toString();
			int p = 0;
			int j = 0;
			char[] buf = new char[32];
			while (p < s.length()) {
				char c = s.charAt(p);
				p += 1;
				if (c == '-')
					continue;
				buf[j] = c;
				j += 1;
			}
			return new String(buf);
		}

		// 执行15秒运算
		@Override
		public void run() {
			long time = System.currentTimeMillis();
			RunThread runThread = new RunThread();
			String uuid = runThread.getUUID();
			Test.Sha256 sha256= new Test().new Sha256();
			String min = sha256.getSHA256StrJava(uuid);
			while (true) {
				String str = sha256.getSHA256StrJava(uuid);
				if (str.compareTo(uuid) < 0) {
					min = str;
				}
				if ((System.currentTimeMillis() - time) >= 15000) {// 执行大于15秒
					System.out.println(Thread.currentThread().getName() + "   " + min);
					break;
				}
			}

		}
	}

	class Sha256 {

		/**
		 * 利用java原生的摘要实现SHA256加密
		 * 
		 * @param str
		 *            加密后的报文
		 * @return
		 */
		public  String getSHA256StrJava(String str) {
			MessageDigest messageDigest;
			String encodeStr = "";
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(str.getBytes("UTF-8"));
				encodeStr = byte2Hex(messageDigest.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encodeStr;
		}

		/**
		 * 将byte转为16进制
		 * 
		 * @param bytes
		 * @return
		 */
		private  String byte2Hex(byte[] bytes) {
			StringBuffer stringBuffer = new StringBuffer();
			String temp = null;
			for (int i = 0; i < bytes.length; i++) {
				temp = Integer.toHexString(bytes[i] & 0xFF);
				if (temp.length() == 1) {
					// 1得到一位的进行补0操作
					stringBuffer.append("0");
				}
				stringBuffer.append(temp);
			}
			return stringBuffer.toString();
		}
	}

}
