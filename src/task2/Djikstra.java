package task2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// to solve this problem, we use Dijkstra's algorithm to find the minimum path between the elements of the graph

public class Djikstra {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Input the number of tests: ");
		int countTest = scanner.nextInt();
		System.out.print("Input the number of cities: ");
		int countCities = scanner.nextInt();
		// create and fill in the list of cities
		List<Vertex> listCities = new ArrayList<Vertex>();
		for (int i = 0; i < countCities; i++) {
			System.out.print("Enter the name of " + (i + 1) + " city: ");
			String cityName = scanner.next();
			Vertex city = new Vertex(cityName);
			city.setId(i + 1);
			listCities.add(city);
		}
		
		// create and fill all neighbors of the city and the paths cost between them
		for (int i = 0; i < countCities; i++) {
			System.out.print("  Input the number of neighbors of city " + listCities.get(i) + ": ");
			int cityNeighbors = scanner.nextInt();
			Edge[] mas = new Edge[cityNeighbors];
			for (int j = 0; j < cityNeighbors; j++) {
				System.out.print("    Input the index of a city connected to " + listCities.get(i).getName()
						+ " and the transportation cost (separate with a space): ");
				int cityID = scanner.nextInt();
				int cost = scanner.nextInt();
				mas[j] = new Edge(Djikstra.getCityById(cityID, listCities), cost);

			}
			listCities.get(i).setAdjacencies(mas);
		}
		
		// number of tests per output
		for (int i = 0; i < countTest; i++) {
			System.out.print(
					"For the minimum path sset, enter the city names in the format: NAME1 NAME2 [NAME1 - source, NAME2 - destination]: ");
			String firstCity = scanner.next();
			String secondCity = scanner.next();
			computePaths(firstCity, listCities);
			Vertex secondVertexCity = getCityByName(secondCity, listCities);
			System.out.println("Distance from " + firstCity + " to " + secondVertexCity + ": " + secondVertexCity.getMinDistance());
			List<Vertex> path = getShortestPathTo(secondVertexCity);
			System.out.println("Path: " + path);
		}
	}
	
	// count all paths cost and choose the shortest
	public static void computePaths(String name, List<Vertex> listCityes) {
		Vertex source = null;
		for (Vertex e : listCityes) {
			if (e.getName().equals(name)) {
				source = e;
			}
		}
		source.setMinDistance(0.);
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			// Visit each edge exiting u
			for (Edge e : u.getAdjacencies()) {
				Vertex v = e.getTarget();
				double weight = e.getWeight();
				double distanceThroughU = u.getMinDistance() + weight;
				if (distanceThroughU < v.getMinDistance()) {
					vertexQueue.remove(v);
					v.setMinDistance(distanceThroughU);
					v.setPrevious(u);
					vertexQueue.add(v);
				}
			}
		}
	}
	
	// list of all element paths
	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.getPrevious())
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}
	
	// get city by ID
	public static Vertex getCityById(int id, List<Vertex> listCities) {
		Vertex obj = null;
		for (Vertex e : listCities) {
			if (e.getId() == id) {
				obj = e;
			}
		}
		return obj;
	}
	
	//get city by Name
	public static Vertex getCityByName(String name, List<Vertex> listCities) {
		Vertex obj = null;
		for (Vertex e : listCities) {
			if (e.getName().equals(name)) {
				obj = e;
			}
		}
		return obj;
	}
}