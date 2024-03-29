package dexflix;

import java.util.ArrayList;

public class Grader {
	
	public Grader () {
		//M�todo Construtor
	}
	
	public int priori (ArrayList<Rating> ratings, int movieId) {
		int i, n;
		float rating;
		
		n = 0;
		rating = 0;
		for (i = 0; i < ratings.size(); i++) {
			if(ratings.get(i).getMovieId() == movieId) {
				rating = rating + ratings.get(i).getRating();
				n++;
			}
		}
		
		rating = rating/n;
		
		return Math.round(rating);
	}
	
	public int naiveBayes (ArrayList<Rating> ratings, ArrayList<User> users, ArrayList<Movie> movies, 
								int movieIndex, int userIndex) {
		float probabilities[] = new float[5];
		float bestProb;
		int i, rating;
		for (i = 0; i < probabilities.length; i++) {
			probabilities[i] = prob (i+1, users.get(userIndex), movies.get(movieIndex), users, ratings, movies);
			//System.out.println(probabilities[i]);
		}
		bestProb = 0;
		rating = 0;
		for (i = 0; i < probabilities.length; i++) {
			if (probabilities[i] > bestProb) {
				bestProb = probabilities[i];
				rating = i+1;
			}
		}
		return rating;
	}
	
	private float prob (int rating, User user, Movie movie, 
							ArrayList<User> users, ArrayList<Rating> ratings, ArrayList<Movie> movies) {
		int favorable_inter, favorable_inter2, favorable_inter3, favorable_inter4, favorable_inter5,
				favorable_A, total, i, j, userIndex, movieIndex;
		float prob_inter, prob_inter2, prob_inter3, prob_inter4, prob_inter5, prob_A, prob;
		movieIndex = 0;
		total = 1000219;
		favorable_inter = 0;
		favorable_inter2 = 0;
		favorable_inter3 = 0;
		favorable_inter4 = 0;
		favorable_inter5 = 0;
		favorable_A = total;
		for (i = 0; i < ratings.size(); i++) {
			userIndex = ratings.get(i).getUserId() - 1;
			for (j = 0; j < movies.size(); j++) {
				if (movies.get(j).getId() == ratings.get(i).getMovieId()) {
					movieIndex = j;
					break;
				}
			}
			if (ratings.get(i).getRating() == rating) {
				favorable_A++;
				if(users.get(userIndex).getAge() == user.getAge()) {
					favorable_inter ++;
				}
				if(users.get(userIndex).getOccupation() == user.getOccupation()) {
					favorable_inter2 ++;
				}
				for (j = 0; j < movie.getGenres().size(); j++) {
					if (movies.get(movieIndex).getGenres().contains(movie.getGenres().get(j))) {
						favorable_inter3 ++;
					}
				}
				if(users.get(userIndex).getGender().equals(user.getGender())) {
					favorable_inter4 ++;
				}
				if (ratings.get(i).getMovieId() == movie.getId()) {
					favorable_inter5 ++;
				}
			}
		}
		prob_inter = (float) favorable_inter/total;
		prob_inter2 = (float) favorable_inter2/total;
		prob_inter3 = (float) favorable_inter3/total;
		prob_inter4 = (float) favorable_inter4/total;
		prob_inter5 = (float) favorable_inter5/total;
		prob_A = (float) favorable_A/total;
		prob = prob_inter*prob_inter2*prob_inter3*prob_inter4*prob_inter5/(prob_A*prob_A*prob_A*prob_A);
		return prob;
	}
	
}
