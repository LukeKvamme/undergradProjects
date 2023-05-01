package bankExample;

public class CreditCard implements Bank {

	private RealBankAccount realBankAccount = new RealBankAccount();
	private static String localHost = "127.0.0.1";
	DisplayImage person = new DisplayImage();

	/**
	 * Checks if IP is from valid location
	 */
	@Override
	public void withdrawFrom(String bankingLocation) throws Exception {
		String ip = pullIP(bankingLocation);

		if (ip != localHost) {
			person.dox(ip);
			throw new Exception("You can't use this credit card while not at home!");
		}
		realBankAccount.withdrawFrom(bankingLocation);

	}

	/**
	 * Pulls IP
	 * 
	 * @param bankingLocation
	 * @return string
	 */
	public static String pullIP(String bankingLocation) {
		if (!bankingLocation.equals(localHost)) {
			return bankingLocation;
		}
		return localHost;
	}

}
