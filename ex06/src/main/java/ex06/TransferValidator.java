package ex06;

public class TransferValidator {
	public static void performTransfer(Transfer transfer) {
		Tracer.trace();

		// SOLUTION START
		// TODO: Implement your solution here
		Account sourceAccount = transfer.getSourceAccount();
		Account targetAccount = transfer.getTargetAccount();
		int amount = transfer.getAmount();

		boolean isBacked = sourceAccount.checkAmount(amount);
		if (isBacked) {
			TransferExecutor.performTransfer(sourceAccount, targetAccount, amount);
		} else {
			TransferInterface.displayError();
		}
		if (isBacked) {
			TransferInterface.displaySuccess();
		}
		// SOLUTION END

		Tracer.trace();
	}
}
