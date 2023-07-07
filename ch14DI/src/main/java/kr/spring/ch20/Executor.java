package kr.spring.ch20;

import org.springframework.beans.factory.annotation.Autowired;

public class Executor {
	//프로퍼티
	@Autowired //(worker를 타입 체크해서 전달받기 위해서 @Autowired를 넣어줌)
	private Worker worker;

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public void addUnit(){
		worker.work();
	}
}
