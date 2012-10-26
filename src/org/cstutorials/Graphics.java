package org.cstutorials;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Graphics
{

	public static int currentVertexHandle;

	public Graphics()
	{
	}

	public void drawLine(float x1, float y1, float x2, float y2)
	{
		float[] vertices = { x1, y1, x2, y2 };
		int numCoordinates = 2;
		int numVertices = 2;

		FloatBuffer vBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vBuffer.put(vertices);
		vBuffer.flip();

		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, currentVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vBuffer, GL_STATIC_DRAW);
		glVertexPointer(numCoordinates, GL_FLOAT, 0, 0L);
		glDrawArrays(GL_LINES, 0, numVertices);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableClientState(GL_VERTEX_ARRAY);

	}

	public void drawQuad(float x, float y, float width, float height)
	{
		float[] vertices = { x, y, x + width, y, x + width, y + height, x, y + height };
		int numCoordinates = 2;
		int numVertices = 4;

		FloatBuffer vBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vBuffer.put(vertices);
		vBuffer.flip();

		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, currentVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vBuffer, GL_STATIC_DRAW);
		glVertexPointer(numCoordinates, GL_FLOAT, 0, 0L);
		glDrawArrays(GL_QUADS, 0, numVertices);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableClientState(GL_VERTEX_ARRAY);
	}

	public void rotate(float x, float y, float angle)
	{
		glTranslatef(x, y, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x, -y, 0);
	}
}
