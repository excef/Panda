package org.panda_lang.panda.core.parser.essential;

import org.panda_lang.panda.core.parser.Atom;
import org.panda_lang.panda.core.parser.Parser;
import org.panda_lang.panda.core.parser.ParserCenter;
import org.panda_lang.panda.core.parser.ParserLayout;
import org.panda_lang.panda.core.parser.essential.assistant.VariableAssistant;
import org.panda_lang.panda.core.parser.essential.util.EssentialPriority;
import org.panda_lang.panda.core.syntax.Factor;
import org.panda_lang.panda.core.syntax.Variable;
import org.panda_lang.panda.lang.PNull;

public class VariableParser implements Parser {

    static {
        ParserLayout parserLayout = new ParserLayout(new VariableParser());
        parserLayout.pattern("*=*;", EssentialPriority.VARIABLE.getPriority());
        parserLayout.pattern(";", EssentialPriority.VARIABLE.getPriority() * 100);
        ParserCenter.registerParser(parserLayout);
    }

    @Override
    public Variable parse(Atom atom) {
        String source = atom.getSourcesDivider().getLine();
        String[] ss = VariableAssistant.splitAndClear(source);
        if (ss == null || ss.length < 1) {
            System.out.println("[VariableParser] Cannot parseLocal: " + source);
            return null;
        }

        Factor factor = new Factor("null", new PNull());
        if (ss.length > 1) {
            atom.setSourceCode(ss[1]);
            ParameterParser parser = new ParameterParser();
            factor = parser.parse(atom);
        }

        String[] lss = ss[0].split(" ");
        String variable = lss.length > 1 ? lss[1] : lss[0];

        if (factor.getDataType() == null && lss.length > 1) {
            factor.setDataType(lss[0]);
        }

        return new Variable(atom.getParent(), variable, factor);
    }

}