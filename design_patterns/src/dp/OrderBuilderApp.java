package dp;

public class OrderBuilderApp {

	public static void main(String[] args) {
		OrderBuilder b = new OrderBuilder();
		b.setId("23").addProduct(new Product())
		 	.addProduct(new Product()).setPrice(300);
		Order o = b.getOrder();
		System.out.println(o);
	}

}
