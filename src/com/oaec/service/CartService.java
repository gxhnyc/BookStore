package com.oaec.service;

import java.util.List;
import java.util.Map;
import com.oaec.dao.CartDao;
import com.oaec.pojo.CartDetails;

public class CartService {

	// private Map<Integer, CartItem> cartmap = new HashMap();

	public List<CartDetails> joincart(int account_id, int bid) {
		CartDao cd = new CartDao();
		/*
		 *  1.操作加入购物车之前,判断该用户购物车是否为空(carts表里的status字段是否为0,若有status=0的数据,
		 *  则作修改,若没有,则添加),设置一个cartflag标志,判断是否添加成功
		 */

		int cartflag = 0;// 购物车是否添加成功的标志,1为成功
		Map<String, Object> cartmap = cd.findCartByStatus(account_id);
		// 判断购物车status=0的记录是否存在,若不存在,则添加购物车(绑定用户),并添加购物项(绑定购物车id,
		if (cartmap.size() < 1) {
			System.out.println("CartService:cartmap.size()--"+cartmap.size());
			cartflag = cd.newCartsandItem(account_id, bid);
		} // 购物车status=0的记录存在,点击"加入购物车"后,改变对应cart_item的数量
		else{
			// 得到cart_id
			int cart_id = Integer.parseInt(cartmap.get("CID").toString());
			// 添加书籍的购物项,既然已存在购物车，那么就要先判断该购物车下是
			Map<String, Object> itemmap = cd.findItemByID(cart_id, bid);
			// 若书籍已存在,则在点击"加入购物车"后,改变书籍对应的item的quantity(+1)
			if (itemmap.size() > 0) {
				int quantity = Integer.parseInt(itemmap.get("QUANTITY").toString());
				System.out.println("CartService:已经有"+bid+"这本书,数量加1");
				cd.changeQuantity(quantity+1, cart_id, bid);
			}
			// 若没有书籍对应的购物项,则添加书籍的购物项,并将quantity设置为1;
			else {
				cd.addItem(cart_id, bid);
			}
		}

		List<CartDetails> list = null;
		// 以上语句实现了:添加购物车,添加书籍对应的购物项,购物车与购物项关联,修改购物项的数量等操作
				// 查询出点击"加入购物车" 后数据库改变的数据,并储在CartItem实体中
				// 通过account_id以及购物车的status查询出所需要的信息
		list = cd.findCartDetails(account_id);
		return list;

	}

	public List<CartDetails> findCartsByAccountID(int account_id) {
		CartDao cd = new CartDao();
		List<CartDetails> itemlist=null;//用来接收查询到的items
		// 判断购物车status=0的记录是否存在
		Map<String, Object> cartmap = cd.findCartByStatus(account_id);
		//购物车不存在,则显示为空
		if (cartmap.size() < 1) {
			System.out.println("购物车为空,CartService:cartmap.size()--"+cartmap.size());	
			return null;
		} // 购物车status=0的记录存在,点击"加入购物车"后,改变对应cart_item的数量
		else {
			//既然已存在购物车，那么就要先判断该购物车下是否有书籍信息
			itemlist = cd.findCartDetails(account_id);
			
		}
		return itemlist;
	}
	/**
	 * 修改图书数量
	 * @param num
	 * @param account_id
	 * @param book_id
	 * @return
	 */
	public int modQuantity(int num,int cart_id, int book_id) {
		CartDao cd = new CartDao();
		
		return cd.modQuantity(num,cart_id,book_id);
	}
	/**
	 * 通过用户id获取购物车id
	 * @param account_id
	 * @return
	 */
	public Map<String, Object> findCartID(int account_id) {
		CartDao cd = new CartDao();
		
		return cd.findCartID(account_id);
	}
	/**
	 * 获取cartDetail
	 * @param account_id
	 * @param book_id
	 * @return
	 */
	public Map<String,Object> getSubtotal(int account_id, int book_id) {
		CartDao cd = new CartDao();
		
		return cd.getSubtotal(account_id,book_id);
	}
	/**
	 * 删除购物车下的某本图书购物项
	 * @param account_id
	 * @param book_id
	 */
	public void rmBookItem(int account_id, int book_id) {
		CartDao cd = new CartDao();
		
		cd.rmBookItem(account_id,book_id);
		
	}

}
