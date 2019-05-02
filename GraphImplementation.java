import java.util.*;

public class GraphImplementation implements Graph {

    //unweighted directed graph and using an adjacency matrix
    int[][] matrix;
    int size;

    public GraphImplementation(int vertices) {

        size = vertices;
        matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; i < size; i++) {
                matrix[i][j] = 0;
            }
        }

    }

    public void addEdge(int v1, int v2) {

        // equals 1 for unweighted graph and signify v1 and v2 have been visited
        //aka v1 and v2 have an edge btwn eachother
        matrix[v1][v2] = 1;

    }

    public List<Integer> topologicalSort() {

        List<Integer> temp = new ArrayList<Integer>();
        int[] incidents = new int[size];

        //initializes visited values to equal 0
        for (int A = 0; A < matrix.length; A++) {
            incidents[A] = 0;
        }

        //creates temp and copies matrix into temp array
        for (int a = 0; a < matrix.length; a++) {
            for (int b = 0; b < matrix.length; b++) {
                //gets sum of column and adds to visited
                incidents[b] += matrix[a][b];
            }
        }

        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){

                if (incidents[j] == 0) {
                   int[] neighbor = neighbors(j);   //collects the neighbors from method

                   for(int k = 0; k < neighbor.length; k++){

                       incidents[neighbor[k]] = incidents[neighbor[k]] - 1;
                   }

                   temp.add(j); //adds the vector to the new/temp list

                   incidents[j] = -1;   //sets back to -1 to signify already visited


                }

            }

        }

        return temp;
    }



    public int[] neighbors(int vertex) {

            int size_t = 0;  //amount of edges in matrix
            //loop counts number of existing edges in the matrix
            for (int i = 0; i < size; i++) {
                if (matrix[vertex][i] > 0) { //edge
                    size_t++;
                }
            }


            //new array for neighbors
            int[] neighbor_m = new int[size_t];
            int count = 0;

            for (int i = 0; i < size; i++) {
                if (matrix[vertex][i] > 0) { //edge
                    neighbor_m[count] = i;
                    count++;
                }
            }


        return neighbor_m;

    }

}
