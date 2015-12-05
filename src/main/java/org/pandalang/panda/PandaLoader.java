package org.pandalang.panda;

import org.pandalang.panda.core.parser.improved.PandaParser;
import org.pandalang.panda.util.IOUtils;

import java.io.File;

public class PandaLoader {

    public static PandaScript loadSimpleScript(File file) {
        if (file.isDirectory()) {
            return null;
        }
        return loadSimpleScript(IOUtils.getContent(file));
    }

    public static PandaScript loadSimpleScript(String source) {
        PandaParser parser = new PandaParser(source);
        PandaScript script = parser.parse();
        return script;
    }

}