package engine.core;

import org.lwjgl.glfw.GLFW;

public enum KeyAction
{
    DOWN,
    UP,
    HOLD;

    public static KeyAction getAction(int glfwId)
    {
        if(glfwId == GLFW.GLFW_PRESS)
            return DOWN;
        if(glfwId == GLFW.GLFW_RELEASE)
            return UP;
        if(glfwId == GLFW.GLFW_REPEAT)
            return HOLD;

        return null;
    }
}
