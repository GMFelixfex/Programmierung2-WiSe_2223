
public class Aufgabe2 {

	public static void main(String[] args) {
		
		int n_max = 20;
		
		
		int[] result = execute(new Power(),n_max);
		System.out.println("First Result");
		for (int i = 0; i < result.length; i++) {
			System.out.println("F("+i+")="+result[i]);
		}
		System.out.println();
		int[] result2 = execute(new MultipyTimes3(),n_max);
		System.out.println("Second Result");
		for (int i = 0; i < result2.length; i++) {
			System.out.println("F("+i+")="+result2[i]);
		}
	}
	
	public static int[] execute(F f, int n) {
		Result results = new Result(n);
		for (int i = 0; i < n; i++) {
			Thread t = new ExecuteThread(results,i,f);
			t.start();
		}
		return results.GetResult();
	}

}

class ExecuteThread extends Thread{
	Result result;
	int n;
	F Function;
	
	public ExecuteThread(Result _result, int _n, F _Function) {
		this.result = _result;
		this.n = _n;
		this.Function = _Function;
	}
	
	public void run() {
		int singleResult = Function.f(n);
		result.SetResult(n, singleResult);
	}
}


interface F {
	 public int f (int x);
}

class Power implements F {

	@Override
	public int f(int x) {
		return x*x;
		
	}
	
}

class MultipyTimes3 implements F {

	@Override
	public int f(int x) {
		return x*3;
		
	}
	
}

class Result {
	private int[] result;
	
	private int ResultCounter = 0;
	
	public Result(int n) {
		result = new int[n];
	}
	public synchronized void SetResult(int n, int partialResult) {
		result[n] = partialResult;
		ResultCounter++;
		if(ResultCounter == result.length) {
			notify();
		}
	}
	
	public synchronized int[] GetResult() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
}