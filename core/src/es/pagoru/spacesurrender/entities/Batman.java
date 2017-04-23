package es.pagoru.spacesurrender.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import es.pagoru.spacesurrender.SpaceSurrender;

/**
 * Created by Pablo on 23/04/17.
 */

public class Batman extends Entity {

    private int money;

    public Batman(){
        money = 0;

        texture = new Texture("batman.png");
        sprite = new Sprite(texture);

        sprite.setPosition(-Gdx.graphics.getWidth()/4/2,-sprite.getHeight()/2);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) / SpaceSurrender.PIXELS_TO_METERS,
                (sprite.getY() + sprite.getHeight()/2) / SpaceSurrender.PIXELS_TO_METERS);

        body = SpaceSurrender.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth()/6 / SpaceSurrender.PIXELS_TO_METERS, sprite.getHeight()
                /3 / SpaceSurrender.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money){
        this.money += money;
    }

    private boolean overflow = false;

    @Override
    public void render(SpriteBatch batch){

        if(overflow){
            setLinearVelocity(new Vector2());
            overflow = false;
        }
        if(body.getPosition().y > 1.0f - (16 / SpaceSurrender.PIXELS_TO_METERS)){
            setLinearVelocity(new Vector2(0, -2f));
            overflow = true;
        }
        if(body.getPosition().y < - 1.0f + (16 / SpaceSurrender.PIXELS_TO_METERS)){
            setLinearVelocity(new Vector2(0, 2f));
            overflow = true;
        }

        //if(((Gdx.graphics.getWidth()/4/2) / SpaceSurrender.PIXELS_TO_METERS))

        body.applyTorque(0.0f, true);

        sprite.setPosition((body.getPosition().x * SpaceSurrender.PIXELS_TO_METERS) - sprite.getWidth()/2 ,
                (body.getPosition().y * SpaceSurrender.PIXELS_TO_METERS) - sprite.getHeight()/2 );
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));

        batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
                        getScaleY(),sprite.getRotation());
    }

}
