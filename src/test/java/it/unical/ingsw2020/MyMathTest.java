package it.unical.ingsw2020;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unical.ingsw2020.MyMath;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

public class MyMathTest {

	private static MyMath myMath;
	
	@Mock
	private ConnectionManager connectionManagerMock;
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@BeforeClass
	public static void prepareAll() {
		System.out.println("before class");
		//myMath = new MyMath();
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("after class");
	}

	@Before
	public void prepareTest() {
		System.out.println("before");
		myMath = new MyMath(connectionManagerMock);
	}

	@After
	public void cleanTest() {
		System.out.println("after");
	}

	@Test(expected = IllegalArgumentException.class)
	public void fibonacciThrowsException() {
		System.out.println("fibonacciThrowsException");
		myMath.fibonacci(-1);
	}

	@Test
	public void fibonacciWorks() {
		System.out.println("testing that fibonacci works");
		assertEquals(1, myMath.fibonacci(1));
		assertEquals(8, myMath.fibonacci(5));
		assertEquals(13, myMath.fibonacci(6));
	}

	@Test
	public void factorialWorks() {
		System.out.println("testing that factorial works");
		assertEquals(1, myMath.factorial(0));
		assertEquals(120, myMath.factorial(5));
	}

	@Test(timeout = 5000)
	public void fibonacciIsFastEnough() {
		System.out.println("fibonacciIsFastEnough");
		myMath.fibonacci(1);
	}

	@Test
	public void testOnDoubles() {
		System.out.println("testOnDoubles");
		assertEquals(0.3, 0.1 + 0.1 + 0.1, 0.001);
	}
	
	/**
	 * Just to show that we can modify the Mocked instance behavior
	 */
	@Test
	public void remoteFactorialWorks() {
		System.out.println("testing that remoteFactorial Works");	
		when(connectionManagerMock.isNetworkConnected()).thenReturn(true);
		assertEquals(12345678, myMath.remoteFactorial(2));
	}

	/**
	 * Just to show that we can modify the Mocked instance behavior
	 */
	@Test(expected = RuntimeException.class)
	public void remoteFactorialThrowsException() {
		System.out.println("testing that remoteFactorial throws");	
		when(connectionManagerMock.isNetworkConnected()).thenReturn(false);
		myMath.remoteFactorial(5);
	}
	
}
