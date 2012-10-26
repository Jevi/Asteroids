package org.cstutorials;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import static org.lwjgl.opengl.GL15.*;

// import org.newdawn.slick.Graphics;

public class Bullet extends VisibleObject
{

	public static final float width = 3;
	public static final float height = 3;

	private Vector2f velocity = new Vector2f(6, 6);
	private Vector2f shipVelocity;
	private float angle = 0;
	private float maxDistance = Game.DISPLAY_WIDTH * .7f;
	private float distanceTraveled = 0;

	public Bullet(String name, float x, float y, float angle, Vector2f shipVelocity)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.shipVelocity = shipVelocity;

		Quad[] quads = new Quad[] { new Quad(x, y, width, height) };

		vertexHandle = glGenBuffers();
		vertexBuffer = BufferUtils.createFloatBuffer(quads.length * Quad.numIndividualVertices);
		numCoordinates = Quad.numCoordinates;
		numPairVertices = quads.length * numCoordinates;

		for (Quad quad : quads)
		{
			vertexBuffer.put(new float[] { quad.getX1(), quad.getY1(), quad.getX2(), quad.getY2(), quad.getX3(), quad.getY3(),
					quad.getX4(), quad.getY4() });
		}
		vertexBuffer.flip();

		glBindBuffer(GL_ARRAY_BUFFER, vertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		Game.gameObjectManager.add(this.name, this);
	}

	@Override
	public void update(int delta)
	{

		if (distanceTraveled >= maxDistance)
		{
			Game.gameObjectManager.remove(this.name);
		}

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

		x += (Math.sin(Math.toRadians(angle)) * velocity.x) + shipVelocity.x;
		y += (-Math.cos(Math.toRadians(angle)) * velocity.y) + shipVelocity.y;
		distanceTraveled += Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y, 2));
	}

	@Override
	public void render(Graphics g)
	{
		g.drawQuads();
	}
}
