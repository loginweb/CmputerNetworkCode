/*
 * ʵ�ּ��������������·��CRC��ѭ������У�飩
 * 2018-04-06
 * zhl
 */
package crc.zhl;

public class TestCRC {
//ʵ���ַ������ģ�����㣨���
//	a��b�ֱ��Ǳ������ͳ�����a��b������ͬ
	public String divs(String a, String b) {
		String str=null;
		str=a;
//		System.out.println("a is:"+a);
//		System.out.println("b is:"+b);
//		���ַ�����ָ��λ�õ��ַ������滻����ת����Ϊ�ַ�����
//		���ַ�������в�������ɺ��ַ�����ת��Ϊ�ַ���
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
			System.out.println("�����ͱ������ַ������Ȳ���ͬ����");
			System.exit(0);
		}
		str = new String(arr);
		return str;
	}

//	��ָ���ַ�����p����ģ�����㣬msg��p���Ȳ�ͬ
	public String fcs(String msg, String p) {
		String fcsmsg=null;
//		zeroΪ��������һ���ַ�Ϊ0ʱ����������ĳ���
		String zero="0";
//		tmp��Ϊ��һ�μ���ʱ�������������ӵ���һλ
//		��Ҫ��msg��Ӧλ��ȡֵ�������ӵ��µı���������
		char [] tmp= {'0'};
		int msgLen,pLen;
		
		msgLen = msg.length();
		pLen = p.length();
		
		//��������ȫ0�ַ��������ȵ��ڳ����ĳ���,zero�������һ��0����ӳ��ȼ�һ��
		for(int i=0;i<pLen-1;i++) {
			zero = zero + "0";
		}

		fcsmsg = msg.substring(0, pLen-1);
		for(int j=0;j<msgLen-pLen+1;j++) {
			tmp[0] = msg.charAt(pLen+j-1);
			fcsmsg = fcsmsg + new String(tmp);
//			���ݱ��������ַ���ѡ�����
			if(fcsmsg.charAt(0) == '1')
				fcsmsg = divs(fcsmsg, p);
			else
				fcsmsg = divs(fcsmsg, zero);
			fcsmsg = fcsmsg.substring(1);
		}
				
		return fcsmsg;
	}
	
//	���ط��Ͷ���Ҫ���͵��ַ�������������fcs
	public String sendStr(String msg, String p) {
		String sends = null;
		String newmsg = null;
		char [] tmp= {'0'};
		int pLen;
		
		System.out.println("ԭʼ������:"+msg);
		System.out.println("����p:"+p);
		newmsg = msg;
		pLen = p.length();
//		�³���β��������Ӧ������0������Ϊ��������-1
		for(int i=0;i<pLen-1;i++) {
			newmsg = newmsg + new String(tmp);			
		}
		System.out.println("�µı�����:"+newmsg);
		sends = msg + fcs(newmsg, p);
		return sends;
	}
	
	public boolean checkStr(String recive, String p) {
		String zero = "0";
//		�˴�zero���ڼ�����Ƿ�ȫΪ0�����ȵ��ڳ������ȼ�1
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
		System.out.println("���͵��ַ���:"+ tt);
	    boolean result = tc.checkStr(tt, p);
		System.out.println(result);
	}

}
