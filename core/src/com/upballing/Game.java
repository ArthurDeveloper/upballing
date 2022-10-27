package com.upballing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	int x, y = 0;

	int x_speed = 0;
	int x_acceleration = 1000;
	int x_disacceleration = 700;
	int x_max_speed = 900;
	int y_speed = 0;
	int jump_force = 600;
	int gravity = 800;
	int max_fall_speed = 1200;
	boolean is_jumping = false;

	double delta;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	public void update() {
		delta = Gdx.graphics.getDeltaTime();

		y_speed -= gravity * delta;
		if(y_speed < -max_fall_speed) y_speed = -max_fall_speed;
		if (is_jumping) {
			 y_speed = jump_force;
			 is_jumping = false;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			 x_speed += x_speed >= x_max_speed ? 0 : x_acceleration * delta;
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			 x_speed -= x_speed >= x_max_speed ? 0 : x_acceleration * delta;
		} else {
			x_speed -= x_disacceleration * delta;
			if (x_speed < 0) x_speed = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			is_jumping = true;
		}

		y += y_speed * delta;
		x += x_speed * delta;
	}

	@Override
	public void render() {
		update();

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
