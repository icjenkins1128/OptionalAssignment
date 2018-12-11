import java.util.*;

public class GraphAdjMatrix implements Graph {

	public int vertices;
	public int edges;
	public int[][] adj;


	public static void main(String[] args) {
	

		// Graph g = getGraph(3);
		
		// g.addEdge(0,  1, 5);


		// System.out.println(g.getEdge(0,1));

		// Graph g = getGraph(5);
		
		// g.addEdge(0, 1, 9);
		// g.addEdge(0, 2, 12);
		// g.addEdge(1, 2, 6);
		// g.addEdge(1, 4, 20);
		// g.addEdge(1, 3, 18);
		// g.addEdge(2, 4, 15);
		// g.addEdge(3, 4, 19);

		// System.out.println(g.createSpanningTree());




	}


	public GraphAdjMatrix (int vertices) {

		this.vertices = vertices;
		this.edges = 0;
		this.adj = new int[vertices][vertices];

	}

	public void addEdge(int v1, int v2, int w) {

		edges++;
		adj[v1][v2] = w;

	}

	public int getEdge(int v1, int v2) {

			//if edge is greater than 0, then a edge is exists with a weight 
			if(adj[v1][v2] > 0){

				return adj[v1][v2];

			}
		

			return 0;
		

		
	}

	


	public int createSpanningTree() {


		int minWeight = 0;
		int size = adj.length;

		ArrayList<TempEdges> edges = new ArrayList<>(size * size);

		ArrayList<TempEdges> minimumSpanningTree = new ArrayList<>();
     	
     	DisjointSet dS = new DisjointSet(size);

		//Create and populate an edges array list in order to create the MST
		for(int i = 0; i < size; i++){

			for(int j = 0; j < size; j++){

				if(adj[i][j] != 0){
					
					edges.add(new TempEdges(i, j, adj[i][j]));

				}


			}


		}


		//We want to sort the edges to help find the least weights
		Collections.sort(edges, new Comparator<TempEdges>() {
            @Override
            public int compare(TempEdges e1, TempEdges e2) {
                return e1.getWeight() - e2.getWeight();
            }
     	});


		//Below create the minimum spannning tree
     
     	for(TempEdges edge: edges){

     		//Since the edges are sorts from least to greatest,
     		//We want to join the two vertices together and add the lowest
     		if( dS.find(edge.getV1()) != dS.find(edge.getV2()) ) {
			
     			dS.union(edge.getV1(), edge.getV2());
     			
				minimumSpanningTree.add(edge);
				
     			minWeight += edge.getWeight();

     		}


     	}


		return minWeight;
	}


	public class TempEdges {
 
   

    	public int v1;
    	public int v2;
    	public int weight;
   
    	public TempEdges(int v1, int v2, int weight){


    		this.v1 = v1;
    		this.v2 = v2;
    		this.weight = weight;
        		
        	
    	}

    	public int getV1(){
    		return this.v1;
    	}

    	public int getV2(){
    		return this.v2;
    	}

    	public int getWeight(){
    		return this.weight;
    	}
   
   
   
       
	}

	public class DisjointSet {



		int parent[];



    	public DisjointSet(int size){

        	parent = new int[size];

        	
        	createSets(size);
        
    	}

    	 public void createSets(int size){
    	 	for (int i = 0; i < size; i++){
            
            	parent[i] = -1;
        	}
    		

    	 }

    	public int find(int i){
        	if (parent[i] < 0){

            	return i;

        	} else {

            	parent[i] = find(parent[i]);
            	return parent[i];

        	}

   		 }

    	public void union(int x, int y){
        	int rootx = find(x);
        	int rooty = find(y);

        	

        	if(parent[rootx] < parent[rooty]){

            	parent[rooty] = rootx;

        	} else {

           		parent[rootx] = rooty;

	        }
	    }



	}

	


	public void topologicalSort(){
		//can be left unimplemented
	}
	public void addEdge(int v1, int v2){
		//can be left unimplemented
	}
}