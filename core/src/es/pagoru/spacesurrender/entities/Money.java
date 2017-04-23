package es.pagoru.spacesurrender.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import es.pagoru.spacesurrender.SpaceSurrender;

/**
 * Created by Pablo on 23/04/17.
 */

public class Money extends Entity {

    private static Texture m_texture;
    public static void m_load(){
        m_texture = new Texture("money.png");
    }
    public static void m_dispose(){
        m_texture.dispose();
    }

    public Money(){
        sprite = new Sprite(m_texture);

        sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) / SpaceSurrender.PIXELS_TO_METERS,
                (sprite.getY() + sprite.getHeight()/2) / SpaceSurrender.PIXELS_TO_METERS);

        body = SpaceSurrender.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth()/2 / SpaceSurrender.PIXELS_TO_METERS, sprite.getHeight()
                /2 / SpaceSurrender.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        body.applyTorque(0.0f, true);

        sprite.setPosition((body.getPosition().x * SpaceSurrender.PIXELS_TO_METERS) - sprite.getWidth()/2 ,
                (body.getPosition().y * SpaceSurrender.PIXELS_TO_METERS) -sprite.getHeight()/2 );
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));

        batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
                        getScaleY(),sprite.getRotation());
    }
}
