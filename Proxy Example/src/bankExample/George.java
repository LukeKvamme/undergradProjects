package bankExample;

public class George {

	public static void main(String[] args) {
		CreditCard bankAccount = new CreditCard();
		try {
			bankAccount.withdrawFrom("156.143.0.9");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//	public static void main(String[] args) {
//		CreditCard bankAccount = new CreditCard();
//		try {
//			bankAccount.withdrawFrom("127.0.0.1");
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

}
