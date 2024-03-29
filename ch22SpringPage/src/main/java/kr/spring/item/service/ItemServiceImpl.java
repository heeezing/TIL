package kr.spring.item.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.item.dao.ItemMapper;
import kr.spring.item.vo.ItemVO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
		
	@Override
	public void insertItem(ItemVO itemVO) {
		itemMapper.insertItem(itemVO);
	}

	@Override
	public int selectItemCount(Map<String, Object> map) {
		return itemMapper.selectItemCount(map);
	}

	@Override
	public List<ItemVO> selectItemList(Map<String, Object> map) {
		return itemMapper.selectItemList(map);
	}

	@Override
	public ItemVO selectItem(Integer item_num) {
		return itemMapper.selectItem(item_num);
	}

	@Override
	public void updateItem(ItemVO itemVO) {
		itemMapper.updateItem(itemVO);
	}

	@Override
	public void deleteItem(Integer item_num) {
		// TODO Auto-generated method stub
		
	}

}
