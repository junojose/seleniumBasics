package assign;

public class Simple {

		static String name;
		static  int salary;

	 static void set(String name1,int salary1)
		{
			name=name1;
			salary=salary1;
		}
		
	 static void get()
	{
		System.out.println("Name:"+name);
		System.out.println(" Salary:"+salary);
	}

		public static void main(String[] args) {
			Simple.set("JUNO",28);
			Simple.get(); 
			

		}

	}



