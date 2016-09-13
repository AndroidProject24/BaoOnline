package com.toan_itc.data.rxjava;

public final class Preconditions {
    private Preconditions() {
        throw new AssertionError("No instances");
    }

	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
	    return value;
    }

    public static void checkArgument(boolean check, String message) {
        if (!check) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkState(boolean check, String message) {
        if (!check) {
            throw new IllegalStateException(message);
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
    }
}
