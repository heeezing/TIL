package kr.spring.item.service;

import java.util.List;
import java.util.Map;

import kr.spring.item.vo.ItemVO;

public interface ItemService {
	//상품
	public void insertItem(ItemVO itemVO);
	public int selectItemCount(Map<String,Object> map);
	public List<ItemVO> selectItemList(Map<String,Object> map);
	public ItemVO selectItem(Integer item_num);
	public void updateItem(ItemVO itemVO);
	public void deleteItem(Integer item_num);
}
