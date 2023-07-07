package kr.spring.ch13;

import java.util.Properties;

public class BookClient {
	//프로퍼티
	//Properties는 Map과 다르게 String타입만 고정. 따라서 제네릭 표현 쓸 필요 X.
	private Properties prop;

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return "BookClient [prop=" + prop + "]";
	}
}
