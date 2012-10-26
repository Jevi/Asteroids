package org.cstutorials;

public class Quad
{
	private float x1, y1, x2, y2, x3, y3, x4, y4;

	public static final int numIndividualVertices = 8;
	public static final int numPairVertices = 4;
	public static final int numCoordinates = 2;

	public Quad(float x, float y, float width, float height)
	{
		this.x1 = x;
		this.y1 = y;

		this.x2 = x1 + width;
		this.y2 = y1;

		this.x3 = x2;
		this.y3 = y2 + height;

		this.x4 = x1;
		this.y4 = y3;
	}

	public float getX1()
	{
		return x1;
	}

	public float getY1()
	{
		return y1;
	}

	public float getX2()
	{
		return x2;
	}

	public float getY2()
	{
		return y2;
	}

	public float getX3()
	{
		return x3;
	}

	public float getY3()
	{
		return y3;
	}

	public float getX4()
	{
		return x4;
	}

	public float getY4()
	{
		return y4;
	}

}
