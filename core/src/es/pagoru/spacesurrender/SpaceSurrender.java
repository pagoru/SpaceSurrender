package es.pagoru.spacesurrender;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceSurrender extends ApplicationAdapter {

	private OrthographicCamera cam;

	private SpriteBatch batch;

	private Texture batmanTexture;
	private Texture batmanLogoTexture;
	private Texture hitheartKappa;

	private int playerPosition = 0;

	private int[] graphics_size = new int[2];

	@Override
	public void create () {
		batch = new SpriteBatch();
		batmanTexture = new Texture("batman.png");
		batmanLogoTexture = new Texture("batman_logo.png");
		hitheartKappa = new Texture("Kappa.png");

		graphics_size[0] = Gdx.graphics.getWidth();
		graphics_size[1] = Gdx.graphics.getHeight();

		// Constructs a new OrthographicCamera, using the given viewport width and height
		// Height is multiplied by aspect ratio.
		cam = new OrthographicCamera(graphics_size[0]/4, graphics_size[1]/4);

		cam.position.set(0, 0, 0);
		cam.update();

	}

	@Override
	public void render () {
		handleInput();

		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(34f/256f, 34f/256f, 34f/256f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(batmanTexture, -(graphics_size[1]/4 - 48), -32 + playerPosition);

		batch.draw(batmanLogoTexture, 128, 64);
		batch.draw(batmanLogoTexture, 20, -54);
		batch.draw(batmanLogoTexture, -124, 56);
		batch.draw(batmanLogoTexture, -154, -96);

		batch.draw(hitheartKappa, 0, 0);
		batch.end();
	}

	public void handleInput(){
		int maxPlayerPosition = 88;
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if(playerPosition < maxPlayerPosition){
				playerPosition ++;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if(playerPosition > -maxPlayerPosition){
				playerPosition --;
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		batmanTexture.dispose();
	}

	public static int randomInt(int Min, int Max)
	{
		return (int) (Math.random()*(Max-Min))+Min;
	}
}
