package com.oaec.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.oaec.pojo.Books;
import com.oaec.pojo.CartDetails;
import com.oaec.util.MRowMapper;
import com.oaec.util.MyJDBCTemp;

public class CartDao {

	/**
	 * 1.ͨ��status��ѯ�����ﳵ(���û�id)����Ϣ
	 * 
	 * @param account_id
	 * @return map
	 */
	public Map<String, Object> findCartByStatus(int account_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		return jdbc.queryForMap("select * from carts where status = 0 and account_id= ?", account_id);

	}

	/**
	 * 2.�ڲ�����status=0�Ĺ��ﳵ�����,<br>
	 * ���status=0,��account_id�Ĺ��ﳵ<br>
	 * ����book_id,cart_id�Ĺ�����
	 * 
	 * @param account_id
	 * @param bid
	 * @return num
	 */
	public int newCartsandItem(int account_id, int bid) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int num = 0;
		int flag = jdbc.update("insert into carts (cid,account_id,status) values(carts_seq.nextval,?,0)", account_id);
		// 1.�����ɺ���Ҫ��ö�����IDΪ������ �������鼮�Ĺ����������
		Map<String, Object> cartmap = jdbc.queryForMap("select * from carts where status=0 and account_id=?",
				account_id);
		int cart_id = Integer.parseInt(cartmap.get("CID").toString());
		// 2.flag>0,�����ﳵ��ӳɹ�,����Ӱ�book_id��cart_id�Ĺ�����
		if (flag > 0) {
			num = jdbc.update("insert into cart_item values(?,?,1)", cart_id, bid);
		} else {
			System.out.println("���ﳵ���ʧ��!----CartDao.newCartsandItem()");
		}
		return num;
	}
	
	

	/**
	 * 3.ͨ��cart_id,book_id������<br>
	 * �Ƿ��Ѵ��ڶ�Ӧ�鼮�Ĺ�����
	 * 
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
	 * 4.���"���빺�ﳵ"��,�޸Ķ�Ӧ�鼮�����������(+1)
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
	 * 5.�ڹ��ﳵ��û���ҵ���Ӧ�鼮�Ĺ�����,������鼮��Ӧ�Ĺ�����,����quantity����Ϊ1
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
	 * 6.����account_id��ѯ�����ݿ������,��List���������
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
	 * ɾ���鼮�Ĺ�����
	 */
	public void rmcartitem(int account_id, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		jdbc.update("delete from cart_item where account_id=?and book_id=?", account_id,book_id);
		
	}
	/**
	 * �޸�ͼ�������ķ��� 
	 * @param num
	 * @param account_id
	 * @param book_id
	 * @return
	 */
	public int modQuantity(int num,int cid, int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		int row=0;
		row=jdbc.update("update cart_item set quantity=? where cart_id=? and book_id=?",num, cid,book_id);
		return row;
	}
	/**
	 * ͨ���û�id��ȡcart_id
	 * @param account_id
	 * @return
	 */
	public Map<String,Object> findCartID(int account_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		Map<String,Object> map=jdbc.queryForMap("select * from carts where account_id=?", account_id);
		return map;
	}
	/**
	 * ͨ��book_id�ҵ�ͼ��۸�
	 * @param book_id
	 * @return
	 */
	public double findBookPrice(int book_id) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		Books book=(Books) jdbc.queryForObject(new MRowMapper() {

			@Override
			public Object mappingRow(ResultSet rs) throws Exception {
				Books book=new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setSelling_price(rs.getDouble("selling_price"));
				return book;
			}},"select * from books where id=?", book_id);
		//double price=map.get("SELLING_PRICE").toString();
		return book.getSelling_price();
	}
	

}
