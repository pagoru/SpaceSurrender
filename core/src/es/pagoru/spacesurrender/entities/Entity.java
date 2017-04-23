package es.pagoru.spacesurrender.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by Pablo on 23/04/17.
 */

public abstract class Entity {

    protected Texture texture;
    protected Sprite sprite;

    protected BodyDef bodyDef;

    protected Body body;

    public abstract void render(SpriteBatch batch);

    public void setLinearVelocity(Vector2 velocity){
        body.setLinearVelocity(velocity);
    }

    public BodyDef getBodyDef(){
        return bodyDef;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void dispose(){
        texture.dispose();
    }
}