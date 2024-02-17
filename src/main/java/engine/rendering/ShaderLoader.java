package engine.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL33;

public class ShaderLoader
{
    public static final String VERTEX =
            """
                    #version 330 core

                    layout (location = 0) in vec3 aPosition;
                    layout (location = 1) in vec2 aTexCoord;

                    out vec2 texCoord;

                    uniform mat3 transformMatrix;

                    void main()
                    {
                        vec3 transformedPosition = transformMatrix * aPosition;
                        gl_Position = vec4(transformedPosition, 1.0);
                        texCoord = aTexCoord;
                    }
                    """;

    public static final String FRAGMENT =
            """
                    #version 330 core
                    in vec2 texCoord;
                    out vec4 fragColor;
                    uniform sampler2D textureSampler;
                    void main()
                    {
                        fragColor = texture(textureSampler, texCoord);
                    }""";

    private static int createShader(String source, int shaderType)
    {
        int shader = GL33.glCreateShader(shaderType);
        GL33.glShaderSource(shader, source);
        GL33.glCompileShader(shader);

        if (GL33.glGetShaderi(shader, GL33.GL_COMPILE_STATUS) == GL11.GL_FALSE)
            throw new RuntimeException("Error compiling shader: " + GL33.glGetShaderInfoLog(shader));

        return shader;
    }

    public static int createProgram()
    {
        int vertexShader = createShader(VERTEX, GL33.GL_VERTEX_SHADER);
        int fragmentShader = createShader(FRAGMENT, GL33.GL_FRAGMENT_SHADER);

        int shaderProgram = GL33.glCreateProgram();
        GL33.glAttachShader(shaderProgram, vertexShader);
        GL33.glAttachShader(shaderProgram, fragmentShader);
        GL33.glLinkProgram(shaderProgram);
        GL33.glUseProgram(shaderProgram);
        return shaderProgram;
    }
}