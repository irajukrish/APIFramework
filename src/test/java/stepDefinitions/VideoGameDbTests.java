package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import resources.VideoGameConfig;
import resources.VideoGamesEndpoints;

import static io.restassured.RestAssured.*;

public class VideoGameDbTests {
	
	Response response;

	@Given("Video Games Request scenario setup")
	public void video_games_request_scenario_setup() {
		
		given();
		
	}

	@When("Get All Games")
	public void get_all_games() {
		response = when().get(VideoGamesEndpoints.ALL_VIDEO_GAMES);
	}

	@Then("Video Games Response and Validation")
	public void video_games_response_and_validation() {
		response.then();
	}
	
	@When("Create a New Game")
	public void create_a_new_game() {
		response = when().post(VideoGamesEndpoints.ALL_VIDEO_GAMES);
	}
	
	@When("Update a Game")
	public void update_a_game() {
		response = when().put("videogames/1");
	}

	@When("Delete a Game")
	public void delete_a_game() {
		response = when().delete("videogames/1");
	}


}
