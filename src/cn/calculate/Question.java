package cn.calculate;

public class Question {
	private  int a;
	private  int b;
	private  int z;
	private  int anwser;
	private  String xuhao;//题号
	private  String  yoghuzhi;//用户给的结果
	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public String getYoghuzhi() {
		return yoghuzhi;
	}

	public void setYoghuzhi(String yoghuzhi) {
		this.yoghuzhi = yoghuzhi;
	}

	public Question() {
		int result=0;
		do{
			a=(int) (Math.random()*100);
			b=(int) (Math.random()*100);
			z=(int) (Math.random()*2)+1;
			switch (z) {//0代表+,1代表-
			case 2:
				result=a+b;
				break;
	       case 1:
	    	   result=a-b;
				break;
			}
			}while(result<0||result>100);
		anwser=result;
			

	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}

	public int getAnwser() {
		return anwser;
	}

	
	


}
