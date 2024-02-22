package com.dcssn.ali.ssl.common.util;

import com.dcssn.ali.ssl.common.exception.ExpResultMessage;
import com.dcssn.ali.ssl.common.exception.ProjectException;
import com.dcssn.ali.ssl.common.exception.ProjectResultMessage;
import org.springframework.lang.Nullable;

/**
 * <p>
 * 断言，不符合条件抛出异常
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
public class Assert {

    public static void state(boolean expression, ExpResultMessage message) {
        if (!expression) {
            throw new ProjectException(message);
        }
    }

    public static void isTrue(boolean expression, ExpResultMessage message) {
        if (!expression) {
            throw new ProjectException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ProjectException(ProjectResultMessage.FAIL, message);
        }
    }

    public static void isFalse(boolean expression, ExpResultMessage message) {
        if (expression) {
            throw new ProjectException(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new ProjectException(ProjectResultMessage.FAIL, message);
        }
    }

    public static void notNull(@Nullable Object object) {
        if (object == null) {
            throw new ProjectException(ProjectResultMessage.FAIL);
        }
    }

    public static void notNull(@Nullable Object object, ExpResultMessage message) {
        if (object == null) {
            throw new ProjectException(message);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new ProjectException(ProjectResultMessage.FAIL, message);
        }
    }

    public static void isNull(@Nullable Object object) {
        if (object != null) {
            throw new ProjectException(ProjectResultMessage.FAIL);
        }
    }

    public static void isNull(@Nullable Object object, ExpResultMessage message) {
        if (object != null) {
            throw new ProjectException(message);
        }
    }

}
