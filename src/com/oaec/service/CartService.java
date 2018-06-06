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
		 * 1.�������빺�ﳵ֮ǰ,�жϸ��û����ﳵ�Ƿ�Ϊ��(carts�����status�ֶ��Ƿ�Ϊ0,����status=0������,
		 * �����޸�,��û��,�����),����һ��cartflag��־,�ж��Ƿ���ӳɹ�
		 */

		int cartflag = 0;// ���ﳵ�Ƿ���ӳɹ��ı�־,1Ϊ�ɹ�
		Map<String, Object> cartmap = cd.findCartByStatus(account_id);
		// �жϹ��ﳵstatus=0�ļ�¼�Ƿ����,��������,����ӹ��ﳵ(���û�),����ӹ�����(�󶨹��ﳵid,��book_id)
		if (cartmap.size() < 1) {
			System.out.println("CartService:cartmap.size()--"+cartmap.size());
			cartflag = cd.newCartsandItem(account_id, bid);
		} // ���ﳵstatus=0�ļ�¼����,���"���빺�ﳵ"��,�ı��Ӧcart_item������
		else{
			// �õ�cart_id
			int cart_id = Integer.parseInt(cartmap.get("CID").toString());
			// ����鼮�Ĺ�����,��Ȼ�Ѵ��ڹ��ﳵ����ô��Ҫ���жϸù��ﳵ���Ƿ��ж�Ӧ�鼮��Ϣ
			Map<String, Object> itemmap = cd.findItemByID(cart_id, bid);
			// ���鼮�Ѵ���,���ڵ��"���빺�ﳵ"��,�ı��鼮��Ӧ��item��quantity(+1)
			if (itemmap.size() > 0) {
				int quantity = Integer.parseInt(itemmap.get("QUANTITY").toString());
				System.out.println("CartService:�Ѿ���"+bid+"�Ȿ��,������1");
				cd.changeQuantity(quantity+1, cart_id, bid);
			}
			// ��û���鼮��Ӧ�Ĺ�����,������鼮�Ĺ�����,����quantity����Ϊ1;
			else {
				cd.addItem(cart_id, bid);
			}
		}

		List<CartDetails> list = null;
		// �������ʵ����:��ӹ��ﳵ,����鼮��Ӧ�Ĺ�����,���ﳵ�빺�������,�޸Ĺ�����������Ȳ���
		// ��ѯ�����"���빺�ﳵ" �����ݿ�ı������,������CartItemʵ����
		// ͨ��account_id�Լ����ﳵ��status��ѯ������Ҫ����Ϣ
		list = cd.findCartDetails(account_id);
		return list;

	}

	public List<CartDetails> findCartsByAccountID(int account_id) {
		CartDao cd = new CartDao();
		List<CartDetails> itemlist=null;//�������ղ�ѯ����items
		// �жϹ��ﳵstatus=0�ļ�¼�Ƿ����
		Map<String, Object> cartmap = cd.findCartByStatus(account_id);
		//���ﳵ������,����ʾΪ��
		if (cartmap.size() < 1) {
			System.out.println("���ﳵΪ��,CartService:cartmap.size()--"+cartmap.size());	
			return null;
		} // ���ﳵstatus=0�ļ�¼����,���"���빺�ﳵ"��,�ı��Ӧcart_item������
		else {
			//��Ȼ�Ѵ��ڹ��ﳵ����ô��Ҫ���жϸù��ﳵ���Ƿ����鼮��Ϣ
			itemlist = cd.findCartDetails(account_id);
			
		}
		return itemlist;
	}
	/**
	 * �޸�ͼ������
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
	 * ͨ���û�id��ȡ���ﳵid
	 * @param account_id
	 * @return
	 */
	public Map<String, Object> findCartID(int account_id) {
		CartDao cd = new CartDao();
		
		return cd.findCartID(account_id);
	}
	/**
	 * ��ȡcartDetail
	 * @param account_id
	 * @param book_id
	 * @return
	 */
	public Map<String,Object> getSubtotal(int account_id, int book_id) {
		CartDao cd = new CartDao();
		
		return cd.getSubtotal(account_id,book_id);
	}

}
