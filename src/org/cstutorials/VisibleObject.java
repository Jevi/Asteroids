package org.cstutorials;

import java.nio.FloatBuffer;

// import org.newdawn.slick.Graphics;

abstract public class VisibleObject
{
	protected String name;
	protected float startx;
	protected float starty;
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	protected int vertexHandle;
	protected FloatBuffer vertexBuffer;
	protected int numCoordinates;
	protected int numPairVertices;

	public abstract void update(int delta);

	public abstract void render(Graphics g);

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public String getName()
	{
		return name;
	}

	public int getVertexHandle()
	{
		return vertexHandle;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public void setY(float y)
	{
		this.y = y;
	}
}
