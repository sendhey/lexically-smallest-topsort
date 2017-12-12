import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Solution {

	public static void main(String[] args) {
		
		
		
		try{
	        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        	String sizes[] = br.readLine().split(" ");
	        	int numStages = Integer.parseInt(sizes[0]);
	        	int relStages = Integer.parseInt(sizes[1]);
	        	Graph g = new Graph(numStages);
	        	
	        	
	        	
	        	for (int i = 0; i < relStages; i++){
	        		
	        		//System.out.println("HI");
	        		
	        		String temp = br.readLine();
	        		String tempParts[] = temp.split(" ");
	        		int tempOne = Integer.parseInt(tempParts[0]);
	        		int tempTwo = Integer.parseInt(tempParts[1]);
	        		
	        		g.insertEdge(tempOne, tempTwo);
	        //		System.out.println("added: " + tempOne + " " + tempTwo);
	        		
	        		
	        		
	        	}
	        	
	        //	System.out.println("HI THERE");
	        	g.Topsort();
	        }
	        catch(IOException e){
	        	e.printStackTrace();
	        }

	}


	public static class Graph {
		int size;
		boolean[] vertBooList;
		Vertice[] vertList;
		
		//constructor
		public Graph(int size){
			this.size = size;
			this.vertBooList = new boolean[size+1];
			this.vertList = new Vertice[size+1];
		}
		

		
		//top sort
		public void Topsort(){
			//array list for the top sort
			ArrayList<Integer> list = new ArrayList<Integer>();
			//the min heap
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>(size);
			

			
			//go through + add vertices with no indegrees
			for(int i = 0; i < size; i++){
				//System.out.println("GOT HERE: " + i);
				if(vertList[i+1].inDegree == 0){
					queue.add(vertList[i+1].val);
				}
			}
			
			//while heap has vertices
			while(!queue.isEmpty()){
				//delete min and take the index
				int index = queue.poll();
				
				//get vertice
				Vertice vert = vertList[index];
				//add value to list
				list.add(vert.val);
				
				//add  all vertices with no indegrees
				for(int j = 0; j < vert.adjacent.size(); j++){
					if (vert.adjacent.get(j) != null){
						//decrease indegree by 1
						vert.adjacent.get(j).inDegree -=1;
						
						if (vert.adjacent.get(j).inDegree == 0){
							queue.add(vert.adjacent.get(j).val);
						}
					}
				}
			}
			if(list.size() > size -1){
				for (int m = 0; m < list.size(); m++){
					System.out.print(list.get(m) + " ");
				}
				
			}
			else{
				System.out.println("-1");
			}
		}
		//insert an edge
		public void insertEdge(int x, int y){
			Vertice a;
			Vertice b;
			
			//vertice does not exist
			if (!vertBooList[x]){
				a = new Vertice(x);
				vertList[x] = a;
				vertBooList[x] = true;
			}
			//vertice exists
			else{
				a = vertList[x];
			}
			//vertice does not exist
			if (!vertBooList[y]){
				b = new Vertice(y);
				vertList[y] = b;
				vertBooList[y] = true;
			}
			//vertice exists
			else{
				b = vertList[y];
			}
			
			//if b is not adjacent to a
			if(!a.adjacent.contains(b)){
				a.adjacent.add(b);
			}
			//if a is not adjavent to b
			if(!b.adjacent.contains(a)){
				b.adjacent.add(a);
			}
			
			a.outDegree++;
			b.inDegree++;
			
		}
	}

	public static class Vertice {
		ArrayList<Vertice> adjacent = new ArrayList<Vertice>();
		public int inDegree;
		public int outDegree;
		public int val;

		//constructor
		public Vertice(int val){
			this.inDegree = 0;
			this.outDegree = 0;
			this.val = val;
		}
	}
}
