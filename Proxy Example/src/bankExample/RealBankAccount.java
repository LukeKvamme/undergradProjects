package bankExample;

public class RealBankAccount implements Bank {

	@Override
	public void withdrawFrom(String bankAccount) {
		System.out.println("Routing to account from " + bankAccount + "...");
	}

}
