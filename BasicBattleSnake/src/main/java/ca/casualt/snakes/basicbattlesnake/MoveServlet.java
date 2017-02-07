package ca.casualt.snakes.basicbattlesnake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.MoveResponse;

@SuppressWarnings("serial")
@WebServlet("/move")
public class MoveServlet extends HttpServlet {

	private final Gson gson = new Gson();
	private final Random random = new Random();

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		final String requestBody = new BufferedReader(new InputStreamReader(req.getInputStream())).lines()
				.collect(Collectors.joining("\n"));
		System.out.println("Request body:\n" + requestBody);

		final MoveRequest moveRequest = gson.fromJson(requestBody, MoveRequest.class);
		System.out.println(moveRequest);

		final MoveResponse moveResponse = new MoveResponse();
		moveResponse.setMove(getRandomMove());
		moveResponse.setTaunt("Walk the plank you scallywag!");

		final String responseBody = gson.toJson(moveResponse);
		resp.getWriter().println(responseBody);
	}

	private Move getRandomMove() {
		final Move[] values = Move.values();
		return values[random.nextInt(values.length)];
	}
}
