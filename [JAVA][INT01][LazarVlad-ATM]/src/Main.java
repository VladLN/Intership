
public class Main{

	private static int  INF=(int) 1e9;
	public static int G[][]= { {INF,5,60,30,45},
								{INF,INF,40,INF,45},
								{INF,INF,INF,15,INF},
								{INF,40,INF,INF,INF},
								{INF,INF,30,INF,INF} };
	public static int Ore[][]={  {1,12,18},{2,10,17},{3,22,12},{4,17,1}};
	public static int Card[][]= { {0,0 ,4000 ,3000},{0,1 ,3000 ,25000},{0,2 ,4500 ,20000}};
	public static int Orar[][]= {{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24},
								 {1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0},
								 {2,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
								 {3,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
								 {4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1}};
	public static int ora_star=11,minut_start=30;
	public static int ora_fin=14,min_fin=00;
	public static int ora,ora_f;
	public static int n=4,Suma=7500;
	public static void floyd()throws Exception
	{
		int i,j,k;
		for(k=0;k<=n;k++)
			for(i=0;i<=n;i++)
				for(j=0;j<=n;j++)
					if(G[i][j]>G[i][k]+G[k][j])
						G[i][j]=G[i][k]+G[k][j];
	}
	public static void cum_scot() throws Exception
	{
		int s,i=1;
		s=Suma;
		while(s>0)
		{
			if(s<=Card[2][i])
			{
				s=0;
			}
			else
			{
				s=s-Card[2][i];
			}
			i++;				
		}
	}
	public static int distanta=(int)1e9;
	public static int[] atm_vizitat=new int[4];
	public static int atm_deschis(int atm, int ora)
	{
	   return Orar[atm][ora/60];
	}
	public static int min(int a,int b)
	{
		if(a<b)
			return a;
			else
				return b;
	}
	public static void scoate() 
	{
		try {
			cum_scot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i,j,atm=0,atmv = 0,distanta = 0,card_in_use=0;
		while (Suma>0 && ora<=ora_f)
		   {
		      for (i=1;i<=n;i++)
		      {
		        		 distanta=G[atm][i];
		        		 atmv=i;
		        	     for (i=1;i<=3;i++)
		            		if (Card[2][i]>0)
		            		{
		            			Suma-=Card[2][i];
		            			//Card[2][i]=Card[2][i]-min(Card[3][i],Card[2][i]);
		            		}
		         atm_vizitat[atmv]=1;///marchez ATM vizitat
		         ora=ora+G[atm][atmv];///modific ora dupa deplasarea la atmv
		         atm=atmv;
		      }
		   }
	}
	public static void main(String args[]) throws Exception
	{
	
		floyd();
		scoate();
	}
}
