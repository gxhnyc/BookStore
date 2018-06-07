package com.oaec.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.oaec.pojo.CartDetails;
import com.oaec.util.MRowMapper;
import com.oaec.util.MyJDBCTemp;

public class CartDao {

	/**
	 * 1.通过status查询出购物车(绑定用户id)的信息
	 * 
	 * @param account_id
	 * @return map
	 */
	public Map<String, Object> findCartByStatus(int account_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		return jdbc.queryForMap("select * from carts where status = 0 and account_id= ?", account_id);

	}

	/**
	 * 2.在不存在status=0的购物车情况下,<br>
	 * 添加status=0,绑定account_id的购物车<br>
	 * 及绑定book_id,cart_id的购物项
	 * 
	 * @param account_id
	 * @param bid
	 * @return num
	 */
	public int newCartsandItem(int account_id, int bid) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int num = 0;
		int flag = jdbc.update("insert into carts (cid,account_id,status) values(carts_seq.nextval,?,0)", account_id);
		// 1.添加完成后，需要获得订单的ID为后续的 订单与书籍的关联表做添加
		Map<String, Object> cartmap = jdbc.queryForMap("select * from carts where status=0 and account_id=?",
				account_id);
		int cart_id = Integer.parseInt(cartmap.get("CID").toString());
		// 2.flag>0,若购物车添加成功,则添加绑定book_id及cart_id的购物项
		if (flag > 0) {
			num = jdbc.update("insert into cart_item values(?,?,1)", cart_id, bid);
		} else {
			System.out.println("购物车添加失败!----CartDao.newCartsandItem()");
		}
		return num;
	}
	
	

	/**
	 * 3.通过cart_id,book_id来查找<br>
	 * 是否已存在对应书籍的购物项
	 * @param cart_id
	 * @param bid
	 * @return itemmap
	 */
	public Map<String, Object> findItemByID(int cart_id, int bid) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		Map<String, Object> itemmap = jdbc.queryForMap("select * from cart_item where cart_id=? and book_id=?", cart_id,
				bid);
		return itemmap;
	}

	/**
	 *4.点击"加入购物车"后,修改对应书籍购物项的数量(+1)
	 * 
	 * @param quantity
	 * @param cart_id
	 * @param bid
	 * @return
	 */
	public int changeQuantity(int quantity, int cart_id, int bid) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int num = jdbc.update("update cart_item set quantity=? where cart_id=? and book_id=?", quantity, cart_id, bid);
		return num;
	}

	/**
	 * 5.在购物车下没有找到对应书籍的购物项,则添加书籍对应的购物项,并将quantity设置为1
	 * 
	 * @param cart_id
	 * @param book_id
	 * @return
	 */
	public int addItem(int cart_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int num = jdbc.update("insert into cart_item values(?,?,1)", cart_id, book_id);
		return num;
	}

	/**
	 * 6.根据account_id查询出数据库的数据,以List结果集返回
	 * 
	 * @param account_id
	 * @return List
	 */
	public List<CartDetails> findCartDetails(int account_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		List<CartDetails> list = jdbc.queryForList(new MRowMapper() {

			@Override
			public Object mappingRow(ResultSet rs) throws Exception {
				CartDetails cd = new CartDetails();
				cd.setBook_id(rs.getInt("book_id"));
				cd.setBook_name(rs.getString("book_name"));
				cd.setCart_id(rs.getInt("cart_id"));
				cd.setQuantity(rs.getInt("quantity"));
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				cd.setSubtotal(quantity * price);

				return cd;
			}
		}, "select cs.cid cart_id,b.id book_id,b.name book_name,ci.quantity quantity,b.selling_price price "
				+ "from books b,cart_item ci,carts cs where b.id=ci.book_id and ci.cart_id=cs.cid and "
				+ "cs.account_id=? and cs.status=0", account_id);
		return list;
	}
	/*
	 *删除书籍的购物项
	 */
	public void rmcartitem(int account_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		jdbc.update("delete from cart_item where account_id=?and book_id=?", account_id,book_id);
		
	}
	/**
	 * 修改图书数量的方法 
	 * @param num
	 * @param account_id
	 * @param book_id
	 * @return
	 */
	public int modQuantity(int num,int cart_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int row=0;
		row=jdbc.update("update cart_item set quantity=? where cart_id=? and book_id=?",num, cart_id,book_id);
		return row;
	}
	/**
	 * 获取cart_id
	 * @param account_id
	 * @return
	 */
	public Map<String, Object> findCartID(int account_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();		
		return jdbc.queryForMap("select * from carts where account_id=?", account_id);
	}
	/**
	 * 获取cartDetail
	 * @param book_id 
	 * @param account_id 
	 * @return
	 */
	public Map<String,Object> getSubtotal(int account_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();		
		return jdbc.queryForMap("select cs.cid cart_id,b.id book_id,b.name book_name,ci.quantity quantity,b.selling_price price,ci.quantity*b.selling_price subtotal "
				+ "from books b,cart_item ci,carts cs where b.id=ci.book_id and ci.cart_id=cs.cid and "
				+ "cs.account_id=? and cs.status=0 and ci.book_id=?", account_id,book_id);
	}
	/**
	 * 删除购物车下的某本图书购物项
	 * @param account_id
	 * @param book_id
	 */
	public void rmBookItem(int account_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();		
		jdbc.update("delete from cart_item where cart_id=(select cid cart_id from carts where account_id=? ) and book_id=?",account_id,book_id );
		
	}
	

}
