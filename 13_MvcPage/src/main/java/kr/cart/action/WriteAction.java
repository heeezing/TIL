package kr.cart.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인X
			mapAjax.put("result", "logout");
		}else { //로그인O
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터를 자바빈에 저장
			CartVO cart = new CartVO();
			cart.setItem_num(Integer.parseInt(request.getParameter("item_num")));
			cart.setOrder_quantity(Integer.parseInt(request.getParameter("order_quantity")));
			cart.setMem_num(user_num);
			
			CartDAO dao = CartDAO.getInstance();
			CartVO db_cart = dao.getCart(cart); //장바구니 상세 정보를 가져옴
			if(db_cart == null) { //장바구니에 동일 상품이 안 담겨있을 경우 (데이터 null)
				dao.insertCart(cart);
				mapAjax.put("result", "success");
			}else { //동일 상품이 담겨있을 경우 (데이터 O)
				//재고 수 체크 - ItemDAO 호출
				ItemDAO itemDao = ItemDAO.getInstance();
				ItemVO item = itemDao.getItem(db_cart.getItem_num()); //=구매 상품의 상세 정보
				//구매 수량 합산
				//(기존 장바구니(db)에 저장된 구매 수량 + 새로 입력한 구매 수량) 
				int order_quantity = db_cart.getOrder_quantity() + cart.getOrder_quantity();
				
				if(item.getQuantity() < order_quantity) {
					//상품 재고 수량 < 장바구니에 담은 구매 수량
					mapAjax.put("result", "over_quantity");
				}else {
					cart.setOrder_quantity(order_quantity); //자바빈에 합산된 수량을 저장
					dao.updateCartByItem_num(cart); //합산한 수량을 update
					mapAjax.put("result", "success");
				}
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
