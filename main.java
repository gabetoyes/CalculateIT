import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class CalculateIT {
	public static boolean breaker = false;
	public static void Functions(String izraz) {
		System.out.println("Izberi funkciq za izraza:"+izraz+" \n" +
				"1 - pokazi pulniq izraz\n" +
				"2 - pokazi znak\n" +
				"3 - pokazi index\n" + 
				"4 - pokazi promenliva\n" + 
				"5 - subirane");
		System.out.print("Funkciq: ");
	}
	public static LinkedList<String> Nacepin(String result) {
		String str = "";
		char[] a = new char[result.length()];
		LinkedList<String>parts = new LinkedList<String>();
		for (int i = 0; i < result.length(); i++)
			a[i] = result.charAt(i);
		@SuppressWarnings("unused")
		char lastSign = 0;//better not delete this
		int lastIndex = 0;
		for (int i = 0; i < result.length() - 1; i++) {
			str += a[i];
			for(int k = result.length()-1;k > 0;k--)
			{
				if(a[k] == '+')
				{
					lastSign = '+';//and this
					lastIndex = k;
					break;
				}
				if(a[k] == '-') 
				{
					lastSign = '-';//and this
					lastIndex = k;
					break;
				}
			}
			if(a[i+1] == '+' || a[i+1] == '-') {
				parts.add(str);
				str = "";
			}
			if(i == lastIndex)
			{
				str = "";
				for(int o = lastIndex; o < result.length(); o++)
					str += a[o];
				parts.add(str);
			}
		}
		return parts;
	}
	public static int addSign(String str) {
		int sign = 1;
		char[] a = str.toCharArray();
		if(a[0] == '+' || a[0] == '-') 
		{
			if(a[0] == '-')sign = -1;
		}
		return sign;
	}
	public static String addSignS(String str) {
		String sign = "";
		char[] a = str.toCharArray();
		if(a[0] == '+' || a[0] == '-') 
		{
			if(a[0] == '-')sign = "-";
		}
		return sign;
	}
	public static double indexed(String izraz) {
		double index = 0;
		char[] a = new char[izraz.length()];
		a = izraz.toCharArray();
		int sign = 1;
		if(a[0] == '+' || a[0] == '-') 
		{
			if(a[0] == '-')sign = -1;
			a[0] = ' ';
		}
		char[] numbers ={'1','2','3','4','5','6','7','8','9','0','.'};
		String indexing = "";
		int breaker = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == '^')break;
			if(breaker > 0)break;
			for(int j = 0; j < numbers.length; j++)
			{
				if(a[i] == numbers[j])
				{
					indexing += a[i];
					breaker = 0;
					break;//VAZNO
				}
				else 
					if(a[i] != '+' && a[i] != '-' && a[i] != ' ')
						breaker++;
					else breaker = 0;
			}
			if(breaker > 0)break;
		}
		//System.out.println(indexing);//das
		index = Double.valueOf(indexing);
		return index*sign;
	}
	public static String variableFind(String str, boolean SIGN) {
		if(str == "")
		{
			System.out.println("");
			return str;
		}
		String variable = "";
		String sign = addSignS(str);
		char[] a = str.toCharArray();
		String Letters = 
				"xyXYabcdefghijklmnopqrstuvwzABCDEFGHIJKLMNOPQRSTUVWZ";
		char[] letters = Letters.toCharArray();
		if(a[0] == '+' || a[0] == '-') a[0] = ' ';
		int breaker = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == ' ')continue;
			if(breaker > 0)
			{
				variable += a[i];
				continue;
			}
			for(int j = 0; j < letters.length; j++)
			{
				if(a[i] == letters[j])
				{
					variable += a[i];
					breaker++;
					break;
				}
			}
		}
		if(variable == "")return "";
		if(SIGN)variable = sign + variable;
		System.out.println("VAR: " + variable);
		return variable;
	}
	public static String round(double value) {
		
		DecimalFormat df = new DecimalFormat("###.#");
		return df.format(value);
	}
	public static String compression(String izraz1, String izraz2) {
		String result = "";
		String var = "";
		boolean grading = false;
		double index;
		int ln = 
			round(indexed(izraz1)).length();
		int ln1 = variableFind(izraz1,false).length();
		//System.out.println("ln: " + ln + "\nln1 :" + ln1);
			
		if(variableFind(izraz1,false).
				equals(variableFind(izraz2,false)))
		{
				var = variableFind(izraz1,false);
				izraz1.replaceAll(" +-", "");
				if(ln + ln1 < izraz1.length())grading = true;
				
				index = indexed(izraz1) + indexed(izraz2);
		}
		else 
		{
			System.out.println(izraz1 + 
					" nemoze da se subere s " + izraz2);
			breaker = true;
			return "";
		}
		if(grading)var = "^" + var;
		result = Double.toString(index) + var;
		return result;
	}
	public static int smallest(String str) {
		
		char[] ch = str.toCharArray();
		int[] a = new int[ch.length];
		
		for(int i = 0; i < a.length; i++)
			a[i] = 
				Integer.parseInt(Character.toString(ch[i]));
		int smallest = a[0];
		
		for(int i = 0; i < a.length; i++)
			if(smallest < a[i]) smallest = a[i];
		
		return smallest;
	}
	public static LinkedList<String>PartRemover(
			LinkedList<String> newList,String indexes,String add) {
		
		char[] indexch = indexes.toCharArray();
		
		int[] index = new int[indexch.length];
		
		for(int i = 0; i < index.length; i++)
			index[i] = 
				Integer.parseInt(Character.toString(indexch[i]));
		Arrays.sort(index);
		int addIndex = index[0];
		
		for(int i = index.length-1; i >= 0; i--)
			newList.remove(index[i]);
		
		newList.add(addIndex, add);
		
		return newList;
	}
	public static void izrazF(String str,String chast) {
		System.out.print(str + " na izraza " + 
				chast + " e: ");
	}
	public static void izraz2F(String str,String chast1,String chast2) {
		System.out.println(str + " na izraza " + 
				chast1 + " i izraza "+chast2 + " e: ");
	}
	public static void main(String[] args) {
		boolean running = true;
		
		Scanner scan = new Scanner(System.in);
		String izraz = "5x - 12x^4 + 42 + 5 + 2x^4";//scan.next();

		System.out.println("Vuvedi izraz:");
		LinkedList<String>chasti = Nacepin(izraz);
		while(running)
		{
				
		
			System.out.printf("%n %10s %10s %n","chast:","izraz:");
			for(int i = 1; i <= chasti.size(); i++)
				cf.squaredAdd(chasti.get(i-1),"","      " +
							Integer.toString(i)+"        ");
			System.out.print("Izberi izraz: ");
			int chast = scan.nextInt();
			int index = chast - 1;
			//System.out.println("izbran izrazZZZZZ: " + chasti.get(index));
			Functions(chasti.get(index));
			int function = scan.nextInt();
			String using = chasti.get(index);
			switch(function)
			{
				case 1: 
					System.out.println("Пълен израз: " + izraz);
					break;
					
				case 2: 
					izrazF("Znaka",using);
					if(addSign(using) == -1)
						System.out.print("-");
					else System.out.print("+");
					break;
					
				case 3: 
					izrazF("Indexa",using);
					System.out.println(indexed(using));
					break;
					
				case 4: 
					izrazF("Promenlivata",using);
					System.out.println(indexed(using));
					break;
					
				case 5:
					System.out.println("izbran izraz: " + using);
					System.out.print("izberi vtori izraz: ");
					
					int index2 = scan.nextInt() - 1;
					if(index2 == index)
					{
						System.out.println("Izbran e sushtiq element!!!");
						break;
					}
					String using2 = chasti.get(index2);
					System.out.println("izbran vtori izraz: " + using2);
					izraz2F("Subiraneto",using,using2);
					
					String compress = compression(using,using2);
					if(breaker)
					{
						breaker = false;
						break;
					}
					System.out.print(compress);
					
					String indexes = 
							Integer.toString(index) +
							Integer.toString(index2);
					
					chasti = PartRemover(chasti,indexes,compress);
					
					//System.out.print(chasti);
					break;
					
				case 69: return;
			}
			
		}
		scan.close();
	}
}
