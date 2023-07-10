package kr.spring.ch11.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.spring.ch11.vo.PageRank;

public class PageRanksView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, 
									  HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		//시트 생성
		HSSFSheet sheet = createFirstSheet((HSSFWorkbook)workbook);
		//컬럼 라벨 생성
		createColumnLabel(sheet);
		
		List<PageRank> pageRanks = (List<PageRank>)model.get("pageRanks");
		int rowNum = 1;
		for(PageRank rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
		
		//파일명 지정
		String fileName = "pageRanks2023.xls"; 
		//다운로드 할 수 있도록 헤더 처리
		response.setHeader("Content-Disposition", "attachment;fileName=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}

	
	//시트 생성
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "페이지 순위"); //sheet index, 이름
		//특정 컬럼(두번재 열)의 넓이 지정
		sheet.setColumnWidth(1, 256*20); //column index, 넓이
		return sheet;
	}
	
	
	//컬럼 라벨 생성 (첫번 째 행에 항목 생성)
	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0); //첫번째 행
		
		HSSFCell cell = firstRow.createCell(0); //첫번째 열
		cell.setCellValue("순위");
		cell = firstRow.createCell(1); //두번째 열
		cell.setCellValue("페이지");
	}
	
	
	//행 단위로 데이터 세팅
	private void createPageRankRow(HSSFSheet sheet, PageRank rank, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0); //첫번째 열
		cell.setCellValue(rank.getRank()); //순위
		
		cell = row.createCell(1); //두번째 열
		cell.setCellValue(rank.getPage());
	}

}