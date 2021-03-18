package project.basket.model;

public class BasketVO {
	private int basketID;
	private String m_id;
	private int goods_num;
	private String goods_name;
	private String price;
	private String image;
	public int getBasketID() {
		return basketID;
	}
	public void setBasketID(int basketID) {
		this.basketID = basketID;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "BasketList [basketID=" + basketID + ", m_id=" + m_id + ", goods_num=" + goods_num + ", goods_name="
				+ goods_name + ", price=" + price + ", image=" + image + "]";
	}
}
