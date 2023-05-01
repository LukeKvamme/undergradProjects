package PythonList;

public class PythonListMain {
	public static void main(String[] args) {
		
		PythonList<Integer> _intL = new PythonList<Integer>();
		_intL.add(0, 1);
		_intL.add(0, 3);
		_intL.add(1, 10);
		
		_intL.remove(1);
		
		_intL.set(1, 200);
		_intL.add(2, 2);
		_intL.add(3, 3);
		_intL.add(4, 4);
		
		_intL.subList(1, 3); // not complete but ran out of time
		
		
		System.out.println("____________________________________________________________________");
		
		PythonList<String> _strL = new PythonList<String>();
		_strL.add(0,"dog");
		_strL.add(1,"cat");
		_strL.add(2, "can");
		
		_strL.remove(1);
		_strL.set(1, "grass");
		
		

	}

}
