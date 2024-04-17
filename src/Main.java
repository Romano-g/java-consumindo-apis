import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.TitleOmdb;
import models.Titles;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Digite um filme para busca: ");
		var search = reader.nextLine();

		String movieSearch = "http://omdbapi.com/?t=" +
				search.replace(" ", "+") +
				"&apikey=6498ae93";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(movieSearch))
				.build();

		HttpResponse<String> response = client
				.send(request, HttpResponse.BodyHandlers.ofString());

		String json = response.body();

		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.create();

		try {
			TitleOmdb myTitleOmdb = gson.fromJson(json, TitleOmdb.class);
			Titles myTitle = new Titles(myTitleOmdb);
			System.out.println(myTitle);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatação de número:");
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException i) {
			System.out.println("Erro de argumento:");
			System.out.println(i.getMessage());
		}
	}
}
