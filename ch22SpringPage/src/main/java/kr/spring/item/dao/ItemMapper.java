package kr.spring.item.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.item.vo.ItemVO;

@Mapper
public interface ItemMapper {
	//상품
	public void insertItem(ItemVO itemVO);
	public int selectItemCount(Map<String,Object> map);
	public List<ItemVO> selectItemList(Map<String,Object> map);
	
	@Select("SELECT * FROM spitem WHERE item_num=#{item_num}")
	public ItemVO selectItem(Integer item_num);
	
	public void updateItem(ItemVO itemVO);
	public void deleteItem(Integer item_num);
	
}
