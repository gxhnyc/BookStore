package com.oaec.pojo;

import java.util.Date;
/**
 * 
 * @author Yechao
 *
 */
public class Books {
	
	 private int id;
	 private String name;//����
	 private String description;//����
	 private String instroduction;//ͼ����
	 private int grade;//���ּ���
	 private double purchase_price;//�����۸�
	 private double selling_price;//���ۼ۸�
	 private String image;//ͼƬ·��
	 private String isbn;//isbnͼ����
	 private String book_concern;//������
	 private Date publishing_date;//����ʱ��
	 private String author;//����
	 private String author_introduction;//���߼��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstroduction() {
		return instroduction;
	}
	public void setInstroduction(String instroduction) {
		this.instroduction = instroduction;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public double getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_concern() {
		return book_concern;
	}
	public void setBook_concern(String book_concern) {
		this.book_concern = book_concern;
	}
	public Date getPublishing_date() {
		return publishing_date;
	}
	public void setPublishing_date(Date publishing_date) {
		this.publishing_date = publishing_date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor_introduction() {
		return author_introduction;
	}
	public void setAuthor_introduction(String author_introduction) {
		this.author_introduction = author_introduction;
	}
	 
	 
}
