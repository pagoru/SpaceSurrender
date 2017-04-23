package es.pagoru.spacesurrender;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

import es.pagoru.spacesurrender.entities.Batman;
import es.pagoru.spacesurrender.entities.Money;

/**
 * Created by Pablo on 22/04/17.
 */

public class SpaceSurrender extends ApplicationAdapter implements InputProcessor {

	public static final boolean DEBUG_MODE = false;
	public static final float PIXELS_TO_METERS = 100f;

	public static World world;
	public static Batman batman;

	private SpriteBatch batch;
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private OrthographicCamera camera;
	private BitmapFont font;

	private List<Money> moneyList;

	@Override
	public void create() {

		batch = new SpriteBatch();

		world = new World(new Vector2(-1f, 0),true);
		world.setContactListener(new ListenerContact());
		batman = new Batman();

		Money.m_load();
		moneyList = new ArrayList<Money>();
		moneyList.add(new Money());

		Gdx.input.setInputProcessor(this);

		debugRenderer = new Box2DDebugRenderer();
		font = new BitmapFont();
		font.setColor(new Color(128f/256f, 128f/256f, 128f/256f, 1));
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);

	}

	@Override
	public void render() {
		camera.update();

		world.step(1f/60f, 6, 2);

		Gdx.gl.glClearColor(11f/256f, 11f/256f, 10f/256f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
				PIXELS_TO_METERS, 0);
		batch.begin();

		batman.render(batch);
		for (Money money : moneyList) {
			//System.out.println(money.getSprite().getX());
			money.render(batch);
		}

		font.draw(batch,
				"I'm batman, I'm " + batman.getMoney() +"$ rich!!!",
				-Gdx.graphics.getWidth()/4/2 + 10,
				Gdx.graphics.getHeight()/4/2 - 10);
		batch.end();

		if(DEBUG_MODE)
			debugRenderer.render(world, debugMatrix);
	}

	@Override
	public void dispose() {
		batman.dispose();
		Money.m_dispose();
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	private void moveBatman(int screenY){
		if(screenY < 1080/2){
			batman.setLinearVelocity(new Vector2(0, 1f));
			return;
		}
		batman.setLinearVelocity(new Vector2(0, -1f));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		moveBatman(screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		batman.setLinearVelocity(new Vector2());
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		moveBatman(screenY);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}