package org.pandalang.panda.core.parser.depracted.util;

public enum SyntaxIndication {

    BLOCK("(){", "{"),
    CLOSE("}", "};"),
    COMMENT("//", "#"),
    METHOD("();"),
    RUNTIME("()"),
    STATEMENT(";"),
    VARIABLE("=;");

    private final String[] indication;

    private SyntaxIndication(String... indication) {
        this.indication = indication;
    }

    public String[] getIndication() {
        return this.indication;
    }

    public static SyntaxIndication recognize(String s) {
        for (SyntaxIndication i : values()) {
            for (String indi : i.getIndication()) {
                if (s.equals(indi)) {
                    return i;
                }
            }
        }
        return null;
    }

}