package kr.spring.ch06;

public class SystemMonitor {
	private long periodTime;
	private SmsSender sender;
	
	//생성자
	public SystemMonitor(long periodTime, SmsSender sender) {
		this.periodTime = periodTime;
		this.sender = sender;
	}

	//toString() 메서드 활용(재정의)
	@Override
	public String toString() {
		return "SystemMonitor [periodTime=" + periodTime + ", sender=" + sender + "]";
	}
	
}
