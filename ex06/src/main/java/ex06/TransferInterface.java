package ex06;

public class TransferInterface {
	public static void requestTransfer(Transfer transfer) {
		Tracer.trace();

		// SOLUTION START
		// TODO: Implement your solution here
		TransferValidator.performTransfer(transfer);
		// SOLUTION END

		Tracer.trace();
	}

	public static void displayError() {
		Tracer.trace();

		// SOLUTION START
		// TODO: Implement your solution here
		System.out.println("Transfer Error");
		// SOLUTION END

		Tracer.trace();
	}

	public static void displaySuccess() {
		Tracer.trace();

		// SOLUTION START
		// TODO: Implement your solution here
		System.out.println("Transfer Success");
		// SOLUTION END

		Tracer.trace();
	}
}
