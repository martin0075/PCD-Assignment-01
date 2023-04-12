
public class Minore 
{
	public static void main(String[]args) 
	{
		Integer[]vet = {20,23,4,56};
		System.out.println(min(vet));
	}
	
	public static Integer min(Integer[]items)
	{
		Integer item=items[0];
		for(int i=1; i<items.length;i++)
		{
			if(items[i].compareTo(item)<0)
				item=items[i];
		}
		return item;
	}

}


