package com.oaec.pojo;

import java.io.Serializable;
/**
 * ������ʵ����(��Ӧ���ݿ�:order_items��)
 * @author Yechao
 *
 */
public class CartDetails implements Serializable {

	/**
	 * ��������Ϊ���ﳵ������,������ΪӦ�õ�����������ݿ�,һ���û�����Ʒ���빺�ﳵ,����������ݿ���,
	 * ������Ӧ�����û�ID����,���й���������������,�û�ID,ͼ��ID,ͼ������.
	 * �����"�ύ"��ťʱ,��������ݿ�-������(����id,�û�id,�������,�µ�ʱ��,��ɶ���ʱ��,����״̬)
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
