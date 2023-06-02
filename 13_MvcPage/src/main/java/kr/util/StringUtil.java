package kr.util;

public class StringUtil {
	
	/* HTML(태그)를 허용하면서 줄바꿈 */
	public static String useBrHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	
	/* HTML을 허용하지 않으면서 줄바꿈 */
	public static String useBrNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;") //HTML허용X -> 태그 무력화
				  .replaceAll(">", "&gt;")
				  .replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	
	/* HTML을 허용하지 않음 */
	public static String useNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;");
	}
	
	
	/* 큰 따옴표 처리 */
	public static String parseQuot(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\"", "&quot;");
	}
	
	
	/* 문자열을 지정한 개수 이후에 ...으로 처리 */
	public static String shortWords(int length, String content) {
		//내용이 없을 경우
		if(content == null) return null;
		//내용이 지정한 개수보다 클 경우 
		if(content.length() > length) {
			return content.substring(0, length) + " ...";
		}
		//내용이 지정한 개수보다 작거나 같을 경우
		return content;
	}
	
}
