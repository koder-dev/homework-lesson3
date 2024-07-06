package org.homework;

@SaveTo(path = "/Users/vecus/IdeaProjects/homework3/text.txt", methodName = "save")
public final class TextContainer {
    private final String text;

    public TextContainer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
