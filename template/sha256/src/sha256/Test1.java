package sha256;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.LinkedList;

public class Test1 {

	public static void main(String[]args){
		LinkedList<String> hashList = new LinkedList<>();
		hashList.add("e");
		hashList.add("a");
		hashList.add("x");
		hashList.add("p");
		int length = hashList.size();
		for (int i = 0;i<hashList.size();i++){
			String minHash = hashList.get(i);
			int min = i;
			for (int j = i;j<length-i;j++){
				if((hashList.get(j).compareTo(hashList.get(min)))<0){
					//minHash = hashList.get(j).getHash();
					min = j;
				}
				if(i!=min){
					String a = hashList.get(min);
					hashList.set(min, hashList.get(i));
					hashList.set(i, a);
				}
			}
		}
		
		for (int i = 0; i < hashList.size(); i++) {
			System.out.println(hashList.get(i));
		}
	}
	
	
}
