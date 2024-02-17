package engine.core;

import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

import static engine.core.Key.*;

public class Keys
{
    private final static HashMap<Integer, Key> keymap = new HashMap<>();
    static
    {
        keymap.put(GLFW.GLFW_KEY_SPACE, SPACE);
        keymap.put(GLFW.GLFW_KEY_APOSTROPHE, APOSTROPHE);
        keymap.put(GLFW.GLFW_KEY_COMMA, COMMA);
        keymap.put(GLFW.GLFW_KEY_MINUS, MINUS);
        keymap.put(GLFW.GLFW_KEY_PERIOD, PERIOD);
        keymap.put(GLFW.GLFW_KEY_SLASH, SLASH);
        keymap.put(GLFW.GLFW_KEY_0, NUM_0);
        keymap.put(GLFW.GLFW_KEY_1, NUM_1);
        keymap.put(GLFW.GLFW_KEY_2, NUM_2);
        keymap.put(GLFW.GLFW_KEY_3, NUM_3);
        keymap.put(GLFW.GLFW_KEY_4, NUM_4);
        keymap.put(GLFW.GLFW_KEY_5, NUM_5);
        keymap.put(GLFW.GLFW_KEY_6, NUM_6);
        keymap.put(GLFW.GLFW_KEY_7, NUM_7);
        keymap.put(GLFW.GLFW_KEY_8, NUM_8);
        keymap.put(GLFW.GLFW_KEY_9, NUM_9);
        keymap.put(GLFW.GLFW_KEY_SEMICOLON, SEMICOLON);
        keymap.put(GLFW.GLFW_KEY_EQUAL, EQUAL);
        keymap.put(GLFW.GLFW_KEY_A, A);
        keymap.put(GLFW.GLFW_KEY_B, B);
        keymap.put(GLFW.GLFW_KEY_C, C);
        keymap.put(GLFW.GLFW_KEY_D, D);
        keymap.put(GLFW.GLFW_KEY_E, E);
        keymap.put(GLFW.GLFW_KEY_F, F);
        keymap.put(GLFW.GLFW_KEY_G, G);
        keymap.put(GLFW.GLFW_KEY_H, H);
        keymap.put(GLFW.GLFW_KEY_I, I);
        keymap.put(GLFW.GLFW_KEY_J, J);
        keymap.put(GLFW.GLFW_KEY_K, K);
        keymap.put(GLFW.GLFW_KEY_L, L);
        keymap.put(GLFW.GLFW_KEY_M, M);
        keymap.put(GLFW.GLFW_KEY_N, N);
        keymap.put(GLFW.GLFW_KEY_O, O);
        keymap.put(GLFW.GLFW_KEY_P, P);
        keymap.put(GLFW.GLFW_KEY_Q, Q);
        keymap.put(GLFW.GLFW_KEY_R, R);
        keymap.put(GLFW.GLFW_KEY_S, S);
        keymap.put(GLFW.GLFW_KEY_T, T);
        keymap.put(GLFW.GLFW_KEY_U, U);
        keymap.put(GLFW.GLFW_KEY_V, V);
        keymap.put(GLFW.GLFW_KEY_W, W);
        keymap.put(GLFW.GLFW_KEY_X, X);
        keymap.put(GLFW.GLFW_KEY_Y, Y);
        keymap.put(GLFW.GLFW_KEY_Z, Z);
        keymap.put(GLFW.GLFW_KEY_LEFT_BRACKET, LEFT_BRACKET);
        keymap.put(GLFW.GLFW_KEY_BACKSLASH, BACKSLASH);
        keymap.put(GLFW.GLFW_KEY_RIGHT_BRACKET, RIGHT_BRACKET);
        keymap.put(GLFW.GLFW_KEY_GRAVE_ACCENT, GRAVE_ACCENT);
        keymap.put(GLFW.GLFW_KEY_ESCAPE, ESCAPE);
        keymap.put(GLFW.GLFW_KEY_ENTER, ENTER);
        keymap.put(GLFW.GLFW_KEY_TAB, TAB);
        keymap.put(GLFW.GLFW_KEY_BACKSPACE, BACKSPACE);
        keymap.put(GLFW.GLFW_KEY_INSERT, INSERT);
        keymap.put(GLFW.GLFW_KEY_DELETE, DELETE);
        keymap.put(GLFW.GLFW_KEY_RIGHT, RIGHT);
        keymap.put(GLFW.GLFW_KEY_LEFT, LEFT);
        keymap.put(GLFW.GLFW_KEY_DOWN, DOWN);
        keymap.put(GLFW.GLFW_KEY_UP, UP);
        keymap.put(GLFW.GLFW_KEY_PAGE_UP, PAGE_UP);
        keymap.put(GLFW.GLFW_KEY_PAGE_DOWN, PAGE_DOWN);
        keymap.put(GLFW.GLFW_KEY_HOME, HOME);
        keymap.put(GLFW.GLFW_KEY_END, END);
        keymap.put(GLFW.GLFW_KEY_CAPS_LOCK, CAPS_LOCK);
        keymap.put(GLFW.GLFW_KEY_SCROLL_LOCK, SCROLL_LOCK);
        keymap.put(GLFW.GLFW_KEY_NUM_LOCK, NUM_LOCK);
        keymap.put(GLFW.GLFW_KEY_PRINT_SCREEN, PRINT_SCREEN);
        keymap.put(GLFW.GLFW_KEY_PAUSE, PAUSE);

        // Modifier keys
        keymap.put(GLFW.GLFW_KEY_LEFT_SHIFT, LEFT_SHIFT);
        keymap.put(GLFW.GLFW_KEY_LEFT_CONTROL, LEFT_CONTROL);
        keymap.put(GLFW.GLFW_KEY_LEFT_ALT, LEFT_ALT);
        keymap.put(GLFW.GLFW_KEY_LEFT_SUPER, LEFT_SUPER);
        keymap.put(GLFW.GLFW_KEY_RIGHT_SHIFT, RIGHT_SHIFT);
        keymap.put(GLFW.GLFW_KEY_RIGHT_CONTROL, RIGHT_CONTROL);
        keymap.put(GLFW.GLFW_KEY_RIGHT_ALT, RIGHT_ALT);
        keymap.put(GLFW.GLFW_KEY_RIGHT_SUPER, RIGHT_SUPER);
        keymap.put(GLFW.GLFW_KEY_MENU, MENU);
    }

    public static Key getKey(int glfwKey)
    {
        return keymap.get(glfwKey);
    }
}
