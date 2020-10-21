package it.unical.ingsw2020;

public class MyMath {

	private ConnectionManager cm;

	/**
	 * Creating dependency within class make testing difficult, 
	 * could be better to receive it from outside
	 */
	public MyMath() {		
		//cm = new ConnectionManager();
	}
	
	/**
	 * This constructor is used to receive the ConnectionManager dependency
	 * (Set methods could also be used)
	 */
	public MyMath(ConnectionManager cm) {		
		this.cm = cm;
	}

	public int fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Illegal value " + n);
		}
		if (n == 0 || n == 1) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public int factorial(int n) {

		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	/**
	 * This is a fake remoteFactorial method just to show how to use a mocked class
	 */
	public int remoteFactorial(int n) {
		if(!cm.isNetworkConnected()) {
			throw new RuntimeException ("It's not posible to reach the server");
		}
		return 12345678;
	}

}
