package project.product.model;

public class ProductVO {
	private int products_num;
	private String category;
	private String products_name;
	private String energy_efcnc;
	private String price;
	private String liter;
	private String image;
	private String old_file;
	public int getProducts_num() {
		return products_num;
	}
	public void setProducts_num(int products_num) {
		this.products_num = products_num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProducts_name() {
		return products_name;
	}
	public void setProducts_name(String products_name) {
		this.products_name = products_name;
	}
	public String getEnergy_efcnc() {
		return energy_efcnc;
	}
	public void setEnergy_efcnc(String energy_efcnc) {
		this.energy_efcnc = energy_efcnc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLiter() {
		return liter;
	}
	public void setLiter(String liter) {
		this.liter = liter;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOld_file() {
		return old_file;
	}
	public void setOld_file(String old_file) {
		this.old_file = old_file;
	}
	@Override
	public String toString() {
		return "ProductVO [products_num=" + products_num + ", category=" + category + ", products_name=" + products_name
				+ ", energy_efcnc=" + energy_efcnc + ", price=" + price + ", liter=" + liter + ", image=" + image
				+ ", old_file=" + old_file + "]";
	}
	
	
	
}
