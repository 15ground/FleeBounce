package FB.STG.Entity;

public class Products {
	int id;
	String name;
	String images;
	int quantity;
	double price;
	String description;
	int categoryID;

	public Products() {
	}

	public Products(int id, String name, String images, int quantity, double price, String description) {
		this.id = id;
		this.name = name;
		this.images = images;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}

	public Products(int id, String name, String images, int quantity, double price, String description,
			int categoryID) {
		this.id = id;
		this.name = name;
		this.images = images;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.categoryID = categoryID;
	}

	public Products(String name, String images, int quantity, double price, String description, int categoryID) {
		this.name = name;
		this.images = images;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.categoryID = categoryID;
	}

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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
}
