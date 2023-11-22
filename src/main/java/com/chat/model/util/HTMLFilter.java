package com.chat.model.util;


public final class HTMLFilter {

    /**
     * Filter the specified message string for characters that are sensitive
     * in HTML.  This avoids potential attacks caused by including JavaScript
     * codes in the request URL that is often reported in error messages.
     *
     * WARNING: This method isn't able to filter every single attack possible.
     * If you need to secure your application completely, you should look for
     * some third party libraries or write that on your own
     *
     * @param message The message string to be filtered
     * @return filtered message
     */
    public static String filter(String message) {

        if (message == null)
            return (null);

        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("<");
                    break;
                case '>':
                    result.append(">");
                    break;
                case '&':
                    result.append("&");
                    break;
                case '"':
                    result.append("\"");
                break;
            default:
                result.append(content[i]);
            }
        }
        return (result.toString());

    }
}

