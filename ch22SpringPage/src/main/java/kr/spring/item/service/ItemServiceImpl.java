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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectItemCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItemVO> selectItemList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemVO selectItem(Integer item_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateItem(ItemVO itemVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(Integer item_num) {
		// TODO Auto-generated method stub
		
	}

}
