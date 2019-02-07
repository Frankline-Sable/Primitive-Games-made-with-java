package SnailMania;

import java.util.Scanner;

public class Knights_Tour {
	
	private int arrayFloor[][]=new int[8][8];
	private final int ONE=1;
	private final int TWO=2;
	private final int THREE=3;
	private final int FOUR=4;
	private final int FIVE=5;
	private final int SIX=6;
	private final int SEVEN =7;
	private final int ZERO =0;
	private int x;
	private Scanner input;
	private int horizontal[]={2,1,-1,-2,-2,-1,1,2};
	private int vertical[]={-1,-2,-2,-1,1,2,2,1};
	private int currentRow;
	private int currentColumn;
	private int moveNumber;
	private int val;
	
	public void executeApplication(){
		
		input=new Scanner(System.in);
		System.out.println("Please input a number.");
		
		do{
			
			x=input.nextInt();
			
			switch(x){
			
			case ONE:
				
				moveNumber=1;
				currentRow+=vertical[moveNumber];
				currentColumn+=horizontal[moveNumber];
				val++;
				//arrayFloor[currentColumn]=val;
				break; 
				}
			//end of switch
			
		}
		while(x!=9);//end of while
	}
	
	public Knights_Tour(){
		
		iniatializeArray(arrayFloor);
		printArray(arrayFloor);
		executeApplication();
		
	}
	public static void main(String[] args) {

		Knights_Tour t=new Knights_Tour();

	}
	public void iniatializeArray(int x[][]){
		
		for(int i=0; i<x.length; i++){
			
		
			
			for(int j=0; j<x[i].length;j++){
				
				x[i][j]=0;
			}
		}
	}
	public void printArray(int x[][]){
		
		System.out.println("We have now initialised the array.\n");
		for(int i=0; i<x.length; i++){
			
			System.out.printf("%5d  ",i);
			
			for(int j=0; j<x[i].length; j++){
				
				System.out.printf("%d ", x[i][j]);
			}
			System.out.println();
		}
	}
	}
