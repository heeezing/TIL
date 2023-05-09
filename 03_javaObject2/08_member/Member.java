package kr.s08.member;

public class Member {
	/*
	 * [실습]
	 * 멤버변수 : 이름(name), 나이(age), 직업(job), 주소(address), 전화번호(phone)
	 * 멤버메서드 : get/set 메서드 정의
	 * 
	 */
	 private String name;
	 private int age;
	 private String job;
	 private String address;
	 private String phone;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	 
}
