package org.cstutorials;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

// import org.newdawn.slick.Graphics;

public class Ship extends VisibleObject
{
	private final float width = 30;
	private float height = 40;

	private Vector2f velocity = new Vector2f(5, 5);
	private float acceleration = 0f;
	private float maxAcceleration = 1f;
	private float angleRotation = 5;
	private float angle = 0;
	private long bulletTimer = 10;

	public Ship()
	{
		this.name = "Player0";
		this.x = Game.DISPLAY_WIDTH / 2;
		this.y = Game.DISPLAY_HEIGHT / 3;
		this.vertexHandle = glGenBuffers();
		Game.gameObjectManager.add(this.name, this);
	}

	public Ship(String name, float x, float y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.vertexHandle = glGenBuffers();
		Game.gameObjectManager.add(this.name, this);
	}

	@Override
	public void update(int delta)
	{
		if (x < 0)
		{
			setX(Game.DISPLAY_WIDTH);
		}

		if (x > Game.DISPLAY_WIDTH)
		{
			setX(0);
		}

		if (y < 0)
		{
			setY(Game.DISPLAY_HEIGHT);
		}

		if (y > Game.DISPLAY_HEIGHT)
		{
			setY(0);
		}

		actions();
		move();
	}

	private void move()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			if (acceleration < maxAcceleration)
			{
				acceleration += 0.025f;
			}

			x += Math.sin(Math.toRadians(angle)) * (velocity.x * acceleration);
			y += -Math.cos(Math.toRadians(angle)) * (velocity.y * acceleration);
		}
		else
		{
			if (acceleration > 0f)
			{
				acceleration -= 0.01f;
			}
			else if (acceleration < 0f)
			{
				acceleration = 0;
			}

			x += Math.sin(Math.toRadians(angle)) * (velocity.x * acceleration);
			y += -Math.cos(Math.toRadians(angle)) * (velocity.y * acceleration);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			angle += angleRotation;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			angle -= angleRotation;
		}
	}

	private void actions()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && bulletTimer <= 0)
		{
			shootBullet();
			bulletTimer = 10;
		}
		else
		{
			bulletTimer--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_1))
		{
			setX(600);
			setY(400);
			angle = 0;
		}
	}

	private void shootBullet()
	{
		String name = "Bullet";
		String lastName = Game.gameObjectManager.getLastObjectName(name);

		if (lastName != null)
		{
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(lastName);
			m.find();
			String objectIndex = m.group();
			int nextObjectIndex = Integer.parseInt(objectIndex) + 1;
			name = name + nextObjectIndex;

			new Bullet(name, x - Bullet.width, y, angle, new Vector2f((float) Math.sin(Math.toRadians(angle))
					* (velocity.x * acceleration), (float) -Math.cos(Math.toRadians(angle)) * (velocity.y * acceleration)));
		}
		else
		{
			name = name + "0";

			new Bullet(name, x - Bullet.width, y, angle, new Vector2f((float) Math.sin(Math.toRadians(angle))
					* (velocity.x * acceleration), (float) -Math.cos(Math.toRadians(angle)) * (velocity.y * acceleration)));
		}
	}

	@Override
	public void render(Graphics g)
	{
		glLoadIdentity();
		glPushMatrix();
		g.rotate(x, y, angle);
		g.drawLine(x, y - height / 4, x + width / 3, y + height / 4);
		g.drawLine(x + width / 3, y + height / 4, x, y);
		g.drawLine(x, y, x - width / 3, y + height / 4);
		g.drawLine(x - width / 3, y + height / 4, x, y - height / 4);
		glPopMatrix();
	}
}
