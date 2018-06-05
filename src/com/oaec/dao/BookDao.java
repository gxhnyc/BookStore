package com.oaec.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.oaec.pojo.Books;
import com.oaec.util.MRowMapper;
import com.oaec.util.MyJDBCTemp;

/**
 * 
 * @author Yechao
 *
 */
public class BookDao {

	@SuppressWarnings("unchecked")
	public List<Books> findBooks(int pagenum) {

		System.out.println("BookDao-----findBokks(int )");

		MyJDBCTemp jdbc = new MyJDBCTemp();
		List<Books> list = jdbc.queryForList(new MRowMapper() {

			@Override
			public Object mappingRow(ResultSet rs) throws Exception {
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setDescription(rs.getString("description"));
				// System.out.println(rs.getString("instroduction"));
				book.setInstroduction(rs.getString("instroduction"));
				book.setGrade(rs.getInt("grade"));
				book.setPurchase_price(rs.getDouble("purchase_price"));
				book.setSelling_price(rs.getDouble("selling_price"));
				book.setImage(rs.getString("image"));
				book.setIsbn(rs.getString("isbn"));
				book.setBook_concern(rs.getString("book_concern"));
				book.setPublishing_date(rs.getDate("publishing_date"));
				book.setAuthor(rs.getString("author"));
				book.setAuthor_introduction(rs.getString("author_introduction"));

				return book;
			}

		}, "select * from books", null);

		return list;
	}
	// select * from (select a. *,rownum rn from (select * from books )a where
	// rownum<=?) where rn>?

	public List<Books> findBooksByPage(int pagenum) {
		MyJDBCTemp jdbc = new MyJDBCTemp();
		List<Books> list = jdbc.queryForList(new MRowMapper() {

			@Override
			public Object mappingRow(ResultSet rs) throws Exception {
				Books book = new Books();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setDescription(rs.getString("description"));
				// System.out.println(rs.getString("instroduction"));
				book.setInstroduction(rs.getString("instroduction"));
				book.setGrade(rs.getInt("grade"));
				book.setPurchase_price(rs.getDouble("purchase_price"));
				book.setSelling_price(rs.getDouble("selling_price"));
				book.setImage(rs.getString("image"));
				book.setIsbn(rs.getString("isbn"));
				book.setBook_concern(rs.getString("book_concern"));
				book.setPublishing_date(rs.getDate("publishing_date"));
				book.setAuthor(rs.getString("author"));
				book.setAuthor_introduction(rs.getString("author_introduction"));

				return book;
			}

		}, "select * from (select  a. *,rownum rn from (select * from books )a where rownum<=?) where rn>?",
				15 * pagenum, 15 * (pagenum - 1));

		return list;
	}

	public Map<String, Object> findBookByID(String isd) {
		
		int id=Integer.parseInt(isd);
		System.out.println("findbookbyid()-----"+id);
		MyJDBCTemp jdbc = new MyJDBCTemp();
		String sql="select * from books where id=?";
		Map<String,Object> map=jdbc.queryForMap(sql, id);
		//System.out.println(map);
		
		return map;
	}
}
