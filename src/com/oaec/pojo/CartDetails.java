package com.oaec.pojo;

import java.io.Serializable;
/**
 * 购物项实例类(对应数据库:order_items表)
 * @author Yechao
 *
 */
public class CartDetails implements Serializable {

	/**
	 * 购物项作为购物车的数据,个人认为应该单独存放在数据库,一旦用户将商品加入购物车,即存放至数据库中,
	 * 购物项应关联用户ID即可,其中购物项有三个参数,用户ID,图书ID,图书数量.
	 * 当点击"提交"按钮时,存放至数据库-订单表(订单id,用户id,订单金额,下单时间,完成订单时间,订单状态)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int cart_id;
	private int book_id;
	private String book_name;
	private int quantity;
	private double subtotal;
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		
		return "CartItem:[cart_id:"+this.cart_id+",book_id:"+this.book_id+",book_name:"+this.book_name+",quantity:"+this.quantity+",subtotal:"+this.subtotal+"]";
	}
	
	
}
