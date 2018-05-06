/*
 * 实现计算机网络数据链路层CRC（循环冗余校验）
 * 2018-04-06
 * zhl
 */
package crc.zhl;

public class TestCRC {
//实现字符串间的模二运算（异或）
//	a和b分别是被除数和除数，a和b长度相同
	public String divs(String a, String b) {
		String str=null;
		str=a;
//		System.out.println("a is:"+a);
//		System.out.println("b is:"+b);
//		将字符串中指定位置的字符进行替换，先转换成为字符数组
//		对字符数组进行操作，完成后将字符数组转换为字符串
		char [] arr=str.toCharArray();
		if(a.length()==b.length()) {
			for(int i=0;i<a.length();i++) {
				if(a.charAt(i)==b.charAt(i)) {
					arr[i]='0';
				}else {
					arr[i]='1';
				}
			}
		}else {
			System.out.println("除数和被除数字符串长度不相同！！");
			System.exit(0);
		}
		str = new String(arr);
		return str;
	}

//	对指定字符串和p进行模二运算，msg和p长度不同
	public String fcs(String msg, String p) {
		String fcsmsg=null;
//		zero为被除数第一个字符为0时，参与运算的除数
		String zero="0";
//		tmp作为下一次计算时，被除数所增加的那一位
//		需要从msg对应位置取值，并附加到新的被除数后面
		char [] tmp= {'0'};
		int msgLen,pLen;
		
		msgLen = msg.length();
		pLen = p.length();
		
		//参与计算的全0字符串，长度等于除数的长度,zero本身存在一个0，添加长度减一个
		for(int i=0;i<pLen-1;i++) {
			zero = zero + "0";
		}

		fcsmsg = msg.substring(0, pLen-1);
		for(int j=0;j<msgLen-pLen+1;j++) {
			tmp[0] = msg.charAt(pLen+j-1);
			fcsmsg = fcsmsg + new String(tmp);
//			根据被除数首字符，选择除数
			if(fcsmsg.charAt(0) == '1')
				fcsmsg = divs(fcsmsg, p);
			else
				fcsmsg = divs(fcsmsg, zero);
			fcsmsg = fcsmsg.substring(1);
		}
				
		return fcsmsg;
	}
	
//	返回发送端所要发送的字符串，后面增加fcs
	public String sendStr(String msg, String p) {
		String sends = null;
		String newmsg = null;
		char [] tmp= {'0'};
		int pLen;
		
		System.out.println("原始被除数:"+msg);
		System.out.println("除数p:"+p);
		newmsg = msg;
		pLen = p.length();
//		新除数尾部增加相应数量的0，数量为除数长度-1
		for(int i=0;i<pLen-1;i++) {
			newmsg = newmsg + new String(tmp);			
		}
		System.out.println("新的被除数:"+newmsg);
		sends = msg + fcs(newmsg, p);
		return sends;
	}
	
	public boolean checkStr(String recive, String p) {
		String zero = "0";
//		此处zero用于检查结果是否全为0，长度等于除数长度减1
		for(int i=1;i<p.length()-1;i++) {
			zero = zero + "0";
		}

		if(fcs(recive, p).equals(zero))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCRC tc = new TestCRC();
		String p = "10011";
		String tt = tc.sendStr("1101011011", p);
		System.out.println("发送的字符串:"+ tt);
	    boolean result = tc.checkStr(tt, p);
		System.out.println(result);
	}

}
