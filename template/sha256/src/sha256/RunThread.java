package sha256;

import java.util.UUID;

public class RunThread implements Runnable{

	public static String getUUID(){
		UUID uuid  =  UUID.randomUUID(); 
		String s = uuid.toString();
		int p = 0;
		int j = 0;
		char[] buf = new char[32];
		while(p<s.length()){
		 char c = s.charAt(p);
		 p+=1;
		 if(c=='-')continue;
		 buf[j]=c;j+=1;
		}
		return new String(buf);
	}
	
	@Override
	public void run() {
		long time = System.currentTimeMillis();
		String uuid = RunThread.getUUID();
		String min = Sha256.getSHA256StrJava(uuid);
		while(true){
			String str = Sha256.getSHA256StrJava(uuid);
			if (str.compareTo(uuid)<0) {
				min = str;
			}
			if ((System.currentTimeMillis()-time)==15000) {//执行15秒
				System.out.println(Thread.currentThread().getName()+"   "+min);
				break;
			}
		}
		
	}
	
}
