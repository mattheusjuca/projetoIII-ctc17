package dexflix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner_movies = new Scanner(new FileReader("movies.dat")).useDelimiter("\\::|\\n");
		Scanner scanner_ratings = new Scanner(new FileReader("ratings.dat")).useDelimiter("\\::|\\n");
		Scanner scanner_users = new Scanner(new FileReader("users.dat")).useDelimiter("\\::|\\n");
		Scanner input = new Scanner(System.in);
		
		Movie movie_aux;
		User user_aux;
		Rating rating_aux;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		
		Grader grader = new Grader();
		
		int i;
		
		while (scanner_movies.hasNext()) {
			int id = scanner_movies.nextInt();
			String title = scanner_movies.next();
			String genres = scanner_movies.next();
			
			movie_aux = new Movie (id, title);
			
			Scanner auxscan = new Scanner(genres).useDelimiter("\\|");
			while (auxscan.hasNext()) {
				String genre = auxscan.next();
				movie_aux.addGenre(genre);
			}
			
			movies.add(movie_aux);
		}
		
		while (scanner_users.hasNext()) {
			int id = scanner_users.nextInt();
			String gender = scanner_users.next();
			int age = scanner_users.nextInt();
			int occupation = scanner_users.nextInt();
			String zipCode = scanner_users.next();
			
			user_aux = new User(id, gender, age, occupation, zipCode);
			
			users.add(user_aux);
		}
		
		while (scanner_ratings.hasNext()) {
			int userId = scanner_ratings.nextInt();
			int movieId = scanner_ratings.nextInt();
			int rating = scanner_ratings.nextInt();
			int timestamp = scanner_ratings.nextInt();
			
			rating_aux = new Rating(userId, movieId, rating, timestamp);
			
			ratings.add(rating_aux);
		}
		
		System.out.print("Digite seu UserId: ");
		if (!input.hasNextInt()) {
			System.out.println("ID inv�lido - Aplica��o Encerrada");
			System.exit(0);
		}
		int myId = input.nextInt();
		int userIndex = myId - 1;
		if (myId > 6041 || myId < 1) {
			System.out.println("Usu�rio n�o registrado - Aplica��o Encerrada");
			System.exit(0);
		}
		System.out.print("Digite um ID de Filme: ");
		while (input.hasNextInt()) {
			int movieId = input.nextInt();
			int movieIndex = 5000;
			for (i = 0; i < movies.size(); i++) {
				if (movies.get(i).getId() == movieId) {
					movieIndex = i;
					break;
				}
			}
			int ratingPriori = grader.priori(ratings, movieId);
			int ratingBayes = grader.naiveBayes(ratings, users, movies, movieIndex, userIndex);
			if (movieIndex == 5000) {
				System.out.println("Filme n�o encontrado\n");
				System.out.print("Digite outro ID: ");
			}
			else {
				System.out.println("Classifica��o do Filme " + movies.get(movieIndex).getTitle() + " a Priori: " + ratingPriori);
				System.out.println("Classifica��o do Filme " + movies.get(movieIndex).getTitle() + " por Navie Bayes: " + ratingBayes + "\n");
				System.out.print("Digite outro ID: ");
			}
		}
		System.out.println("ID inv�lido - Aplica��o Encerrada");
		
		/*for (i = 0; i < movies.size(); i++) {
			System.out.println("ID: " + movies.get(i).getId());
			System.out.println("Movie: " + movies.get(i).getTitle());
			System.out.println("Genres: " + movies.get(i).getGenres() + "\n");
		}
		
		for (i = 0; i < users.size(); i++) {
			System.out.println("ID: " + users.get(i).getId());
			System.out.println("Gender: " + users.get(i).getGender());
			System.out.println("Age: " + users.get(i).getAge());
			System.out.println("Occupation: " + users.get(i).getOccupation());
			System.out.println("ZipCode: " + users.get(i).getZipCode() + "\n");
		}
		
		for (i = 0; i < ratings.size(); i++) {
			System.out.println("User: " + ratings.get(i).getUserId());
			System.out.println("Movie: " + ratings.get(i).getMovieId());
			System.out.println("Rating: " + ratings.get(i).getRating());
			System.out.println("TimeStamp: " + ratings.get(i).getTimestamp() + "\n");
		}*/
	
		System.exit(0);
	}
}
