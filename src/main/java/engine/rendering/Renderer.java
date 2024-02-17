package engine.rendering;

import com.sun.javafx.util.Utils;
import engine.core.Config;
import engine.core.InputHandler;
import org.joml.Matrix3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Renderer
{
    private long window;
    private int shaderProgram;
    private int vao;
    private int texture;







    public void run(Config config, InputHandler inputHandler)
    {
        init(config, inputHandler);
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init(Config config, InputHandler inputHandler)
    {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        if(config.startFullscreen)
        {
            GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
            window = GLFW.glfwCreateWindow(1920, 1080, config.windowTitle, NULL, NULL);
        }

        else
        {
            window = glfwCreateWindow(config.windowWidth, config.windowHeight, config.windowTitle, NULL, NULL);
        }

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) ->
        {
            inputHandler.triggerKeyEvent(key, action);

            //glfwSetWindowShouldClose(window, true); //TODO: Implement dynamic close button
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop()
    {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 1.0f, 0.0f, 0.0f);

        Test();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window))
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            //GL33.glUniformMatrix3fv(transformMatrixLocation, false, transformMatrix.get(new float[9]));

            GL33.glBindVertexArray(vao);
            GL33.glDrawArrays(GL33.GL_QUADS, 0, 4);
            GL33.glBindVertexArray(0);

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    int transformMatrixLocation;
    public void Test()
    {
        shaderProgram = ShaderLoader.createProgram();
        transformMatrixLocation = GL33.glGetUniformLocation(shaderProgram, "transformMatrix");

        Matrix3f transformMatrix = new Matrix3f();
        float rotationAngle = (float) Math.toRadians(0);
        transformMatrix = transformMatrix.rotateZ(rotationAngle);

        GL33.glUniformMatrix3fv(transformMatrixLocation, false, transformMatrix.get(new float[9]));

        //texture = loadTexture("pickachu.jpg");

        // Set up the 2D plane
        float[] vertices = {
                -0.5f, -0.5f, 0.0f, 0.0f,
                0.5f, -0.5f, 1.0f, 0.0f,
                0.5f,  0.5f, 1.0f, 1.0f,
                -0.5f,  0.5f, 0.0f, 1.0f
        };

        vao = GL33.glGenVertexArrays();
        int vbo = GL33.glGenBuffers();

        GL33.glBindVertexArray(vao);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vbo);
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertices, GL33.GL_STATIC_DRAW);

        GL33.glVertexAttribPointer(0, 2, GL33.GL_FLOAT, false, 4 * Float.BYTES, 0);
        GL33.glEnableVertexAttribArray(0);

        GL33.glVertexAttribPointer(1, 2, GL33.GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);
        GL33.glEnableVertexAttribArray(1);

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
        GL33.glBindVertexArray(0);
    }

    private static int loadTexture(String filename)
    {
        // Load image data using STBImage
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer imageBuffer = STBImage.stbi_load(Utils.class.getResource("/sprites/" + filename).getPath(), width, height, channels, 4);

        if (imageBuffer == null) {
            throw new RuntimeException("Failed to load texture");
        }

        // Create texture and upload image data
        int textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width.get(0), height.get(0), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, imageBuffer);
        STBImage.stbi_image_free(imageBuffer);

        // Set texture parameters
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        return textureID;
    }
}
