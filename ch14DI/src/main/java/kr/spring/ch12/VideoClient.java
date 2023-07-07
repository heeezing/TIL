package kr.spring.ch12;

import java.util.Set;

public class VideoClient {
	//프로퍼티
	private Set<Integer> subset;

	public void setSubset(Set<Integer> subset) {
		this.subset = subset;
	}

	@Override
	public String toString() {
		return "VideoClient [subset=" + subset + "]";
	}
}
